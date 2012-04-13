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
    private static final int PADDING = 6;
    private static final Color bg = new Color(32, 32, 32);
    private ControlFrame controlFrame;
    public DetailFrame(ControlFrame controlFrame, GridSquare[][] g) {
        this.controlFrame = controlFrame;

        setLocationByPlatform(true);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(this);

        widgets = new ArrayList<GridWidget>();

        grid = new JPanel();

        if (g != null) {
            grid.setLayout(new GridLayout(g.length, g[0].length, 1, 1));
            for (GridSquare[] row: g) {
                for (GridSquare gs: row) {
                    widgets.add(new GridWidget(gs));
                }
            }
        }
        else {
            int i = DEFAULT_SIZE * DEFAULT_SIZE;
            grid.setLayout(new GridLayout(DEFAULT_SIZE, DEFAULT_SIZE, 1, 1));
            while (i --> 0) {
                widgets.add(new GridWidget(null));
            }
            System.out.println("IMPLEMENT ME");
        }

        setBackground(bg);

        grid.setBorder(BorderFactory.createLineBorder(bg, PADDING));
        grid.setBackground(bg);

        for (GridWidget widget: widgets) {
            grid.add(widget);
        }

        add(grid);

        pack();

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
