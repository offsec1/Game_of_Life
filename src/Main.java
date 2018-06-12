import javax.swing.*;
import java.awt.*;

public class Main {

    //this just starts the app
    public static void main(String[] args) {
        PlayGround.init(50);
        new Main();
    }

    public Main() {
        EventQueue.invokeLater(() -> { //that's pretty amazing that arrow there replaces a whole call to a new sth and function head
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                //test for linux / windows and check what happens there...
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

