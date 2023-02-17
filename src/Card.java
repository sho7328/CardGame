import java.awt.*;

public class Card
{
    private String rank;
    private String suit;
    private int value;
    private Image cardImage;
    private GameViewer gv;

    public Card(String theRank, String theSuit, int theValue, Image cImage)
    {
        cardImage = cImage;
        rank = theRank;
        suit = theSuit;
        value = theValue;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Image getCardImage()
    {
        return cardImage;
    }
    public void setCardImage(Image ci)
    {
        this.cardImage = ci;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString()
    {
        return rank + " of " + suit;
    }

    public void draw(Graphics g, int x, int y, Card c)
    {
        g.drawImage(c.getCardImage(), x, y, gv.CARD_WIDTH, gv.CARD_HEIGHT, gv);
    }

}
