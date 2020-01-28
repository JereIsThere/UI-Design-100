package com.example.ui_design_100;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int FIRST_NAME_INDEX = 0;
    public static final int PHONE_NUMBER_INDEX = 1;
    public static final int LAST_NAME_INDEX = 2;
    public static final int AGE_INDEX = 3;
    public static final int BIRTHDAY_INDEX = 4;
    private long storedTime;

    private List<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    public long getStoredTime() {
        return storedTime;
    }

    public void setStoredTime(long storedTime) {
        this.storedTime = storedTime;
    }

    public void addToDataList(String var, int index) {
        if (this.dataList.size() <= index) {
            this.dataList.add(index, var);
        } else {
            this.dataList.set(index, var);
        }
    }

}
