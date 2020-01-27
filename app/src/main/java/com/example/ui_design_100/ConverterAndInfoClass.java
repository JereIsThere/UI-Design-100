package com.example.ui_design_100;

import android.content.Context;
import android.content.res.Configuration;

import java.util.ArrayList;

public class ConverterAndInfoClass {

    public static final String[] units = {
            "", "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };
    public static final String[] tens = {
            "",        // 0
            "",        // 1
            "twenty",  // 2
            "thirty",  // 3
            "forty",   // 4
            "fifty",   // 5
            "sixty",   // 6
            "seventy", // 7
            "eighty",  // 8
            "ninety"   // 9
    };
    private ArrayList<String> str_number;

    public static String convertToString(final int n) {
        if (n < 0) {
            return "minus " + convertToString(-n);
        }

        if (n < 20) {
            return units[n];
        }

        if (n < 100) {
            return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
        }

        if (n < 1000) {
            return units[n / 100] + " hundred" + ((n % 100 != 0) ? " " : "") + convertToString(n % 100);
        }

        if (n < 1000000) {
            return convertToString(n / 1000) + " thousand" + ((n % 1000 != 0) ? " " : "") + convertToString(n % 1000);
        }

        if (n < 1000000000) {
            return convertToString(n / 1000000) + " million" + ((n % 1000000 != 0) ? " " : "") + convertToString(n % 1000000);
        }

        return convertToString(n / 1000000000) + " billion" + ((n % 1000000000 != 0) ? " " : "") + convertToString(n % 1000000000);
    }

    public static int convertToInt(String str, int limit) {
        boolean defaultVal = true;
        int number = 1;

        for (int i = 0; i <= limit; i++) {
            if (convertToString(i).equals(str)) {
                number = i;
                defaultVal = false;
                break;
            }
        }
        if (!defaultVal) {
            return number;
        } else {
            for (int i = -limit; i < 0; i++) {
                if (convertToString(i).equals(str)) {
                    number = i;
                    break;
                }
            }
        }

        return number;
    }

    public static boolean isDarkMode(Context context) {
        int nightModeFlags =
                context.getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                return true;

            case Configuration.UI_MODE_NIGHT_NO:
            default:
                return false;
        }
    }

}
