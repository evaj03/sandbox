package Sandbox.Threading.Application;

import Sandbox.Threading.IRandomText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Create an application that will create a text file and write random text to it.
public class Application implements IApplication {
    private String      fileName;
    private File        file;
    private IRandomText randomText;


    public Application() { }

    public Application(final String filename) {
        fileName = filename;
    }

    public void setRandomText(final IRandomText randomText) {
        this.randomText = randomText;
    }

    public void setFileName(final String filename) {
        fileName = filename;
    }

    public String getFileName() {
        return fileName;
    }

    public void create() {
        file = new File(fileName);
    }

    public void write(final int length) {
        if (randomText != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

                writer.append(randomText.getText(length));
                writer.append('\n');

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
