package file.channel;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class ReadFileWithMappedByteBuffer {

    public static void main(String[] args) throws IOException {
        var path = Paths.get("laposte_hexasmal.csv");
        try (var fileChannel = FileChannel.open(path)) {
            var buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            buffer.load();
            int count = buffer.limit();
            IntStream.range(0, count).map(index -> buffer.get())
                    .forEach(b -> System.out.print((char) b));
            buffer.clear();
        }
    }
}
