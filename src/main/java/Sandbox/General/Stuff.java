package Sandbox.General;

//import com.google.common.base.Functions;
//import com.google.common.collect.Collections2;

/**
 * Created by jonathanevans on 28/01/2015.
 */
public class Stuff {

    public String fieldA;
    public String fieldB;
    public String fieldC;
    public String fieldD;

    //public static void main(String[] args) throws IllegalAccessException {
        //Object headerRecord = new Stuff();

        //String[] fields = convertFieldNamesToStringArray(headerRecord);


        //datey();

        //dateInterval();

//    private static String[] convertFieldNamesToStringArray(final Object record) throws IllegalAccessException {
//        Map<String, String> properties = convertObjectToMap(record);
//
//        Collection strings = Collections2.transform(properties.keySet(), Functions.toStringFunction());
//        return Arrays.copyOf(strings.toArray(), strings.size(), String[].class);
//    }


//    private static Map<String, String> convertObjectToMap(final Object record) throws IllegalAccessException {
//        Field[] fields = record.getClass().getFields();
//        Map<String, String> properties = new TreeMap<String, String>();
//        for (Field f : fields) {
//            Object v = f.get(record);
//            String value;
//            if (v != null) {
//                value = v.toString();
//            } else {
//                value = "";
//            }
//            properties.put(f.getName(), value);
//        }
//        return properties;
//    }


//    private static void datey() {
//        int minAge = 40;
//        int maxAge = 44;
//
//        Date today = new Date();
//        Calendar oldestBornOn = Calendar.getInstance();
//        oldestBornOn.add(Calendar.YEAR, -(maxAge + 1));
//        oldestBornOn.add(Calendar.DATE, -1);
//        Calendar youngestBornOn = Calendar.getInstance();
//        youngestBornOn.add(Calendar.YEAR, - minAge);
//
//        Date y = youngestBornOn.getTime();
//        Date o = oldestBornOn.getTime();
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(today);
//        cal.add(Calendar.DAY_OF_YEAR, 30);
//
//        Date r = cal.getTime();
//    }


//    private static void dateInterval() {
//        int              recallInterval = 120;
//        SimpleDateFormat ft             = new SimpleDateFormat ("yyyy-MM-dd");
//        String           input          = "2014-06-25";
//        Date             aDate;
//
//        try {
//            aDate = ft.parse(input);
//
//            Date bob1 = addDaysToDate(recallInterval, aDate);
//            Date bob2 = addDaysToDate2(recallInterval, aDate);
//
//            System.out.println("Date in MS: " + bob1.toString());
//            System.out.println("Date in cal: " + bob2.toString());
//        } catch (ParseException e) {
//            System.out.println("Unparseable using " + ft);
//        }
//    }


//    private static Date addDaysToDate(final int numberOfDays, final Date date) {
//        long NUMBER_OF_MILLISECONDS_IN_ONE_DAY = 1000 * 60 * 60 * 24;
//
//        long totalMs = NUMBER_OF_MILLISECONDS_IN_ONE_DAY * numberOfDays;
//
//        Date newDate = new Date(date.getTime() + totalMs);
//
//        return newDate;
//    }


//    private static Date addDaysToDate2(final int numberOfDays, final Date date) {
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        c.add(Calendar.DATE, numberOfDays);
//
//        Date newDate = c.getTime();
//
//        return newDate;
//    }
}
