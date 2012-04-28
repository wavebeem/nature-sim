import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TextFrame extends JFrame {
    public TextFrame(String txt) {
        super("Stats");
        setLocationByPlatform(true);
        final int pad = 4;
        final JTextArea textArea = new JTextArea();
        final JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        textArea.setText(txt);
        add(textArea);
        setMinimumSize(new Dimension(200, 150));
        pack();
        setVisible(true);
    }
}
