package Sandbox.NumberTextTwo.NumberText;

/**
 * Created by jonathanevans on 10/12/2015.
 */
public class Hundred implements IHundred {
    private String hundredAsUnitAsText       = null;
    private String hundredsAsText            = null;
    private String hundredsTensAndUnitAsText = null;
    private int    hundredAsUnit;
    private int    hundreds;
    private int    hundredsTensAndUnit;
    private int    value;

    public Hundred() {}

    public Hundred(final int value) {
        setValue(value);
    }


    public void setValue(final int value) {
        this.value = value;
        init();
    }

    public final String convertAsText() {
        return getText();
    }


    public final int extractHundred() {
        if (hundredsAsText != null && !hundredsAsText.isEmpty()) {
            hundreds = Integer.valueOf(hundredsAsText);
        }

        return hundreds;
    }


    public final int extractHundredsTensAndUnit() {
        if (hundredsTensAndUnitAsText != null && !hundredsTensAndUnitAsText.isEmpty()) {
            hundredsTensAndUnit = Integer.valueOf(hundredsTensAndUnitAsText);
        }

        return hundredsTensAndUnit;
    }


    // NON API

    private void init() {
        convertToString();
        hundredAsUnit       = extractHundredAsUnit();
        hundreds            = extractHundred();
        hundredsTensAndUnit = extractHundredsTensAndUnit();
    }


    private void convertToString() {
        String asText = Integer.toString(value);

        if (asText.length() > 1) {
            hundredAsUnitAsText       = asText.substring(asText.length() - 3, asText.length() - 2);
            hundredsAsText            = hundredAsUnitAsText + "00";
            hundredsTensAndUnitAsText = asText.substring(asText.length() - 3);
        }
    }


    private int extractHundredAsUnit() {
        if (hundredAsUnitAsText != null && !hundredAsUnitAsText.isEmpty()) {
            hundredAsUnit = Integer.valueOf(hundredAsUnitAsText);
        }

        return hundredAsUnit;
    }


    private final String getText() {
        String text = null;

        IUnit unit = new Unit(hundredAsUnit);

        text = unit.convertUnitAsText();
        text += " ";
        text += IHundred.HUNDRED_AS_TEXT;

        int remainder = hundredsTensAndUnit % 100;

        if (remainder != 0) {
            text += " AND ";

            // Have only units (e.g. 101)
            if (remainder < 10 && remainder > -1) {
                unit.setValue(hundredsTensAndUnit);
                text += unit.convertUnitAsText();
            } else {
                ITen tens = new Ten(hundredsTensAndUnit);
                text += tens.convertAsText();
            }
        }

        return text;
    }
}
