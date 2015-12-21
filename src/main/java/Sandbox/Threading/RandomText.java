package Sandbox.Threading;

import java.util.Random;

/**
 * Created by jonathanevans on 17/12/2015.
 */
public class RandomText implements IRandomText {
    private static final String CHARS  = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";
    private static Random       random = new Random();


    public String getText(final int length) {
        if (length > 0) {
            return randomString(length);
        }

        return null;
    }


    private String randomString(final int length) {
        StringBuilder sb = new StringBuilder(length);

        for( int i = 0; i < length; i++ ) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }

        return sb.toString();
    }
}
