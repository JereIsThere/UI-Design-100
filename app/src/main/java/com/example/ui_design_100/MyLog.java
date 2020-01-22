package com.example.ui_design_100;

import android.util.Log;

public class MyLog {

    public static void d(String message) {
        Log.d("myCustomTag", message);
    }

    public static void d(Object message) {
        Log.d("myCustomTag", message.toString());
    }

}
