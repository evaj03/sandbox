package Sandbox.ScratchPad;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListOfNumbers {
    private List<Integer> list;
    private static final int SIZE = 10;

    public ListOfNumbers () {
        list = new ArrayList<Integer>(SIZE);
        for (int i = 0; i < SIZE; i++)
            list.add(new Integer(i));
    }

    public void writeList() {
        PrintWriter out = null;

        try {
            System.out.println("Entering try statement");
            out = new PrintWriter(new FileWriter("OutFile.txt"));

            for (int i = 0; i < SIZE; i++)
                out.println("Value at: " + i + " = " + list.get(i));
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Caught IndexOutOfBoundsException: " +
                    e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        } finally {
            if (out != null) {
                System.out.println("Closing PrintWriter");
                out.close();
            } else {
                System.out.println("PrintWriter not open");
            }
        }
    }

    public void readList(final String filename) throws FileNotFoundException {
        if (filename == null || filename.isEmpty()) {
            throw new FileNotFoundException("No file given.");
        }

        BufferedReader in = null;

        try {
            String line;

            in = new BufferedReader(new FileReader(filename));

            while ((line = in.readLine()) != null) {
                int a = Integer.parseInt(line);

                System.out.println(a);

                list.add(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        ListOfNumbers l = new ListOfNumbers();

        try {
            l.readList("numbers.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}