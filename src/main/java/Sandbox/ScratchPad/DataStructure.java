package Sandbox.ScratchPad;

/**
 * Created by jonathanevans on 11/12/2015.
 */
public class DataStructure {

    // Create an array
    private final static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];

    public DataStructure() {
        // fill the array with ascending integer values
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }

    public int getSize() {
        return SIZE;
    }

    public int[] getArrayOfInts() {
        return arrayOfInts;
    }


    public int get(int index) {
        return arrayOfInts[index];
    }

    interface DataStructureIterator extends java.util.Iterator<Integer> { }

    // Inner class implements the DataStructureIterator interface,
    // which extends the Iterator<Integer> interface

    private class EvenIterator implements DataStructureIterator {

        // Start stepping through the array from the beginning
        private int nextIndex = 0;

        public boolean hasNext() {

            // Check if the current element is the last in the array
            return (nextIndex <= SIZE - 1);
        }

        public Integer next() {

            // Record a value of an even index of the array
            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);

            // Get the next even element
            nextIndex += 2;
            return retValue;
        }

        @Override
        public void remove() {

        }
    }


    public EvenIterator getEvenIterator() {
        return new EvenIterator();
    }


    public void printEven() {

        // Print out values of even indices of the array
        DataStructureIterator iterator = this.new EvenIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }


    public void print(DataStructureIterator iterator) {
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }


    public DataStructureIterator getOddIterator = new DataStructureIterator() {

        // Start stepping through the array from the beginning
        private int nextIndex = 1;

        @Override
        public boolean hasNext() {
            // Check if the current element is the last in the array
            return (nextIndex <= SIZE - 1);
        }

        @Override
        public Integer next() {
            // Record a value of an even index of the array
            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);

            // Get the next odd element
            nextIndex += 2;
            return retValue;
        }

        @Override
        public void remove() {

        }
    };


//    private class OddIterator implements DataStructureIterator {
//
//        // Start stepping through the array from the beginning
//        private int nextIndex = 1;
//
//        public boolean hasNext() {
//
//            // Check if the current element is the last in the array
//            return (nextIndex <= SIZE - 1);
//        }
//
//        public Integer next() {
//
//            // Record a value of an even index of the array
//            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);
//
//            // Get the next odd element
//            nextIndex += 2;
//            return retValue;
//        }
//
//        @Override
//        public void remove() {
//
//        }
//    }

    public static void main(String s[]) {

        // Fill the array with integer values and print out only
        // values of even indices
        final DataStructure ds = new DataStructure();
        ds.printEven();
        ds.print(ds.getEvenIterator());
        ds.print(ds.getOddIterator);

        System.out.println("print(DataStructureIterator) with "
                + "anonymous class, odd indicies");
        ds.print(
                new DataStructure.DataStructureIterator() {
                    private int nextIndex = 1;
                    public boolean hasNext() {
                        return (nextIndex <= ds.getSize() - 1);
                    }
                    public Integer next() {
                        int retValue = ds.get(nextIndex);
                        nextIndex += 2;
                        return retValue;
                    }
                    @Override
                    public void remove() {
                    }
                }
        );
    }
}
