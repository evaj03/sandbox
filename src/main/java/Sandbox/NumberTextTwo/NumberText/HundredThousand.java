package Sandbox.NumberTextTwo.NumberText;

/**
 * Created by jonathanevans on 10/12/2015.
 */
public class HundredThousand implements IHundredThousand {
    private String rootUnitAsText  = null;
    private String rootAsText      = null;
    private String rootValueAsText = null;
    private int    rootUnit;
    private int    root;
    private int    rootValue;
    private int    value;

    public HundredThousand() {}

    public HundredThousand(final int value) {
        setValue(value);
    }


    public void setValue(final int value) {
        this.value = value;
        init();
    }

    public final String convertAsText() {
        return getText();
    }


    public final int extractRoot() {
        if (rootAsText != null && !rootAsText.isEmpty()) {
            root = Integer.valueOf(rootAsText);
        }

        return root;
    }


    public final int extractRootValue() {
        if (rootValueAsText != null && !rootValueAsText.isEmpty()) {
            rootValue = Integer.valueOf(rootValueAsText);
        }

        return rootValue;
    }


    // NON API

    private void init() {
        convertToString();
        rootUnit  = extractRootUnit();
        root      = extractRoot();
        rootValue = extractRootValue();
    }


    private void convertToString() {
        String asText = Integer.toString(value);

        if (asText.length() > 1) {
            rootUnitAsText  = asText.substring(asText.length() - 6, asText.length() - 3);
            rootAsText      = rootUnitAsText + "000";
            rootValueAsText = asText.substring(asText.length() - 6);
        }
    }


    private int extractRootUnit() {
        if (rootUnitAsText != null && !rootUnitAsText.isEmpty()) {
            rootUnit = Integer.valueOf(rootUnitAsText);
        }

        return rootUnit;
    }


    private final String getText() {
        String   text    = null;
        IHundred hundred = new Hundred(rootUnit);

        text = hundred.convertAsText();
        text += " ";
        text += IHundredThousand.THOUSAND_AS_TEXT;

        int remainder = rootValue % 100000;

        System.out.println("remainder = " + remainder);

        if (remainder != 0) {
            text += " AND ";

            // Have only units (e.g. 100001)
            if (remainder < 10 && remainder > -1) {
                IUnit unit  = new Unit(rootValue);
                text += unit.convertUnitAsText();
            } else {
                // Have only tens (e.g. 100010)
                if (remainder > 9 && remainder < 100) {
                    ITen ten  = new Ten(rootValue);
                    text += ten.convertAsText();
                } else {
                    hundred.setValue(rootValue);
                    text += hundred.convertAsText();
                }
            }
        }

        return text;
    }
}
