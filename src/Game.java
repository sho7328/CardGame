// Sophie Ho Blackjack
// 12/7/2022

import java.util.Scanner;
import java.util.ArrayList;

public class Game
{
    //instance variables
    private Player user1;
    private ArrayList<Card> hand1 = new ArrayList<>();
    private Player user2;
    private ArrayList<Card> hand2 = new ArrayList<>();
    private Deck deck;
    public static Scanner input = new Scanner(System.in);
    private static String name1;
    private static String name2;
    private static int round;
    private GameViewer gv;
    public Game()
    {
        round = 0;
        //initialize deck
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"♠", "♥", "♦", "♣"};
        int[] values = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        deck = new Deck(ranks, suits, values);

        //initialize players
        hand1.add(deck.deal());
        hand1.add(deck.deal());
        user1 = new Player(name1, hand1);

        hand2.add(deck.deal());
        hand2.add(deck.deal());
        user2 = new Player(name2, hand2);

        gv = new GameViewer(this);
    }

    public static void main(String[] args)
    {
        System.out.print("Player 1 name: ");
        name1 = input.nextLine();
        System.out.print("Player 2 name: ");
        name2 = input.nextLine();

        round = 1;
        Game round1 = new Game();
        round1.printInstructions();
        // Show the cards when they are ready to show
        System.out.println("ROUND 1:");
        round1.playGame();

        round = 2;
        round1.transition();
        Game round2 = new Game();
        System.out.println("ROUND 2:");
        round2.playGame();

        round = 3;
        round1.transition();
        Game round3 = new Game();
        System.out.println("ROUND 3:");
        round3.playGame();

        System.out.println(round3.getWinner());
    }

    public void playGame()
    {
        // a for loop so that I can break out when a winner is decided early
        for (int b = 0; b < 1; b++)
        {
            //print out the player's hands
            // player 1 hand
            System.out.println(user1.getName() + "'s starting hand: ");
            System.out.println(hand1);
            System.out.println("Your starting value is " + getTotalValue(hand1));
            gv.repaint();
            //check for natural blackjack
            if (checkBJ(hand1, user1))
            {
                System.out.println(user1.getName() + " got a natural Blackjack and automatically won this round!");
                input.nextLine();
                break;
            }
            input.nextLine();

            //player 2 hand
            System.out.println(user2.getName() + "'s starting hand: ");
            System.out.println(hand2);
            System.out.println("Your starting value is " + getTotalValue(hand2));
            //check for natural
            if (checkBJ(hand2, user2))
            {
                System.out.println(user2.getName() + " got a natural Blackjack and automatically won this round!");
                input.nextLine();
                break;
            }
            input.nextLine();

            //PLAYER 1 TURN
            System.out.print(user1.getName() + "'s turn:\n");
            do
            {
                System.out.println(user1.getName() + ", hit or stand?");
                if(input.nextLine().equals("stand"))
                {
                    break;
                }
                // add a card to the player's hand and reprint the total value
                else
                {
                    hand1.add(deck.deal());
                    System.out.println(hand1);
                    System.out.println("The total value is now " + getTotalValue(hand1));
                    gv.repaint();
                }
            }
            while(getTotalValue(hand1) < 21);

            //if p1 busts, p2 wins
            if(bust(hand1, user1))
            {
                System.out.print("BUST! " + user2.getName() + " automatically wins this round.");
                input.nextLine();
                break;
            }
            //check for blackjack. If player reaches blackjack, they automatically win the round
            else if (checkBJ(hand1, user1))
            {
                System.out.print("Blackjack! " + user1.getName() + " wins this round!");
                input.nextLine();
                break;
            }

            //PLAYER 2 TURN
            System.out.println(user2.getName() + "'s turn:");
            do
            {
                System.out.println(user2.getName() + ", hit or stand?");
                if(input.nextLine().equals("stand"))
                {
                    break;
                }
                else
                {
                    hand2.add(deck.deal());
                    System.out.println(hand2);
                    System.out.println("The total value is now " + getTotalValue(hand2));
                    gv.repaint();
                }
            }
            while(getTotalValue(hand2) < 21);

            if(bust(hand2, user2))
            {
                System.out.print("BUST! " + user1.getName() + " automatically wins this round.");
                input.nextLine();
                break;
            }
            else if (checkBJ(hand2, user2))
            {
                System.out.print("Blackjack! " + user2.getName() + " wins this round!");
                input.nextLine();
                break;
            }

            //check who won the round
            if (getTotalValue(hand1) > getTotalValue(hand2))
            {
                System.out.print(user1.getName() + " wins this round!");
                input.nextLine();
            }
            else if (getTotalValue(hand1) == getTotalValue(hand2))
            {
                System.out.print("There was a draw! No one wins this round.");
                input.nextLine();
            }
            else
            {
                System.out.print(user2.getName() + " wins this round!");
                input.nextLine();
            }
        }
    }

    // calculate and return the total value of the player's hand.
    public int getTotalValue(ArrayList<Card> hand)
    {
        int totalVal = 0;
        for(int i = 0; i < hand.size(); i++)
        {
            totalVal += hand.get(i).getValue();
        }
        return totalVal;
    }

    //check if the player's hand has busted or not.
    public boolean bust(ArrayList<Card> hand, Player user)
    {
        if (getTotalValue(hand) > 21)
        {
            user.addPoints(-1);
            return true;
        }
        return false;
    }

    //check if the player has reached blackjack in their hand.
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

    // check who won out of the three rounds.
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

    //print instructions
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
            //System.out.print("Best out of three rounds wins.");
            //input.nextLine();
            System.out.print("Good luck!");
            input.nextLine();
        }
        transition();
    }

    // a visual transition between sections of the game.
    public void transition()
    {
        System.out.println("---------------------------------------------------------------------------");
    }

    public Player getPlayer1()
    {
        return user1;
    }

    public Player getPlayer2()
    {
        return user2;
    }

    public ArrayList<Card> getHand1()
    {
        return hand1;
    }

    public ArrayList<Card> getHand2()
    {
        return hand2;
    }

}
