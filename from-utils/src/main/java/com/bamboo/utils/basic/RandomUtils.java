package com.bamboo.utils.basic;

import java.util.Random;

/**
 * Created by admin on 2017/3/17.
 */
public class RandomUtils {

    /**
     * String is generated randomly by specifying the length of the string
     * @param num
     * @return
     */
    public  static String getRandomCharByLength(int num) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
