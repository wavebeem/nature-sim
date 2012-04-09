import javax.swing.*;
import java.awt.*;
import java.util.*;

public class DetailFrame extends JFrame {
    private ArrayList<GridWidget> widgets;
    private static final int DEFAULT_SIZE = 6;
    private JPanel grid;
    private static final int PADDING = 3;
    private static final Color bg = new Color(32, 32, 32);
    public DetailFrame(GridSquare[][] g) {
        widgets = new ArrayList<GridWidget>();

        if (g == null) {
            int i = DEFAULT_SIZE * DEFAULT_SIZE;
            while (i --> 0) {
                widgets.add(new GridWidget(null));
            }
        }
        else {
            throw new RuntimeException("IMPLEMENT ME");
        }

        setBackground(bg);

        grid = new JPanel();
        grid.setLayout(new GridLayout(
            DEFAULT_SIZE, DEFAULT_SIZE,
            PADDING, PADDING));

        grid.setBorder(BorderFactory.createLineBorder(bg, PADDING));
        grid.setBackground(bg);

        for (GridWidget widget: widgets) {
            grid.add(widget);
        }

        add(grid);

        pack();

        setVisible(true);
    }

    private class GridSquare {}
}
