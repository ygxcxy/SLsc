package cn.scj.util;

import java.util.Random;

public class MathUtil {

    public static String getRandom1() {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < 18; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }
}
