import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TextFrame extends JFrame {
    private static TextFrame that;
    private final JTextArea textArea;
    public TextFrame(String txt) {
        super("Stats");

        // Kill last window
        if (that != null)
            that.dispose();

        that = this;
        setLocationByPlatform(true);
        final int pad = 4;
        textArea = new JTextArea();
        final JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        textArea.setText(txt);
        add(textArea);
        setMinimumSize(new Dimension(200, 150));
        pack();
        setVisible(true);
    }

    public static void update(String txt) {
        if (that != null)
            that.update(txt);
    }

    public void update(String txt) {
        textArea.setText(txt);
    }
}
