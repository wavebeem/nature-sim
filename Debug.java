import java.io.*;

public class Debug {
    private static boolean DEBUG = false;
    private static PrintStream out = System.out;

    public static void echo(Object... args) {
        if (! DEBUG)
            return;

        if (args.length < 1)
            return;

        for (int i = 0; i < args.length - 1; i++) {
            out.print(args[i] + " ");
        }

        out.println(args[args.length - 1]);
    }

    public static void printf(String format, Object... args) {
        if (DEBUG)
            out.printf(format, args);
    }
}
