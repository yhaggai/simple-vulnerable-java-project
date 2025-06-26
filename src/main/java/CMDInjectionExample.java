import io.github.pixee.security.BoundedLineReader;
import java.util.*;
import java.io.*;

class CMDInjectionExample {
    public static void main(String[] args) throws Exception {
        Process proc = Runtime.getRuntime().exec("ls -laxo " + args[0]);
        proc.waitFor();

        var stdio = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        var stderr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
        String line = null;
        while ((line = BoundedLineReader.readLine(stdio, 5_000_000)) != null) {
            System.out.println(line);
        }
        while ((line = BoundedLineReader.readLine(stderr, 5_000_000)) != null) {
            System.out.println(line);
        }
    }
}
