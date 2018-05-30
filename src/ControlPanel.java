import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlPanel extends JPanel {

    private JButton start;
    private Timer timer;
    /*
    Here comes the whole add thing maybe also for
    - Button
    - Timer (which does all the things)
    - etc.
     */

    public ControlPanel() {

        //Panel stuff
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500,30 ));

        //Button
        start = new JButton("start");
        start.setLayout(new BorderLayout());
        start.setPreferredSize(new Dimension(75, 12));
        add(start, BorderLayout.EAST);
        ActionListener buttonHandler;
        buttonHandler = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer.isRunning()) {
                    timer.stop();
                    start.setText("start");
                } else {
                    timer.start();
                    start.setText("stop");
                }
            }
        };
        start.addActionListener(buttonHandler);

        //Timer
        ActionListener tickEvent = e -> {
            PlayGround.changeIndexValue(0,0);
            //here needs to be the grid repainted...
            System.out.print("Test\n");
        };
        //TODO PM: here you have to make one year passed (makeMove on Playground)

        timer = new Timer(10, tickEvent);
        timer.setRepeats(true);
    }


}
