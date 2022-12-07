import java.util.Scanner;
import java.util.ArrayList;

public class Game
{
    //instance variables
    private Player user;
    private ArrayList<Card> pHand = new ArrayList<>();
    private Player comp;
    private ArrayList<Card> cHand = new ArrayList<>();
    private boolean hit;
    private Deck deck;

    public Game()
    {
        //initialize deck
        String[] ranks = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"♣", "♦", "♥", "♠"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        deck = new Deck(ranks, suits, values);

        //initialize user
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = input.nextLine();
        pHand.add(deck.deal());
        pHand.add(deck.deal());
        user = new Player(name, pHand);

        //initialize computer
        cHand.add(deck.deal());
        cHand.add(deck.deal());
        comp = new Player("Computer", cHand);

    }

    public void playGame()
    {
        printInstructions();
        System.out.println("Here is your starting hand: ");

    }

    //print instructions
    public void printInstructions()
    {
        System.out.println("Welcome to Blackjack! \nINSTRUCTIONS:");
        System.out.println("");
    }
}
