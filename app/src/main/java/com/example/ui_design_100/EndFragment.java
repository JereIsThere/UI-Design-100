package com.example.ui_design_100;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class EndFragment extends Fragment {

    private TextView textView;
    private TextView timeView;

    public EndFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_end, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = Objects.requireNonNull(getView()).findViewById(R.id.tv_end_vals);
        timeView = getView().findViewById(R.id.tv_end_time);

        setTextView();

        setTimeView();
    }

    private void setTimeView() {
        long startTime = ((MainActivity) Objects.requireNonNull(getActivity())).getStoredTime();
        long afterTime = System.currentTimeMillis();

        long time = (afterTime - startTime);
        long seconds = time / 1000;

        String unit;

        if (seconds > 60 * 60) {
            time = seconds / (60 * 60);
            unit = "h";
        } else if (seconds > 60) {
            time = seconds / 60;
            unit = "min";
        } else {
            time = seconds;
            unit = "s";
        }

        String originalString = getResources().getString(R.string.str_tv_end_time);
        String formattedString = String.format(originalString, time, unit);

        timeView.setText(formattedString);
    }

    /**
     * Gets all the values from the datalist in {@link MainActivity} and displays them
     */
    private void setTextView() {
        MainActivity mainActivity = ((MainActivity) getActivity());

        String firstName = Objects.requireNonNull(mainActivity).getFromDataList(MainActivity.FIRST_NAME_INDEX);
        String phoneNumber = mainActivity.getFromDataList(MainActivity.PHONE_NUMBER_INDEX);
        String lastName = mainActivity.getFromDataList(MainActivity.LAST_NAME_INDEX);
        String age = mainActivity.getFromDataList(MainActivity.AGE_INDEX);
        String birthday = mainActivity.getFromDataList(MainActivity.BIRTHDAY_INDEX);

        String originalText = getResources().getString(R.string.str_tv_end_val);

        String text = String.format(originalText, firstName, phoneNumber, lastName, age, birthday);

        textView.setText(text);
    }
}
