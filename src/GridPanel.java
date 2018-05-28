import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

//TODO PM: resizing doesn't work at all this needs to be fixed

public class GridPanel extends JPanel {

    private int n = PlayGround.getN(); // this needs to be changed in the long run...
    private List<Rectangle> cells;
    private Point selectedCell;

    public GridPanel() {

        //Panel stuff
        setPreferredSize(new Dimension(500, 502));

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

                //whats the 5s?
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
