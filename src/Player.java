import java.util.ArrayList;

public class Player
{
    private String name;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private int points;

    public Player(String pName)
    {
        name = pName;
        points = 0;
    }

    public Player(String pName, ArrayList<Card> pHand)
    {
        name = pName;
        points = 0;
        hand = pHand;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int num)
    {
        points += num;
    }

    public void addCard(Card newCard)
    {
        hand.add(newCard);
    }

    public String toString()
    {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }

}
