import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.io.*;

public class ControlFrame extends JFrame {
    private static final int DEFAULT_SIZE = 6;

    private Simulation  theSim;
    private DetailFrame theDetails;

    private JPanel mapBar;
    private JPanel toolbar;

    private JLabel fileLabel;
    private JButton runButton;
    private JButton stopButton;
    private JButton stepButton;
    private JComboBox fileCombo;
    private JCheckBox shouldGrid;

    public ControlFrame() {
        super("Nature Sim Control Frame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationByPlatform(true);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        toolbar = new JPanel();
        mapBar  = new JPanel();

        runButton  = new RunButton(this);
        stepButton = new StepButton();
        stopButton = new StopButton();
        fileCombo  = new JComboBox(Util.ls("resources/maps"));
        fileLabel  = new JLabel("Map:");
        shouldGrid = new GridLinesCheckBox();

        shouldGrid.setSelected(true);

        fileCombo.setEditable(false);

        mapBar.add(fileLabel);
        mapBar.add(fileCombo);
        mapBar.add(shouldGrid);

        toolbar.add(runButton);
        toolbar.add(stepButton);
        toolbar.add(stopButton);

        add(mapBar);
        add(toolbar);

        pack();
        setMinimumSize(getSize());

        setVisible(true);
    }

    private class RunButton
    extends JButton
    implements ActionListener {
        private ControlFrame that;
        public RunButton(ControlFrame that) {
            super("Run");
            this.that = that;
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            if (theSim == null) {
                String dirname = (String)fileCombo.getSelectedItem();
                dirname = "resources/maps/" + dirname;
                System.out.println(new File(dirname, "animals.dat"));
                theSim = new Simulation(
                    new File(dirname, "animals.dat"),
                    new File(dirname, "terrain.dat"));
                theDetails = new DetailFrame(that,
                    theSim.getGrid().getGridSquares());
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
            stop();
        }
    }

    protected void stop() {
        theDetails.dispose();
        theSim = null;
    }

    private class StepButton
    extends JButton
    implements ActionListener {
        public StepButton() {
            super("Step");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            step();
        }
    }

    private void step() {
        if (theSim != null && theDetails != null) {
            theSim.step();
            theDetails.repaint();
        }
    }

    private class GridLinesCheckBox
    extends JCheckBox
    implements ChangeListener {
        public GridLinesCheckBox() {
            super("Grid lines?");
            addChangeListener(this);
        }

        public void stateChanged(ChangeEvent e) {
            setGridLines();
        }
    }

    private void setGridLines() {
        if (theDetails != null) {
            theDetails.setGridLinesAreEnabled(shouldGrid.isSelected());
            theDetails.repaint();
        }
    }
}
