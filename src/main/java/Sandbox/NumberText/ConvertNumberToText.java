package Sandbox.NumberText;

/**
 * Created by jonathanevans on 10/12/2015.
 */
public class ConvertNumberToText implements IConvertNumberToText{
    private int      input   = 0;
    private String   result  = null;


    @Override
    public String convert(final int input) {
        if (input > -1) {
            this.input = input;

            switch(Integer.toString(input).length()) {
                case 1:
                    // Units
                    IUnit unit = new Unit(this.input);
                    this.result = unit.convertUnitAsText();
                    break;
                case 2:
                    // Tens
                    ITen ten = new Ten(this.input);
                    this.result = ten.convertAsText();
                    break;
                case 3:
                    // Hundreds
                    IHundred hundred = new Hundred(this.input);
                    this.result = hundred.convertAsText();
                    break;
                case 4:
                    // Thousands
                    IThousand thousand = new Thousand(this.input);
                    this.result = thousand.convertAsText();
                    break;
                case 5:
                    // Tens of Thousand
                    ITenThousand tenThousand = new TenThousand(this.input);
                    this.result = tenThousand.convertAsText();
                    break;
                case 6:
                    // Hundreds of Thousand
                    IHundredThousand hundredThousand = new HundredThousand(this.input);
                    this.result = hundredThousand.convertAsText();
                    break;
                default:
                    this.result = "Failed";
            }
        }

        return this.result;
    }


    @Override
    public int getInput() {
        return this.input;
    }


}
