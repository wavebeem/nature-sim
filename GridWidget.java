import java.awt.*;
import javax.swing.*;
import java.util.*;

public class GridWidget extends JComponent {
    private GridSquare gridSquare;
    //private static final Color bg = new Color(64, 64, 16);
    private Color bg = new Color(64, 64, 16);
    private static final Color fg = new Color(64, 64, 64);

    public Dimension getMinimumSize()   { return new Dimension(16, 16); }
    public Dimension getPreferredSize() { return new Dimension(16, 16); }

    public GridWidget(GridSquare gridSquare) {
        this.gridSquare = gridSquare;
        bg = Util.randomColor();
    }

    public void paintComponent(Graphics g) {
        paintComponent((Graphics2D)g);
    }

    public void paintComponent(Graphics2D g) {
        final int w = getWidth();
        final int h = getHeight();
        g.setBackground(bg);
        g.clearRect(0, 0, w, h);
        //g.setColor(fg);
        //g.fillOval(0, 0, w, h);
        g.drawImage(Resources.gridSquareOverlayImage, 0, 0, w, h, null);
    }
}
