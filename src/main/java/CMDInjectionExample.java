import io.github.pixee.security.SystemCommand;
import java.util.*;
import java.io.*;

class CMDInjectionExample {
    public static void main(String[] args) throws Exception {
        Process proc = SystemCommand.runCommand(Runtime.getRuntime(), "ls -laxo " + args[0]);
        proc.waitFor();

        var stdio = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        var stderr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
        String line = null;
        while ((line = stdio.readLine()) != null) {
            System.out.println(line);
        }
        while ((line = stderr.readLine()) != null) {
            System.out.println(line);
        }
    }
}
