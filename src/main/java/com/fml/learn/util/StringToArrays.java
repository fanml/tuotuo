package com.fml.learn.util;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Arrays;

import java.util.HashMap;
import java.util.List;

public class StringToArrays {
    public static List<String> stringToArrays(String str) {
        String newStr = str.substring(1, str.length() - 1);
        String[] split = newStr.split(",");
        List<String> strings = Arrays.asList(split);

        return strings;
    }

    public static void main(String[] args) {
        XSSFWorkbook xs = new XSSFWorkbook();
        System.out.println("12345");

    }
}
