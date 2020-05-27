package file.channel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadSmallTextFile {

    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) throws IOException {
        var path = Paths.get("laposte_hexasmal.csv");
        var lines  = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.forEach(System.out::println);

    }
}
