// Sophie Ho Blackjack
// 12/7/2022
// SOPHIE HO 2/17/23
// The Game class runs the back-end of BlackJack.

import java.util.Scanner;
import java.util.ArrayList;

public class Game
{
    // Instance variables
    // The Player object for player 1.
    private Player user1;
    // Creating an ArrayList for player 1's hand.
    private ArrayList<Card> hand1 = new ArrayList<>();
    // Player object for player 2.
    private Player user2;
    // ArrayList for player 2's hand.
    private ArrayList<Card> hand2 = new ArrayList<>();
    // The entire deck with all the cards.
    private Deck deck;
    // Scanner to ask for user input.
    public static Scanner input = new Scanner(System.in);
    // Player 1's name used to create the first player object.
    private static String name1;
    // Player 2's name used to create the second player object.
    private static String name2;
    // Static int to specify which round it is.
    private static int round;
    // GameViewer object to sync up the front and back end.
    private GameViewer gv;
    // Game constructor to set all arrays and variables.
    public Game()
    {
        // Set the game round
        round = 0;

        // Initialize deck
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"♠", "♥", "♦", "♣"};
        int[] values = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        deck = new Deck(ranks, suits, values);

        // Initialize players and deal their 2 starting cards
        hand1.add(deck.deal());
        hand1.add(deck.deal());
        user1 = new Player(name1, hand1);

        hand2.add(deck.deal());
        hand2.add(deck.deal());
        user2 = new Player(name2, hand2);

        // Set up gv object to sync with front-end
        gv = new GameViewer(this);
    }

    // Run the back-end game
    public static void main(String[] args)
    {
        // Ask for the player names
        System.out.print("Player 1 name: ");
        name1 = input.nextLine();
        System.out.print("Player 2 name: ");
        name2 = input.nextLine();

        // ROUND 1
        round = 1;
        Game round1 = new Game();
        round1.printInstructions();
        // Show the cards when they are ready to show
        System.out.println("ROUND 1:");
        round1.playGame();

        // ROUND 2
        round = 2;
        round1.transition();
        Game round2 = new Game();
        System.out.println("ROUND 2:");
        round2.playGame();

        // ROUND 3
        round = 3;
        round1.transition();
        Game round3 = new Game();
        System.out.println("ROUND 3:");
        round3.playGame();

        // Print the overall winner
        System.out.println(round3.getWinner());
    }

    // The main method for the BlackJack game
    public void playGame()
    {
        // A for loop so that I can break out when a winner is decided early
        for (int b = 0; b < 1; b++)
        {
            // Print out the player's hands and starting values
            // Player 1:
            System.out.println(user1.getName() + "'s starting hand: ");
            System.out.println(hand1);
            System.out.println("Your starting value is " + getTotalValue(hand1));
            // Update the front-end visuals to show the player hands
            gv.repaint();
            // Check for natural blackjack
            if (checkBJ(hand1, user1))
            {
                // Player 1 automatically wins teh round if they get a natural blackjack.
                System.out.println(user1.getName() + " got a natural Blackjack and automatically won this round!");
                input.nextLine();
                // Break out of the loop and start the next round or end the game.
                break;
            }
            input.nextLine();

            // Player 2:
            System.out.println(user2.getName() + "'s starting hand: ");
            System.out.println(hand2);
            System.out.println("Your starting value is " + getTotalValue(hand2));
            // Check for natural blackjack
            if (checkBJ(hand2, user2))
            {
                // Player 2 automatically wins the round if they get a natural blackjack.
                System.out.println(user2.getName() + " got a natural Blackjack and automatically won this round!");
                input.nextLine();
                break;
            }
            input.nextLine();

            // PLAYER 1 TURN
            System.out.print(user1.getName() + "'s turn:\n");
            // While P1 hasn't reached blackjack:
            do
            {
                // Ask P1 if they want to hit or stand
                System.out.println(user1.getName() + ", hit or stand?");
                // If they stand:
                if(input.nextLine().equals("stand"))
                {
                    // Break out of loop and move on to player 2
                    break;
                }
                // If they hit:
                else
                {
                    // Add a card to the player's hand
                    hand1.add(deck.deal());
                    // Reprint the total value
                    System.out.println(hand1);
                    System.out.println("The total value is now " + getTotalValue(hand1));
                    // Update front end visuals to add the new card to the screen
                    gv.repaint();
                }
            }
            while(getTotalValue(hand1) < 21);

            // If p1 busts
            if(bust(hand1, user1))
            {
                // Print in the console that there is a bust and p2 wins.
                System.out.print("BUST! " + user2.getName() + " automatically wins this round.");
                input.nextLine();
                // Break out of current round and go to next round or end game
                break;
            }
            // Check for blackjack.
            else if (checkBJ(hand1, user1))
            {
                // If player reaches blackjack, they automatically win the round
                System.out.print("Blackjack! " + user1.getName() + " wins this round!");
                input.nextLine();
                // Break out of current round and go to next round or end game
                break;
            }

            // PLAYER 2 TURN
            System.out.println(user2.getName() + "'s turn:");
            // While P2 doesn't reach blackjack
            do
            {
                // Asks to hit or stand
                System.out.println(user2.getName() + ", hit or stand?");
                // If stand, break out and move on to next round, or end game.
                if(input.nextLine().equals("stand"))
                {
                    break;
                }
                // If hit, deal them another card and print out the new total value.
                else
                {
                    hand2.add(deck.deal());
                    System.out.println(hand2);
                    System.out.println("The total value is now " + getTotalValue(hand2));
                    // Update the front-end to display the new card.
                    gv.repaint();
                }
            }
            while(getTotalValue(hand2) < 21);

            // Check is P2 busts
            if(bust(hand2, user2))
            {
                // If they bust, print it out
                System.out.print("BUST! " + user1.getName() + " automatically wins this round.");
                input.nextLine();
                // Break and move on to next round
                break;
            }
            // Check for a BlackJack
            else if (checkBJ(hand2, user2))
            {
                System.out.print("Blackjack! " + user2.getName() + " wins this round!");
                input.nextLine();
                // Break and move on to next round
                break;
            }

            // Check who won the round
            // If p1 wins
            if (getTotalValue(hand1) > getTotalValue(hand2))
            {
                // Print out the winner and add a point for winner
                System.out.print(user1.getName() + " wins this round!");
                user1.addPoints(1);
                input.nextLine();
            }
            // Check if there's a draw
            else if (getTotalValue(hand1) == getTotalValue(hand2))
            {
                System.out.print("There was a draw! No one wins this round.");
                input.nextLine();
            }
            // If p2 wins
            else
            {
                System.out.print(user2.getName() + " wins this round!");
                input.nextLine();
                user2.addPoints(1);
            }
        }
    }

    // Calculate and return the total value of the player's hand.
    public int getTotalValue(ArrayList<Card> hand)
    {
        int totalVal = 0;
        for(int i = 0; i < hand.size(); i++)
        {
            totalVal += hand.get(i).getValue();
        }
        return totalVal;
    }

    // Check if the player's hand has busted or not.
    public boolean bust(ArrayList<Card> hand, Player user)
    {
        if (getTotalValue(hand) > 21)
        {
            user.addPoints(-1);
            return true;
        }
        return false;
    }

    // Check if the player has reached blackjack in their hand.
    public boolean checkBJ(ArrayList<Card> hand, Player user)
    {
        //if the player or computer automatically gets blackjack in their initial hand, print blackjack and say they won
        //the round and then add one to the player/comp's points
        if (getTotalValue(hand) == 21)
        {
            user.addPoints(1);
            return true;
        }
        return false;
    }

    // Check who won out of the three rounds and return a String that notifies who wins.
    public String getWinner()
    {
        if(user1.getPoints() > user2.getPoints())
        {
            return user1.getName() + " wins best out of all the rounds!! Thanks for playing!";
        }
        else if(user1.getPoints() == user2.getPoints())
        {
            return "There was a draw between " + user1.getName() + " and " + user2.getName() + " out of all the rounds!"
                    + " Thanks for playing!!";
        }
        return user2.getName() + " wins best out of all the rounds!! Thanks for playing!";
    }

    // Print game instructions
    public void printInstructions()
    {
        transition();
        for(int i = 0; i < 1; i++)
        {
            System.out.println("Welcome to Blackjack! Press enter after every part of the game to move on");
            System.out.print("If you don't need to read the instructions, enter \"x\". Otherwise, press enter.");
            if(input.nextLine().equals("x"))
            {
                break;
            }
            System.out.print("INSTRUCTIONS:\nThis is a 2-player game.");
            input.nextLine();
            System.out.print("Each player gets a hand of 2 cards.");
            input.nextLine();
            System.out.print("Your goal is to reach a number less than or equal to 21 through the sum of your cards.");
            input.nextLine();
            System.out.print("You have the option to hit (draw another card to increase your hand value), or stand (not "
                           + "increase your hand value any more). ");
            input.nextLine();
            System.out.print("Keep in mind you are drawing a new card from a shuffled deck, so there is a possibility ");
            System.out.print("of drawing a total of over 21. \nThis will cause a bust, and your opponent will automatically win.");
            input.nextLine();
            System.out.print("Best out of three rounds wins.");
            input.nextLine();
            System.out.print("Good luck!");
            input.nextLine();
        }
        transition();
    }

    // A visual transition between sections of the game.
    public void transition()
    {
        System.out.println("---------------------------------------------------------------------------");
    }

    // Return p1 object for GameViewer access
    public Player getPlayer1()
    {
        return user1;
    }

    // Return p2 object
    public Player getPlayer2()
    {
        return user2;
    }

    // Return p1's hand for gv access
    public ArrayList<Card> getHand1()
    {
        return hand1;
    }

    // Return p2's hand
    public ArrayList<Card> getHand2()
    {
        return hand2;
    }
}
