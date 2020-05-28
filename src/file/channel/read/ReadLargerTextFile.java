package file.channel.read;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadLargerTextFile {

    public static void main(String[] args) throws IOException {
        var path = Paths.get("laposte_hexasmal.csv");
        try(var scanner = new Scanner(path, StandardCharsets.UTF_8)) {
            while(scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }

    }
}
