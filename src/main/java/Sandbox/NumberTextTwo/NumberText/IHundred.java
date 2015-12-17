package Sandbox.NumberTextTwo.NumberText;

/**
 * Created by jonathanevans on 10/12/2015.
 */
public interface IHundred {
    static String HUNDRED_AS_TEXT = "HUNDRED";

    void   setValue(final int value);
    String convertAsText();
    int    extractHundred();
    int    extractHundredsTensAndUnit();
}
