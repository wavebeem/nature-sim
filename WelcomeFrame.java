import javax.swing.*;
import java.awt.*;

public class WelcomeFrame extends JFrame {
private static final String help =
"Choose a map from the drop-down, then click “Load” to load the simulation with that map. You can click “Run” to run the simulation, or “Step” to step through it one step at a time. You can also adjust the speed of the simulation by adjusting the speed slider. Click “Stats” to get more detailed information about your current run of the simulation.";

    public WelcomeFrame() {
        super("HELP!!");
        final JTextArea   text = new JTextArea(help);
        final JScrollPane pane = new JScrollPane(text);
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(new JScrollPane(text));
        setSize(new Dimension(300, 200));
        setVisible(true);
    }
}
