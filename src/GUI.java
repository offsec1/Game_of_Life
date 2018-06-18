import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel {

    private MyJGrid grid;
    private JButton startButton;
    private JSlider gridSlider;
    private JLabel gridLabel;
    private JComboBox modelComboBox;
    private Timer timer;

    public GUI() {

        //Panel settings
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 600));

        //Grid
        initGrid();
        add(grid, BorderLayout.PAGE_START);

        //Button
        initButton();
        add(startButton, BorderLayout.LINE_END);

        //Label
        gridLabel = new JLabel("Grid: 50x50");
        add(gridLabel, BorderLayout.LINE_START);

        //Slider
        initSlider();
        add(gridSlider, BorderLayout.CENTER);

        //Timer
        initTimer();

        //ComboBox
        initModel();
        add(modelComboBox, BorderLayout.SOUTH);
    }

    private void initGrid() {
        grid = new MyJGrid();
        grid.setLayout(new BorderLayout());
        grid.setPreferredSize(new Dimension(550, 550));
    }

    private void initButton() {
        startButton = new JButton("start");
        startButton.setLayout(new BorderLayout());
        startButton.setPreferredSize(new Dimension(75, 40));

        ActionListener buttonHandler;
        buttonHandler = e -> {
            if (timer.isRunning()) {
                timer.stop();
                startButton.setText("start");
            } else {
                timer.start();
                startButton.setText("stop");
            }
        };
        startButton.addActionListener(buttonHandler);
    }


    private void initSlider() {
        gridSlider = new JSlider(JSlider.HORIZONTAL, 9, 100, 50);
        gridSlider.setPreferredSize(new Dimension(400,25));

        ChangeListener changeListener = e -> {
            JSlider source = (JSlider)e.getSource();
            int val = source.getValue();
            gridLabel.setText("Grid: " + val + "x" + val);
            PlayGround.setN(val);
            repaint();
        };
        gridSlider.addChangeListener(changeListener);
    }

    private void initTimer() {
        ActionListener tickEvent = e -> {
            //Live goes on
            PlayGround.evolve();
            repaint();
            invalidate();
        };

        timer = new Timer(100, tickEvent);
        timer.setRepeats(true);
    }

    //TODO PM: Here you go
    private void initModel() {
        String[] selection = new String[] {"Empty", "Glider", "Small Exploder", "Exploder", "Spaceship", "Tumbler"};
        modelComboBox = new JComboBox(selection);

        ActionListener comboBoxHandler = e -> {
            JComboBox source = (JComboBox)e.getSource();
            int val = source.getSelectedIndex();

            //clear before adding stuff
            PlayGround.clearGameArray();
            switch (val) {
                case 1:
                    PlayGround.spawnGlider();
                    break;
                case 2:
                    PlayGround.spawnSmallExploder();
                    break;
                case 3:
                    PlayGround.spawnExploder();
                    break;
                case 4:
                    PlayGround.spawnSpaceShip();
                    break;
                case 5:
                    PlayGround.spawnTumbler();
                    break;
                default:
                    //do nothing
                    break;
            }
            repaint();
        };
        modelComboBox.addActionListener(comboBoxHandler);
    }


}
