package com.example.ui_design_100;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static MainActivity instance;
    private final int FIRST_NAME_INDEX = 0;
    private final int PHONE_NUMBER_INDEX = 1;
    private ArrayList<String> dataList;

    public static MainActivity getInstance() {
        if (instance == null) {
            instance = new MainActivity();
        }
        return instance;
    }

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

    public void addToDataList(Object var, int index) {

        if (this.dataList.size() < index) {
            this.dataList.add(index, var.toString());
        } else {
            this.dataList.set(index, var.toString());
        }
    }
}
