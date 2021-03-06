import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DetailFrame
extends JFrame
implements WindowListener, KeyListener {
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
        super("Nature Sim");
        this.controlFrame = controlFrame;

        setLocationByPlatform(true);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(this);
        addKeyListener(this);

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
        }

        setBackground(bg);

        scroll.setBorder(BorderFactory.createLineBorder(bg, 0));
        grid.setBorder(BorderFactory.createLineBorder(bg, OUTER_PADDING));
        grid.setBackground(bg);

        for (GridWidget widget: widgets) {
            grid.add(widget);
        }

        scroll.getHorizontalScrollBar().setBlockIncrement(3 * 24);
        scroll.getHorizontalScrollBar().setUnitIncrement (3 * 24);
        scroll.getVerticalScrollBar()  .setBlockIncrement(3 * 24);
        scroll.getVerticalScrollBar()  .setUnitIncrement (3 * 24);

        add(scroll);

        pack();
        setSize(new Dimension(512, 512));
        setMinimumSize(new Dimension(6 * 24, 6 * 24));

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

    public void keyTyped   (KeyEvent e) {}
    public void keyPressed (KeyEvent e) {}
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ENTER) {
            controlFrame.runPause();
        }
        else if (code == KeyEvent.VK_SPACE) {
            controlFrame.stopAndStep();
        }
    }
}
