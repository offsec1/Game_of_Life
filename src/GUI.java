import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JPanel {

    private int n = PlayGround.getN(); // this needs to be changed in the long run...
    private List<Rectangle> cells;
    private Point selectedCell;
    private JButton start;
    private Timer timer;

    public GUI() {


        //Panel stuff
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 600));
        /*
        setPreferredSize(new Dimension(500, 502));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500,30 ));
*/
        //Grid stuff
        cells = new ArrayList<>(n * n);
        MouseAdapter clickHandler;
        clickHandler = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int width = getWidth();
                int height = getHeight();

                int cellWidth = width / n;
                int cellHeight = height /n;

                int xOffset = (width - (n * cellWidth)) / 2;
                int yOffset = (height - (n * cellHeight)) / 2;

                selectedCell = null;

                if (e.getX() >= xOffset && e.getY() >= yOffset) {
                    int column = (e.getX() - xOffset) / cellWidth;
                    int row = (e.getY() - yOffset) / cellHeight;

                    if (column >= 0 && row >= 0 && column < n && row < n)
                        selectedCell = new Point(column, row);
                }

                repaint();
            }
        };
        addMouseListener(clickHandler);

        //Button
        start = new JButton("start");

        start.setLayout(new BorderLayout());
        start.setPreferredSize(new Dimension(75, 40));

        add(start, BorderLayout.SOUTH);
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
            //Live goes on
            PlayGround.evolve();
            repaint();
            invalidate();
            System.out.print("Test\n");

        };
        //TODO PM: here you have to make one year passed (makeMove on Playground)

        timer = new Timer(10, tickEvent);
        timer.setRepeats(true);
    }


    @Override
    public void invalidate() {
        cells.clear();
        selectedCell = null;
        super.invalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        int cellWidth = width / n;
        int cellHeight = height / n;

        int xOffset = (width - (n * cellWidth)) / 2;
        int yOffset = (height - (n * cellHeight)) / 2;

        if (cells.isEmpty()) {
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    Rectangle cell = new Rectangle(
                            xOffset + (col * cellWidth),
                            yOffset + (row * cellHeight),
                            cellWidth,
                            cellHeight);
                    cells.add(row * n + col, cell); //just making sure that the index is correct for later use
                }
            }
        }

        if (selectedCell != null) {
            int index = selectedCell.x + (selectedCell.y * n);

            PlayGround.changeIndexValue(index / n, index % n);
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                Rectangle cell = cells.get(i * n + j); //get the index from the array values

                if (PlayGround.getGameArray()[i][j]) {
                    g2d.setColor(Color.BLACK);
                    g2d.fill(cell);
                    g2d.draw(cell);
                }
                else {
                    g2d.setColor(Color.GRAY);
                    g2d.draw(cell);
                }
            }
        }
        g2d.dispose();
    }

}
