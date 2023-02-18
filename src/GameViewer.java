import javax.swing.*;
import java.awt.*;
// SOPHIE HO 2/17/23
// The front end to the BlackJack game. Displays all the visuals in a separate window.

public class GameViewer extends JFrame
{
    // Game object to sync to front end and access methods and variables.
    private Game game;
    // Background image for the window.
    private Image background;
    // Proportions of the window
    private final int WINDOW_WIDTH = 960;
    private final int WINDOW_HEIGHT = 540;
    // Proportions of the cards
    public final int CARD_WIDTH = 133;
    public final int CARD_HEIGHT = 208;

    // GameViewer constructor
    public GameViewer(Game game)
    {
        // Initialize game object
        this.game = game;

        // Create the background image
        background = new ImageIcon("Resources/CardGame background.png").getImage();

        // Initialize and set up window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("BlackJack");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    // The paint method draws the front-end
    public void paint(Graphics g)
    {
        // Print the player's names to specify which card hand belongs to which player.
        // Reset the background, font, and font color
        g.drawImage(background, 0, 20, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        // Draw the player names in their respective coordinates
        g.drawString(game.getPlayer1().getName(), WINDOW_WIDTH / 2, 50);
        g.drawString(game.getPlayer2().getName(), WINDOW_WIDTH / 2, WINDOW_HEIGHT - 3);

        // Draw the cards
        // Set the axis values of the card coordinates
        int x = 275;
        int y = 58;
        // When cards are ready to show:
        // Print out the whole hand for player 1
        for(int i = 0; i < game.getHand1().size(); i++)
        {
            // Draw the card
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
        // Draw the card values for each player
        // Set the font
        g.setFont(new Font("Serif", Font.BOLD, 15));
        // For player 1: draw their name and "total card value" to set up for the total value
        String p1value = game.getPlayer1().getName() + "'s total card value: ";
        String p1totalVal =  "" + game.getTotalValue(game.getHand1());
        g.drawString(p1value, 40, 100);
        // Same for player 2: draw name and "total card value" String to set up
        String p2value = game.getPlayer2().getName() + "'s total card value: ";
        String p2totalVal = "" +  + game.getTotalValue(game.getHand2());
        g.drawString(p2value, 40, WINDOW_HEIGHT - 100);

        // Draw their hand values in a bigger font
        g.setFont(new Font("Serif", Font.BOLD, 30));
        // Player 1:
        g.drawString(p1totalVal, 110, 140);
        // Player 2:
        g.drawString(p2totalVal, 110, WINDOW_HEIGHT - 60);

    }
}
