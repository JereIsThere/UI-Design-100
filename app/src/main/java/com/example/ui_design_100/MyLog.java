package com.example.ui_design_100;

import android.util.Log;

/**
 * a class to call {@link Log#d} with the predefined tag "myCustomTag"
 */
class MyLog {

    public static void d(String message) {
        Log.d("myCustomTag", message);
    }

    public static void d(Object message) {
        Log.d("myCustomTag", message.toString());
    }

}
