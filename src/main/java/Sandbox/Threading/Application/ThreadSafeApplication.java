package Sandbox.Threading.Application;

import Sandbox.Threading.IRandomText;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by jonathanevans on 18/12/2015.
 */
public class ThreadSafeApplication implements IThreadSafeApplication {
    // Use of final for instance variables reduces mutability of objects
    private final String      fileName;
    private final IRandomText randomText;


    // Constructors are always guaranteed to complete before any thread can be interrupted.
    public ThreadSafeApplication(final String filename, final IRandomText randomText) {
        this.fileName   = filename;
        this.randomText = randomText;
    }


    @Override
    public String getFileName() {
        return fileName;
    }


    @Override
    public void write(final int length) {
        if (randomText != null) {
            writeToFile("", length);
        }
    }


    @Override
    public void write(final String prepend, final int length) {
        if (randomText != null) {
            writeToFile(prepend, length);
        }
    }


    private synchronized void writeToFile(final String prepend, final int length) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            writer.append(prepend + " :: ");
            writer.append(randomText.getText(length));
            writer.append('\n');

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
