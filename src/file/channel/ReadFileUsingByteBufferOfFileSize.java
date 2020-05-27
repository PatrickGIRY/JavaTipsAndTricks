package file.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class ReadFileUsingByteBufferOfFileSize {

    public static void main(String[] args) throws IOException {
        var path = Paths.get("laposte_hexasmal.csv");
        try (var fileChannel = FileChannel.open(path)) {
            var fileSize = fileChannel.size();
            var buffer = ByteBuffer.allocate((int) fileSize);
            fileChannel.read(buffer);
            buffer.flip(); // set limit = current position and set position = 0
            IntStream.range(9, (int) fileSize)
                    .map(index -> buffer.get())
                    .forEach(c -> System.out.print((char) c));
            System.out.println("\nfileSize is " + fileSize);
        }
    }
}
