import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DetailFrame
extends JFrame
implements WindowListener {
    private ArrayList<GridWidget> widgets;
    private static final int DEFAULT_SIZE = 16;
    private JPanel grid;
    private static final int INNER_PADDING = 3;
    private static final int OUTER_PADDING = 9;
    private static final Color bg = new Color(32, 32, 32);
    private ControlFrame controlFrame;
    public DetailFrame(ControlFrame controlFrame, GridSquare[][] g) {
        super("Nature Sim Detail View");
        this.controlFrame = controlFrame;

        setLocationByPlatform(true);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(this);

        widgets = new ArrayList<GridWidget>();

        grid = new JPanel();

        if (g != null) {
            grid.setLayout(new GridLayout(g.length, g[0].length,
                INNER_PADDING, INNER_PADDING));
            for (GridSquare[] row: g) {
                for (GridSquare gs: row) {
                    widgets.add(new GridWidget(gs));
                }
            }
        }
        else {
            int i = DEFAULT_SIZE * DEFAULT_SIZE;
            grid.setLayout(new GridLayout(DEFAULT_SIZE, DEFAULT_SIZE,
                INNER_PADDING, INNER_PADDING));
            while (i --> 0) {
                widgets.add(new GridWidget(null));
            }
            System.out.println("IMPLEMENT ME");
        }

        setBackground(bg);

        grid.setBorder(BorderFactory.createLineBorder(bg, OUTER_PADDING));
        grid.setBackground(bg);

        for (GridWidget widget: widgets) {
            grid.add(widget);
        }

        add(grid);

        pack();

        final int size = Math.min(getWidth(), getHeight());
        setMinimumSize(new Dimension(size, size));

        setVisible(true);
    }

    public void windowClosing(WindowEvent e) {
        controlFrame.stop();
    }

    public void windowDeactivated (WindowEvent e) {}
    public void windowActivated   (WindowEvent e) {}
    public void windowDeiconified (WindowEvent e) {}
    public void windowIconified   (WindowEvent e) {}
    public void windowClosed      (WindowEvent e) {}
    public void windowOpened      (WindowEvent e) {}
}
