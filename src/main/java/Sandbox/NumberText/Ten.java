package Sandbox.NumberText;

/**
 * Created by jonathanevans on 10/12/2015.
 */
public class Ten implements ITen, ITeen {
    private String rootUnitAsText  = null;
    private String rootAsText      = null;
    private String rootValueAsText = null;
    private int    root;
    private int    rootValue;
    private int    value;

    public Ten() {}

    public Ten(final int value) {
        setValue(value);
    }


    @Override
    public void setValue(final int value) {
        this.value = value;
        init();
    }


    @Override
    public String convertAsText() {
        return getText();
    }


    @Override
    public int extractTeen() {
        if (rootValue > 10 && rootValue < 20) {
            return rootValue;
        }
        return 0;
    }


    @Override
    public int extractTen() {
        if (rootAsText != null && !rootAsText.isEmpty()) {
            root = Integer.valueOf(rootAsText);
        }

        return root;
    }


    @Override
    public int extractTensAndUnit() {
        if (rootValueAsText != null && !rootValueAsText.isEmpty()) {
            rootValue = Integer.valueOf(rootValueAsText);
        }

        return rootValue;
    }


    // NON API

    private void init() {
        convertToString();
        root      = extractTen();
        rootValue = extractTensAndUnit();
    }


    private void convertToString() {
        String asText = Integer.toString(value);

        if (asText.length() > 1) {
            rootUnitAsText  = asText.substring(asText.length() - 2, asText.length() - 1);
            rootAsText      = rootUnitAsText + "0";
            rootValueAsText = asText.substring(asText.length() - 2);
        }
    }


    private String getText() {
        String text = null;

        if (rootValue > 0) {
            if (root > 0) {
                // Account for 'teen values
                if (rootValue > 10 && rootValue < 20) {
                    for (TeenConversion t : TeenConversion.values()) {
                        if (t.value() == rootValue) {
                            text = t.text();
                            break;
                        }
                    }
                } else if (rootValue % 10 == 0) {
                    // Account for values ending zero
                    for (TenConversion t : TenConversion.values()) {
                        if (t.value() == rootValue) {
                            text = t.text();
                            break;
                        }
                    }
                } else {
                    // Get 'whole' tens value
                    for (TenConversion t : TenConversion.values()) {
                        if (t.value() == root) {
                            text = t.text();
                            break;
                        }
                    }

                    text += " " + getUnitValue();
                }
            } else {
                // might have just a unit
                text = getUnitValue();
            }
        }

        return text;
    }


    private String getUnitValue() {
        IUnit unit = new Unit(rootValue);

        return unit.convertUnitAsText();
    }
}
