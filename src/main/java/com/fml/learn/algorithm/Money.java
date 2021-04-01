package com.fml.learn.algorithm;

import java.util.*;

public class Money {
    /**
     * 排列3/5
     *
     * @return
     */
    public static int three() {
        Random random = new Random();
        int aaa = random.nextInt(10);
        return aaa;
    }

    public static void main(String[] args) {
        //  System.out.println(String.valueOf(three()) + String.valueOf(three()) + String.valueOf(three()));
        //  System.out.println(String.valueOf(three()) + String.valueOf(three()) + String.valueOf(three()) + String.valueOf(three()) + String.valueOf(three()));
        System.out.println(redBlue());
    }

    public static String redBlue() {
        Random random = new Random();
        Set<Integer> redSet = new TreeSet<>();
        while (redSet.size() < 6) {
            int temp = random.nextInt(37);
            if (temp > 0) {
                redSet.add(temp);
            }
        }
        int blue = 0;
        while (blue == 0) {
            blue = random.nextInt(17);
        }
        Object[] redred = redSet.toArray();
        Arrays.sort(redred);
        String result = redSet.toString();
        result += "     ";
        result += blue;
        return result;
    }
}
