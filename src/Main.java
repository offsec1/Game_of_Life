import javax.swing.*;
import java.awt.*;

/**
 * @author s0563806
 */
public class Main {

    /**
     * this just starts the app
     * @param args
     */
    public static void main(String[] args) {
        PlayGround.init(50);
        new Main();
    }

    /**
     * start the app with system look and feel
     */
    public Main() {
        EventQueue.invokeLater(() -> { //that's pretty amazing that arrow there replaces a whole call to a new sth and function head (anonymous class)
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //display the GUI in a nice way
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                /* ignore if this doesn't work */
            }

            JFrame frame = new JFrame("Conway's Game of Life - by offsec1");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new GUI());
            frame.pack();
            frame.setLocationRelativeTo(null); //start position is middle of the screen
            frame.setVisible(true);
        });
    }

}

