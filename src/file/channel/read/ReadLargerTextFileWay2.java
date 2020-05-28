package file.channel.read;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadLargerTextFileWay2 {

    public static void main(String[] args) throws IOException {
        var path = Paths.get("laposte_hexasmal.csv");
        try (var reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            reader.lines().forEach(System.out::println);
        }

    }
}
