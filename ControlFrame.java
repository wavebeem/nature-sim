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
    private JButton stepButton;

    public ControlFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        toolbar = new JPanel();

        runButton  = new RunButton();
        stepButton = new StepButton();
        stopButton = new StopButton();

        toolbar.add(runButton);
        toolbar.add(stepButton);
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
            if (theSim == null) {
                theSim = new Simulation(DEFAULT_SIZE);
                theDetails = new DetailFrame(null); // theSim.getGrid().getSummaries()
            }
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
            theDetails.dispose();
            theSim = null;
        }
    }

    private class StepButton
    extends JButton
    implements ActionListener {
        public StepButton() {
            super("Step");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            if (theSim != null) {
                theSim.step();
            }
        }
    }
}
