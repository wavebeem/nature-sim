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
    private static final int INNER_PADDING = 0;
    private static final int OUTER_PADDING = 9;
    private static final Color bg = new Color(64, 64, 64);
    private ControlFrame controlFrame;
    private boolean gridLines = true;
    private JScrollPane scroll;
    public DetailFrame(ControlFrame controlFrame, GridSquare[][] g) {
        super("Nature Sim Detail View");
        this.controlFrame = controlFrame;

        setLocationByPlatform(true);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(this);

        widgets = new ArrayList<GridWidget>();

        grid   = new JPanel();
        scroll = new JScrollPane(grid);

        if (g != null) {
            grid.setLayout(new GridLayout(g.length, g[0].length,
                INNER_PADDING, INNER_PADDING));
            for (GridSquare[] row: g) {
                for (GridSquare gs: row) {
                    widgets.add(new GridWidget(gs, this));
                }
            }
        }
        else {
            int i = DEFAULT_SIZE * DEFAULT_SIZE;
            grid.setLayout(new GridLayout(DEFAULT_SIZE, DEFAULT_SIZE,
                INNER_PADDING, INNER_PADDING));
            while (i --> 0) {
                widgets.add(new GridWidget(null, this));
            }
            System.out.println("IMPLEMENT ME");
        }

        setBackground(bg);

        scroll.setBorder(BorderFactory.createLineBorder(bg, 0));
        grid.setBorder(BorderFactory.createLineBorder(bg, OUTER_PADDING));
        grid.setBackground(bg);

        for (GridWidget widget: widgets) {
            grid.add(widget);
        }

        add(scroll);

        pack();

        setVisible(true);
    }

    public void setGridLinesAreEnabled(boolean truth) {
        gridLines = truth;
        repaint();
    }

    public boolean gridLinesAreEnabled() {
        return gridLines;
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
