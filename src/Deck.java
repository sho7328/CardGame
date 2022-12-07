import java.util.ArrayList;
public class Deck
{
    private ArrayList<Card> cards = new ArrayList<Card>();
    private int cardsLeft;

    public Deck(String[] cRank, String[] cSuit, int[] cVal)
    {
        for (int i = 0; i < cSuit.length; i++)
        {
            for (int j = 0; j < cRank.length; j++)
            {
                Card newCard = new Card(cRank[j], cSuit[i], cVal[j]);
                cards.add(newCard);
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
        return cards.get(cardsLeft--);
    }

    public void shuffle()
    {
        cardsLeft = cards.size();
        Card swapper;
        int randInt;
        for(int i = cards.size(); i < cards.size(); i--)
        {
            randInt = (int)(Math.random() * cards.size());
            swapper = cards.get(randInt);
            cards.set(randInt, cards.get(i));
            cards.set(i, swapper);
        }
    }
}
