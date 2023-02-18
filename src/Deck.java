import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

// SOPHIE HO 2/17/23
// This class creates, shuffles, and deals all the cards in the deck.
public class Deck
{
    // ArrayList of all the cards in the deck
    private ArrayList<Card> cards = new ArrayList<Card>();
    // Int value for num of cards left in deck
    private int cardsLeft;
    // ArrayList of images for the front end for each card
    private ArrayList<Image> cardImages;
    // C represents a card object (used when creating the deck and creating card objs)
    private Image c;
    // The length of the deck
    private final int DECK_LENGTH = 52;

    public Deck(String[] cRank, String[] cSuit, int[] cVal)
    {
        // Create the ArrayList of card images
        cardImages = new ArrayList<Image>();
        for(int i = 1; i < DECK_LENGTH + 1; i++)
        {
            // Create the card image and add it to the ArrayList of card images
            c = new ImageIcon("Resources/" + i + ".png").getImage();
            cardImages.add(c);
        }
        int x = 0;
        // Create the deck
        for (int i = 0; i < cRank.length; i++)
        {
            for (int j = 0; j < cSuit.length; j++)
            {
                Card newCard = new Card(cRank[i], cSuit[j], cVal[i], cardImages.get(x));
                cards.add(newCard);
                x++;
            }

        }
        cardsLeft = cards.size();
        // Shuffle the deck
        shuffle();
    }

    // Check if deck is empty
    public boolean isEmpty()
    {
        if(cardsLeft == 0)
        {
            return true;
        }
        return false;
    }

    // Return the whole deck
    public ArrayList<Card> getDeck()
    {
        return cards;
    }

    // Return the num of cards left in deck
    public int getCardsLeft()
    {
        return cardsLeft;
    }

    // Deal one card
    public Card deal()
    {
        if(isEmpty())
        {
            return null;
        }
        return cards.get(--cardsLeft);
    }

    // Shuffle the deck
    public void shuffle()
    {
        cardsLeft = cards.size();
        Card swapper1;
        Card swapper2;
        int randInt;
        for(int i = 0; i < cards.size(); i++)
        {
            randInt = (int)(Math.random() * cards.size());
            swapper1 = cards.get(randInt);
            swapper2 = cards.get(i);
            cards.set(randInt, swapper2);
            cards.set(i, swapper1);
        }
    }

    // Return the ArrayList of all card images.
    public ArrayList<Image> getCardImages()
    {
        return cardImages;
    }

}
