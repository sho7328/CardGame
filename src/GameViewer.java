import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameViewer extends JFrame
{
    private Game game;
    private Player p;
    private Deck d;
    private Image background;
    private final int WINDOW_WIDTH = 960;
    private final int WINDOW_HEIGHT = 540;
    private final int CARD_WIDTH = 133;
    private final int CARD_HEIGHT = 208;

    public GameViewer(Game game)
    {
        this.game = game;

        // Create the background image
        background = new ImageIcon("Resources/CardGame background.png").getImage();

        // Initialize and set up window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("BlackJack");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g)
    {
        g.drawImage(background, 0, 20, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        // Print the player's names to specify which card hand belongs to which player.
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString(game.getPlayer1().getName(), WINDOW_WIDTH / 2, 50);
        g.drawString(game.getPlayer2().getName(), WINDOW_WIDTH / 2, WINDOW_HEIGHT - 3);
        // Set the axis values of the card coordinates
        int x = 275;
        int y = 58;
        // When cards are ready to show:
        // Print out the whole hand for player 1
        for(int i = 0; i < game.getHand1().size(); i++)
        {
            g.drawImage(game.getHand1().get(i).getCardImage(), x, y, CARD_WIDTH, CARD_HEIGHT, this);
            // Change the x value for the next card in the hand
            x += 36 + CARD_WIDTH;
        }
        // Reset the values of the xy coordinates for player 2 cards.
        x = 275;
        y = WINDOW_HEIGHT - 229;
        // Print out the whole hand for player 2
        for(int i = 0; i < game.getHand2().size(); i++)
        {
            g.drawImage(game.getHand2().get(i).getCardImage(), x, y, CARD_WIDTH, CARD_HEIGHT, this);
            // Change the x value for the next card in the hand
            x += 36 + CARD_WIDTH;
        }
//        // Draw the card values for each player
//        String p1value = game.getPlayer1().getName() + "'s total card value: " + game.getTotalValue(game.getHand1());
//        g.drawString(p1value, 30, 30);
//
//
//        String p2value = game.getPlayer2().getName() + "'s total card value: " + game.getTotalValue(game.getHand2());


    }
}
