package Sandbox.NumberTextTwo.NumberText;

/**
 * Created by jonathanevans on 10/12/2015.
 */
public class Unit implements IUnit {
    private int    value;
    private int    unit;
    private String unitAsText = null;

    public Unit() {}

    public Unit(final int value) {
        setValue(value);
    }


    public void setValue(final int value) {
        this.value = value;
        init();
    }


    public String convertUnitAsText() {
        return getText();
    }


    public int extractUnit() {
        return unit;
    }


    // NON API

    private void init() {
        unitAsText = Integer.toString(value);
        unitAsText = unitAsText.substring(unitAsText.length() - 1);
        unit       = Integer.valueOf(unitAsText);
    }


    private String getText() {
        String asText = null;

        for (UnitConversion u : UnitConversion.values()) {
            if (u.value() == unit) {
                asText = u.text();
                break;
            }
        }

        return asText;
    }
}
