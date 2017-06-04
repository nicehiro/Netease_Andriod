package org.nicehiro.shopcar;

import java.util.Random;

/**
 * Created by hiro on 17-6-2.
 */

public class Util {

    public static int getRandomNumber(int length) {
        char[] numbers = new char[length];
        int rand = (int) ((Math.random() * 10000) % length);
        return rand;
    }

    public static byte[] getRandomByteArray(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();

        for (int i=0; i<length; i++) {
            int number = random.nextInt(62);
            stringBuffer = stringBuffer.append(str.charAt(number));
        }

        return stringBuffer.toString().getBytes();
    }
}
