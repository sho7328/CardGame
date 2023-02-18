import java.util.ArrayList;
// SOPHIE HO 2/17/23
// Player class includes name and number of points each player has, along with their cards in their hand

public class Player
{
    // Player name
    private String name;
    // Player hand, an ArrayList of Cards
    private ArrayList<Card> hand = new ArrayList<Card>();
    // How many points they have
    private int points;

    // Set the player's name and points
    public Player(String pName)
    {
        name = pName;
        points = 0;
    }

    // Set the player name, points, and hand
    public Player(String pName, ArrayList<Card> pHand)
    {
        name = pName;
        points = 0;
        hand = pHand;
    }

    // Return player name
    public String getName() {
        return name;
    }

    // Return player hand
    public ArrayList<Card> getHand() {
        return hand;
    }

    // Return player points
    public int getPoints() {
        return points;
    }

    // Add num points to the number of points the player has
    public void addPoints(int num)
    {
        points += num;
    }

    // Add a new card to the player's hand
    public void addCard(Card newCard)
    {
        hand.add(newCard);
    }

    // Return the name, points, and cards that the player has.
    public String toString()
    {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }

}
