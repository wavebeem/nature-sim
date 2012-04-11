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
        grid.setLayout(new GridLayout(DEFAULT_SIZE, DEFAULT_SIZE, 1, 1));
            //PADDING, PADDING));

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
