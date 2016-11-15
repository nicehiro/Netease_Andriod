package org.nicehiro.ormtest;

import java.util.Random;

/**
 * Created by hiro on 16-11-14.
 */

public class Util {

    //获得给定长度的随机字符串
    public static String RandomString (int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();

        for (int i=0; i<length; i++) {
            int number = random.nextInt(62);
            buffer.append(str.charAt(number));
        }

        return buffer.toString();
    }

    //获得给定长度的数字
    public static String RandomNumber (int length) {
        String str = "0123456789";
        char[] rands = new char[length];

        for (int i=0; i<length; i++) {
            int rand = (int) (Math.random() * 10);
            rands[i] = str.charAt(rand);
        }

        return new String(rands);
    }
}
