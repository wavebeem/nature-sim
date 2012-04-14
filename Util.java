import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Util {
    public static final Random random = new Random();

    public static Image loadImage(String filename) {
        try {
            return ImageIO.read(new File("resources/img/" + filename));
        }
        catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    public static Color randomColor() {
        return Color.getHSBColor(random.nextInt(360) / 360.0f, 0.75f, 0.50f);
    }

    public static String[] ls(String dirname) {
        try {
            File dir = new File(dirname);
            File[] files = dir.listFiles();

            String[] strings = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                strings[i] = files[i].getName();
            }

            // The results aren't *guaranteed* to be supported,
            // but they generally seem to be...
            // Better safe than sorry.
            Arrays.sort(strings);

            return strings;
        }
        catch (Exception e) {
            System.out.println("Something nasty happened: " + e);
            return null;
        }
    }

    public static void sleep() {
        try {
            Thread.sleep(50);
        }
        catch (InterruptedException e) {
            System.err.println("INTERRUPTED WHILE SLEEPING");
        }
    }
}
