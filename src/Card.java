// SOPHIE HO 2/17/23
// Contains all the info for each card such as rank, suit, value, and its image.
import java.awt.*;

public class Card
{
    // Rank specifies whether card is ace, jack, two, ten, queen, etc.
    private String rank;
    // Suit specifies whether card is spades, diamonds, hearts, or clubs.
    private String suit;
    // Value represents value of each card (ace = 11, two = 2, jack, queen, king = 10, etc)
    private int value;
    // The image of the card
    private Image cardImage;
    // GameViewer image to access variables
    private GameViewer gv;

    // Set the card's image, rank, suit, and value.
    public Card(String theRank, String theSuit, int theValue, Image cImage)
    {
        cardImage = cImage;
        rank = theRank;
        suit = theSuit;
        value = theValue;
    }

    // Return card rank
    public String getRank() {
        return rank;
    }

    // Set the card's rank
    public void setRank(String rank) {
        this.rank = rank;
    }

    // Return the card suit
    public String getSuit() {
        return suit;
    }

    // Set card suit
    public void setSuit(String suit) {
        this.suit = suit;
    }

    // Get the card's image
    public Image getCardImage()
    {
        return cardImage;
    }

    // Set the card's image
    public void setCardImage(Image ci)
    {
        this.cardImage = ci;
    }

    // Get card value
    public int getValue() {
        return value;
    }

    // Set the card's value
    public void setValue(int value) {
        this.value = value;
    }

    // Returns a string that says the card's rank and suit
    public String toString()
    {
        return rank + " of " + suit;
    }

    // Draw a card by using its own image at coordinates (x,y)
    public void draw(Graphics g, int x, int y, Card c)
    {
        g.drawImage(c.getCardImage(), x, y, gv.CARD_WIDTH, gv.CARD_HEIGHT, gv);
    }

}
