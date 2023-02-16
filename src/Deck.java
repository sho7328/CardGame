import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
public class Deck
{
    private ArrayList<Card> cards = new ArrayList<Card>();
    private int cardsLeft;
    private GameViewer gv;
    private ArrayList<Image> cardImages;
    private Image c;
    private final int DECK_LENGTH = 52;

    public Deck(String[] cRank, String[] cSuit, int[] cVal)
    {
        // Create the ArrayList of card images
        cardImages = new ArrayList<Image>();
        for(int i = 1; i < DECK_LENGTH + 1; i++)
        {
            c = new ImageIcon("Resources/" + i + ".png").getImage();
            cardImages.add(c);
        }
        int x = 0;
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
        shuffle();
    }

    public boolean isEmpty()
    {
        if(cardsLeft == 0)
        {
            return true;
        }
        return false;
    }

    public ArrayList<Card> getDeck()
    {
        return cards;
    }

    public int getCardsLeft()
    {
        return cardsLeft;
    }

    public Card deal()
    {
        if(isEmpty())
        {
            return null;
        }
        return cards.get(--cardsLeft);
    }

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

    public ArrayList<Image> getCardImages()
    {
        return cardImages;
    }

}
