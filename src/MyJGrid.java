import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Own JComponent for implementing the grid in a nice way
 */
public class MyJGrid extends JComponent {

    private List<Rectangle> cells;
    private Point selectedCell;

    /**
     * constructor - includes the click handler which turns the selected cell to true or false
     */
    public MyJGrid() {

        cells = new ArrayList<>(PlayGround.getN() * PlayGround.getN());
        MouseAdapter clickHandler;
        //click handling
        clickHandler = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int width = getWidth();
                int height = getHeight();

                int cellWidth = width / PlayGround.getN();
                int cellHeight = height / PlayGround.getN();

                int xOffset = (width - (PlayGround.getN() * cellWidth)) / 2;
                int yOffset = (height - (PlayGround.getN() * cellHeight)) / 2;

                selectedCell = null;

                if (e.getX() >= xOffset && e.getY() >= yOffset) {
                    int column = (e.getX() - xOffset) / cellWidth;
                    int row = (e.getY() - yOffset) / cellHeight;

                    if (column >= 0 && row >= 0 && column < PlayGround.getN() && row < PlayGround.getN()) {
                        selectedCell = new Point(column, row);
                        int index = selectedCell.x + (selectedCell.y * PlayGround.getN());
                        PlayGround.changeIndexValue(index / PlayGround.getN(), index % PlayGround.getN());
                    }
                }
                repaint();
            }
        };
        addMouseListener(clickHandler);
    }

    /**
     * clears the selected cell and the cell array on invalidation
     */
    @Override
    public void invalidate() {
        cells.clear();
        selectedCell = null;
        super.invalidate();
    }

    /**
     * paints the grid
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        int cellWidth = width / PlayGround.getN();
        int cellHeight = height / PlayGround.getN();

        int xOffset = (width - (PlayGround.getN() * cellWidth)) / 2;
        int yOffset = (height - (PlayGround.getN() * cellHeight)) / 2;

        cells.clear();

        if (cells.isEmpty()) {
            for (int row = 0; row < PlayGround.getN(); row++) {
                for (int col = 0; col < PlayGround.getN(); col++) {
                    Rectangle cell = new Rectangle(
                            xOffset + (col * cellWidth),
                            yOffset + (row * cellHeight),
                            cellWidth,
                            cellHeight);
                    cells.add(row * PlayGround.getN() + col, cell); //just making sure that the index is correct for later use
                }
            }
        }

        for(int i = 0; i < PlayGround.getN(); i++) {
            for(int j = 0; j < PlayGround.getN(); j++) {
                Rectangle cell = cells.get(i * PlayGround.getN() + j); //get the index from the array values

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
