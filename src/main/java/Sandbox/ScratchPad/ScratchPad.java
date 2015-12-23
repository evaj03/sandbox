package Sandbox.ScratchPad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by jonathanevans on 11/12/2015.
 */
public class ScratchPad {

    public static void main(String[] args) {
        File file = new File(args[0]);
        RandomAccessFile input = null;
        String           line  = null;

        try {
            input = new RandomAccessFile(file, "r");

            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
