package Sandbox.NumberTextTwo.NumberText;

/**
 * Created by jonathanevans on 10/12/2015.
 */
public interface IThousand {
    static String THOUSAND_AS_TEXT = "THOUSAND";

    void   setValue(final int value);
    String convertAsText();
    int    extractRoot();
    int    extractRootValue();
}
