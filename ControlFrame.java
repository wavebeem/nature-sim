import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class ControlFrame extends JFrame {
    private static final int DEFAULT_SIZE = 6;

    private Simulation  theSim;
    private DetailFrame theDetails;

    private JPanel toolbar;

    private JButton runButton;
    private JButton stopButton;

    public ControlFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        theSim = null; // new Simulation(DEFAULT_SIZE)
        //theDetails = new DetailFrame(null); // theSim.getGrid().getSummaries()

        toolbar = new JPanel();

        runButton  = new RunButton();
        stopButton = new StopButton();

        toolbar.add(runButton);
        toolbar.add(stopButton);

        add(toolbar);

        pack();

        setVisible(true);
    }

    private class RunButton
    extends JButton
    implements ActionListener {
        public RunButton() {
            super("Run");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            Debug.echo("RUN!");
            theDetails = new DetailFrame(null); // theSim.getGrid().getSummaries()
        }
    }

    private class StopButton
    extends JButton
    implements ActionListener {
        public StopButton() {
            super("Stop");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            Debug.echo("STOP!");
        }
    }

    private class Simulation {}
}
