package file.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

public class ReadFileWithFixedFileSizeByteBuffer {

    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) throws IOException {
        var path = Paths.get("laposte_hexasmal.csv");
        try (var fileChannel = FileChannel.open(path)) {
            var buffer = ByteBuffer.allocate(BUFFER_SIZE);
            var totalOfBytesRead = 0;
            for (var numberOfBytesRead = 0; (numberOfBytesRead = fileChannel.read(buffer)) != -1; totalOfBytesRead += numberOfBytesRead) {
                buffer.flip(); // set limit = current position and set position = 0
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
            }
            System.out.println("\n" + totalOfBytesRead + " bytes read");
        }
    }
}
