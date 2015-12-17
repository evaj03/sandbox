package Sandbox.Locking;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class Locker {
    private String fileName = "locker.txt";

    public static void main(String[] args) {
        Locker locker = new Locker();

        try {
            locker.lockFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void lockFile() throws IOException {
        RandomAccessFile stream  = new RandomAccessFile(fileName, "rw");
        FileChannel      channel = stream.getChannel();
        FileLock         lock    = null;

        try {
            lock = channel.tryLock();
        } catch (final OverlappingFileLockException e) {
            stream.close();
            channel.close();
        }

        stream.writeChars("test lock");
        lock.release();

        stream.close();
        channel.close();
    }
}
