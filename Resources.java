import java.util.*;
import java.util.zip.*;
import java.security.*;
import java.awt.*;
import java.net.*;
import java.io.*;

public class Resources {
    public static Image dirtImage;
    public static Image overlay;

    public static void load() {
        try {
            unsafeLoad();
            System.err.println(imageMap);
        }
        catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    private static void unsafeLoad() throws IOException {
        dirtImage = Util.loadImage("Dirt.png");
        overlay   = Util.loadImage("overlay.png");
        imageMap = new HashMap<String, Image>();

        CodeSource codeSrc = Resources.class.getProtectionDomain().getCodeSource();
        if (codeSrc == null)
            throw new RuntimeException("Couldn't get code source");

        URL jar = codeSrc.getLocation();
        ZipInputStream zip = new ZipInputStream(jar.openStream());
        ZipEntry entry = zip.getNextEntry();
        while (entry != null) {
            String filename = entry.getName();
            System.err.println("FILENAME = " + filename);
            String[] path  = filename.split("/");
            System.err.println("path = " + Arrays.asList(path));

            boolean isImage = path.length == 3
                           && path[0].equals("resources")
                           && path[1].equals("img")
                           && path[2].endsWith(".png");

            if (isImage) {
                System.err.println("IS AN IMAGE");
                String[] hunks = path[2].split("\\.");
                imageMap.put(hunks[0], Util.loadImage(path[2]));
            }

            entry = zip.getNextEntry();
        }
    }

    private static Map<String, Image> imageMap;
    public static Image imageByName(String name) {
        if (imageMap.containsKey(name)) {
            return imageMap.get(name);
        }
        else {
            return imageMap.get("ERROR");
        }
    }
}
