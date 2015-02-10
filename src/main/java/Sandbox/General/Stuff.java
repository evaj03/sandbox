package Sandbox.General;

import com.google.common.base.Functions;
import com.google.common.collect.Collections2;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by jonathanevans on 28/01/2015.
 */
public class Stuff {

    public String fieldA;
    public String fieldB;
    public String fieldC;
    public String fieldD;

    public static void main(String[] args) throws IllegalAccessException {
        Object headerRecord = new Stuff();

        String[] fields = convertFieldNamesToStringArray(headerRecord);
    }


    private static String[] convertFieldNamesToStringArray(final Object record) throws IllegalAccessException {
        Map<String, String> properties = convertObjectToMap(record);

        Collection strings = Collections2.transform(properties.keySet(), Functions.toStringFunction());
        return Arrays.copyOf(strings.toArray(), strings.size(), String[].class);
    }


    private static Map<String, String> convertObjectToMap(final Object record) throws IllegalAccessException {
        Field[] fields = record.getClass().getFields();
        Map<String, String> properties = new TreeMap<String, String>();
        for (Field f : fields) {
            Object v = f.get(record);
            String value;
            if (v != null) {
                value = v.toString();
            } else {
                value = "";
            }
            properties.put(f.getName(), value);
        }
        return properties;
    }
}
