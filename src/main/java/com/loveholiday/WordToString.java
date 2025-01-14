package com.loveholiday;

import java.util.Arrays;

public class WordToString {

    public static String wordToStringConvert(int num) {
        StringBuilder stringBuilder = new StringBuilder();
//        System.out.println(num);
//        if (num == 0) {
//            return "zero";
//        }
//        if (num == 100000) {
//            return "one hundred thousand";
//        }
        if (num / 1000 > 0) {
//            System.out.println(units[num / 1000]);
            stringBuilder.append(units[num / 1000]);
            stringBuilder.append(" thousand");
            wordToStringConvert(num / 1000);
            num %= 1000;
            if(num > 0) {
                if (num < 100) {
                    stringBuilder.append(" and ");
                } else {
                    stringBuilder.append(", ");
                }
            }
        } if (num / 100 > 0) {
//            System.out.println(units[num/100]);


            stringBuilder.append(units[num/100]);
            stringBuilder.append(" hundred");
            num %= 100;
            if(num > 0) {
                stringBuilder.append(" and ");
            }
        }
            if (num / 10 > 0) {
//                System.out.println(units[temp/10]);
                stringBuilder.append(tens[num/10]);
                stringBuilder.append("-").append(units[num % 10]);

            } else {
//                if(num > 0) {
//                    stringBuilder.append(" and ");
//                }
                stringBuilder.append(units[num % 10]);
            }

        return stringBuilder.toString().trim();
    }
    private static final String[] units = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
    private static final String[] tens = { "", "", "twenty","thirty", "forty", "fifty", "sixty","seventy", "eighty", "ninety"};
    public static void main(String[] args) {

        Arrays.asList(52, 1000, 101, 352, 12300, 12055, 12345).stream().forEach(i -> {
            System.out.println(wordToStringConvert(i));
        });
    }
}
