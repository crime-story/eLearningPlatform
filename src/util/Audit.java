package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Audit {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

    public static void logAction(String action) {

        String print = action + ',' + formatter.format(LocalDateTime.now()).toString();

        File csvOutputFile = new File("audit.csv");
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(csvOutputFile, true))) {
            pw.println(print);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
