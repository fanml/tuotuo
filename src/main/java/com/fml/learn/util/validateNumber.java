package com.fml.learn.util;

public class validateNumber {
    public static boolean validateNumber(String str) {


        // 说明一下的是该正则只能识别4位小数；如果不限制小数位数的话，写成[+-]?[0-9]+(\\.[0-9]+)?就可以了

        return str.matches("[0-9]{1,4}+(\\.[0-9]{1})?");


    }
    public static void main(String[] args) {
        System.out.println(validateNumber("11111.1"));
    }
}
