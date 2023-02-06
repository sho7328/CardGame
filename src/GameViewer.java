import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameViewer extends JFrame
{
    private Game game;
    private Image c;
    private ArrayList<Image> cards;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 1500;
    private final int DECK_LENGTH = 52;

    public GameViewer(Game game)
    {
        this.game = game;
        // Create the ArrayList of card images
        cards = new ArrayList<Image>();
        for(int i = 0; i < DECK_LENGTH; i++)
        {
            c = new ImageIcon("Resources/" + i + ".png").getImage();
            cards.add(c);
        }

        // Initialize and set up window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("BlackJack");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }


}
