package Sandbox.NumberTextTwo.NumberText;

/**
 * Created by jonathanevans on 10/12/2015.
 */
public interface ITen {
    enum TenConversion {
        TEN       ("TEN",       10),
        TWENTY    ("TWENTY",    20),
        THIRTY    ("THIRTY",    30),
        FOURTY    ("FOURTY",    40),
        FIFTY     ("FIFTY",     50),
        SIXTY     ("SIXTY",     60),
        SEVENTY   ("SEVENTY",   70),
        EIGHTY    ("EIGHTY",    80),
        NINETY    ("NINETY",    90);

        private final String text;
        private final int    value;

        TenConversion(String text, int value) {
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
            return TenConversion.values()[value].text;
        }

        int asNumber(final String text) {
            return TenConversion.valueOf(text).value;
        }
    }

    void   setValue(final int value);
    String convertAsText();
    int    extractTen();
    int    extractTensAndUnit();
}
