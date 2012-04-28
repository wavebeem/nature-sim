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
    private JButton stepButton;
    private JButton loadButton;
    private JButton statsButton;
    private JComboBox fileCombo;
    private JCheckBox shouldGrid;
    private JButton runButton;
    private JSlider delaySlider;

    private boolean threadShouldRun = false;
    private Thread runThread;

    @SuppressWarnings("unchecked")
    public ControlFrame() {
        super("Nature Sim");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationByPlatform(true);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        toolbar = new JPanel();
        mapBar  = new JPanel();

        final int SPEED_MIN =  10;
        final int SPEED_MAX = 500;
        final int SPEED_VAL = 200;
        delaySlider = new JSlider(SPEED_MIN, SPEED_MAX, SPEED_VAL);

        loadButton  = new LoadButton(this);
        stepButton  = new StepButton();
        statsButton = new StatsButton();
        fileCombo   = new JComboBox(Resources.getMaps());
        fileLabel   = new JLabel("Map:");
        shouldGrid  = new GridLinesCheckBox();
        runButton   = new RunButton();

        delaySlider.setMajorTickSpacing(0);
        delaySlider.setMinorTickSpacing((SPEED_MAX - SPEED_MIN)/10);
        delaySlider.setPaintTicks(true);
        delaySlider.setSnapToTicks(true);
        delaySlider.setInverted(true);

        shouldGrid.setSelected(true);

        fileCombo.setEditable(false);

        mapBar.add(fileLabel);
        mapBar.add(fileCombo);
        mapBar.add(shouldGrid);

        final JPanel midBar = new JPanel();
        midBar.add(new JLabel("Slow"));
        midBar.add(delaySlider);
        midBar.add(new JLabel("Fast"));

        toolbar.add(loadButton);
        toolbar.add(runButton);
        toolbar.add(stepButton);
        toolbar.add(statsButton);

        add(mapBar);
        add(midBar);
        add(toolbar);

        pack();
        setMinimumSize(getSize());
        setResizable(false);

        setVisible(true);
    }

    private class LoadButton
    extends JButton
    implements ActionListener {
        private ControlFrame that;
        public LoadButton(ControlFrame that) {
            super("Load");
            this.that = that;
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            if (true || theSim == null) {
                if (theDetails != null) {
                    stop();
                }
                String dirname = (String)fileCombo.getSelectedItem();
                dirname = "resources/maps/" + dirname + "/";
                theSim = new Simulation(
                    Util.stream(dirname + "animals.dat"),
                    Util.stream(dirname + "terrain.dat"));
                theDetails = new DetailFrame(that,
                    theSim.getGrid().getGridSquares());
            }
        }
    }

    private class RunButton
    extends JButton
    implements ActionListener {
        public RunButton() {
            super("Run/Pause");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            Debug.echo("RUN?!?!");
            if (theSim != null && theDetails != null && !isRunning()) {
                startRun();
            }
            else if (isRunning()) {
                tellRunThreadToFinish();
            }
        }
    }

    private class StatsButton
    extends JButton
    implements ActionListener {
        public StatsButton() {
            super("Stats");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            new TextFrame(Stats.getStats());
        }
    }

    private void startRun() {
        Debug.echo("START RUN!!!!");
        threadShouldRun = true;
        runThread = new Thread() {
            public void run() {
                while (isRunning()) {
                    step();
                    Util.sleep(delaySlider.getValue());
                }
            }
        };
        runThread.start();
    }

    private boolean isRunning() {
        return threadShouldRun;
    }

    private void tellRunThreadToFinish() {
        threadShouldRun = false;
        try {
            if (runThread != null) {
                runThread.join();
            }
        }
        catch (InterruptedException e) {
        }
        runThread = null;
    }

    protected void stop() {
        if (theDetails != null)
            theDetails.dispose();
        theSim = null;
        tellRunThreadToFinish();
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
            Debug.echo(">>> ControlFrame: STEPPING");
            theDetails.setTitle("Step number: " + theSim.getStepNumber());
            TextFrame.update(Stats.getStats());
            theSim.step();
            theDetails.repaint();
        }
    }

    private class GridLinesCheckBox
    extends JCheckBox
    implements ChangeListener {
        public GridLinesCheckBox() {
            super("Show grid lines");
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
