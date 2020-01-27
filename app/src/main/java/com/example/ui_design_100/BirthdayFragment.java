package com.example.ui_design_100;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class BirthdayFragment extends Fragment implements RotationDetectorInitClass.OnRotationListener {

    private RotationDetectorInitClass rotationDetectorInitClass;
    private NavDirections action;

    private CalendarView calendarView;
    private EditText inputView;
    private TextView textView;

    private int number = 1;
    private String month;
    private String date;

    public BirthdayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_birthday, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rotationDetectorInitClass = new RotationDetectorInitClass(this, this);
        calendarView = getView().findViewById(R.id.cv_birthday_calendar);
        inputView = getView().findViewById(R.id.et_birtday_input);
        textView = getView().findViewById(R.id.tv_birthday_info);

        inputView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = inputView.getText().toString();
                number = ConverterClass.convertToInt(text, 31);

                if (number == 0) {
                    number = 1;
                }

                updateTextView();
            }
        });

        textView.setText(String.format(getResources().getString(R.string.str_tv_birthday_info), 1, "st", "january"));

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                setMonth(new DateFormatSymbols().getMonths()[month]);
                setDate(month + 1 + "/" + dayOfMonth + "/" + year);
                updateTextView();
            }
        });


        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy", Locale.GERMAN);
        String strDate = dateFormat.format(date);

        this.date = strDate;
        MyLog.d(this.date);

    }

    private void updateTextView() {
        String end;
        String month = this.month == null ? new SimpleDateFormat("MMMM", Locale.UK).format(new Date()) : this.month;

        String numberString = String.valueOf(number);
        int lastNumber = Integer.valueOf(String.valueOf(numberString.charAt(numberString.length() - 1)));

        switch (lastNumber) {
            case 1:
                end = "st";
                break;
            case 2:
                end = "nd";
                break;
            case 3:
                end = "rd";
                break;
            default:
                end = "th";
        }

        LocalDate convertedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("M/d/yyyy"));
        convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));

        int lengthOfMonth = convertedDate.lengthOfMonth();

        if (number > lengthOfMonth) {
            number = lengthOfMonth;
        }

        String sourceString = getResources().getString(R.string.str_tv_birthday_info);
        String text = String.format(sourceString, number, end, month);

        textView.setText(text);
    }

    @Override
    public void onRotationForward() {
    }

    @Override
    public void onRotationBackwards() {
        action = BirthdayFragmentDirections.actionBirthdayFragmentToAgeFragment();
        rotationDetectorInitClass.navigate(action);
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
