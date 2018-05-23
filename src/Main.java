import javax.swing.*;
import java.awt.*;

public class Main {

    //this just starts the app
    public static void main(String[] args) {
        PlayGround.init(25);
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
            //frame.setContentPane(new JPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridBagLayout());
            frame.add(new Grid());
            /*
            Here comes the whole add thing maybe also for
            - Button
            - Timer (which does all the things)
            - etc.
             */
            frame.pack();
            frame.setLocationRelativeTo(null); //start position is middle of the screen
            frame.setVisible(true);
        });
    }

}

