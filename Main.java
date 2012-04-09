import javax.swing.*;
import javax.swing.UIManager.*;

public class Main {
    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
                if (info.getName().equals("Nimbus")) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception e) {
        }
        new ControlFrame();
    }
}
