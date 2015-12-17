package Sandbox.NumberText;

/**
 * Created by jonathanevans on 10/12/2015.
 */
public interface IUnit {
    enum UnitConversion {
        ZERO  ("ZERO",  0),
        ONE   ("ONE",   1),
        TWO   ("TWO",   2),
        THREE ("THREE", 3),
        FOUR  ("FOUR",  4),
        FIVE  ("FIVE",  5),
        SIX   ("SIX",   6),
        SEVEN ("SEVEN", 7),
        EIGHT ("EIGHT", 8),
        NINE  ("NINE",  9);

        private final String text;
        private final int    value;

        UnitConversion(String text, int value) {
            this.text  = text;
            this.value = value;
        }

        public String text() {
            return text;
        }

        public int value() {
            return value;
        }
    }

    void   setValue(final int value);
    String convertUnitAsText();
    int    extractUnit();
}
