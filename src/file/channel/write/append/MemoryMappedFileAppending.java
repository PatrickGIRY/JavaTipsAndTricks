package file.channel.write.append;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.*;

public class MemoryMappedFileAppending {

    private static final Path BIG_FILE_MASTER_COPY_PATH = FileSystems.getDefault().getPath("words.utf-8.txt");
    private static final Path BIG_FILE_DUPLICATE_PATH = FileSystems.getDefault().getPath("words.utf-8-duplicate.txt");
    private static final File BIG_FILE = BIG_FILE_DUPLICATE_PATH.toFile();
    private static final byte[] DATA = "How to overwrite or append to a file with memory mapped buffer.\n".getBytes();

    public static void main(String[] args) throws IOException {
        // Alloc 7Mb on memory map
        int count = 7 * 1024 * 1024;

        Files.copy(BIG_FILE_MASTER_COPY_PATH, BIG_FILE_DUPLICATE_PATH, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);

        // Open a file disk for mapping
        try(var memoryMappedFile = new RandomAccessFile(BIG_FILE, "rw");
            var fileChannel = memoryMappedFile.getChannel()) {
            var fileSize = (int) memoryMappedFile.length();

            // Mapping file into memory
            var mappedBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, count);

            // Seek to end of file for appending
            mappedBuffer.position(fileSize);

            // Appending to memory mapped file
            mappedBuffer.put(DATA);

            var newFileSize = mappedBuffer.position();

            // We alloc 7Mb on memory mapped file but we just use newFileSize bytes
            if (newFileSize < count) {
                fileChannel.truncate(newFileSize);
            }
        }
    }
}
