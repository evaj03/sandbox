package Sandbox.NumberTextTwo.NumberText;

/**
 * Created by jonathanevans on 10/12/2015.
 */
public interface ITeen {
    enum TeenConversion {
        ELEVEN    ("ELEVEN",    11),
        TWELVE    ("TWELVE",    12),
        THIRTEEN  ("THIRTEEN",  13),
        FOURTEEN  ("FOURTENN",  14),
        FIFTEEN   ("FIFTEEN",   15),
        SIXTEEN   ("SIXTEEN",   16),
        SEVENTEEN ("SEVENTEEN", 17),
        EIGHTTEEN ("EIGHTTEEN", 18),
        NINETEEN  ("NINETEEN",  19);

        private final String text;
        private final int    value;

        TeenConversion(String text, int value) {
            this.text  = text;
            this.value = value;
        }

        String text() {
            return text;
        }

        int value() {
            return value;
        }

        String asText(final int value) {
            return TeenConversion.values()[value].text;
        }

        int asNumber(final String text) {
            return TeenConversion.valueOf(text).value;
        }
    }

    void   setValue(final int value);
    String convertAsText();
    int    extractTeen();
}
