package Sandbox.NumberText;

/**
 * Created by jonathanevans on 10/12/2015.
 */
public class NumberText {
    private ConvertNumberToText converter;

    NumberText() {
        converter = new ConvertNumberToText();
    }


    public static void main( String[ ] args ) {
        System.out.println("Starting Number to Text Conversion...");

        NumberText numberText = new NumberText();

        numberText.test();

        System.out.println("Completed Number to Text Conversion...");
    }


    private void test() {
        System.out.println("Number to Text Conversion Result");

        for (int i = 0; i < 1000000; i++) {
            final String result = converter.convert(i);

            System.out.println("--------------------------------");
            System.out.println("Number to Convert:  ["   +
                               converter.getInput()      +
                               "] Textual Conversion: [" +
                               result                    +
                               "]");
        }
    }
}
