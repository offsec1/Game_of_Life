import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI extends JPanel {

    private int n = PlayGround.getN(); // this needs to be changed by the slider
    private List<Rectangle> cells;
    private Point selectedCell;

    private MyJGrid grid;
    private JButton start;
    private JSlider gridSlider;
    private JLabel gridLabel;
    private Timer timer;

    public GUI() {


        //Panel stuff
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 600));

        //Grid stuff
        grid = new MyJGrid();
        grid.setLayout(new BorderLayout());
        grid.setPreferredSize(new Dimension(550, 550));
        add(grid, BorderLayout.PAGE_START);

        //Button
        start = new JButton("start");
        start.setLayout(new BorderLayout());
        start.setPreferredSize(new Dimension(75, 40));
        add(start, BorderLayout.LINE_END);

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

        //Lable
        gridLabel = new JLabel("Grid: 50x50");
        add(gridLabel, BorderLayout.LINE_START);

        //Slider
        gridSlider = new JSlider(JSlider.HORIZONTAL, 9, 100, 50);
        gridSlider.setPreferredSize(new Dimension(400,30));
        add(gridSlider, BorderLayout.CENTER);
        ChangeListener changeListener = e -> {
            JSlider source = (JSlider)e.getSource();
            int val = source.getValue();
            gridLabel.setText("Grid: " + val + "x" + val);
            PlayGround.setN(val);
            repaint();
        };
        gridSlider.addChangeListener(changeListener);

        //Timer
        ActionListener tickEvent = e -> {
            //Live goes on
            PlayGround.evolve();
            repaint();
            invalidate();
            System.out.print("Test\n");

        };

        timer = new Timer(10, tickEvent);
        timer.setRepeats(true);
    }

}
