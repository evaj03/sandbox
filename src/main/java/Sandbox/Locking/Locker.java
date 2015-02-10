package Sandbox.Locking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class Locker {

    public static void main(String[] args) {

        File file = new File("filename");
        file.mkdir();
        file.setReadable(true);
        file.setWritable(true);
        file.setExecutable(true);
        FileOutputStream in = null;

        try {
            in = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            FileLock lock = in.getChannel().lock();

            int x = 0;
            int y = 3;

            while (x++ < 10) {
                // Try acquiring the lock without blocking. This method returns
                // null or throws an exception if the file is already locked.
                try {
                    lock = in.getChannel().tryLock();
                    System.out.println("I have the lock");
                } catch (OverlappingFileLockException e) {
                    // File is already locked in this thread or virtual machine
                    System.out.println("File is locked");
                }

                if (x >= 10) {
                    lock.release();
                    x = 0;
                    if(--y == 0) {
                        break;
                    }


                }
            }

            // Release the lock
            lock.release();

            // Close the file
            in.getChannel().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
