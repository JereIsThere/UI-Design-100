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
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class BirthdayFragment extends Fragment implements RotationDetectorInitClass.OnRotationListener {

    private RotationDetectorInitClass rotationDetectorInitClass;
    private NavDirections action;

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
        CalendarView calendarView = Objects.requireNonNull(getView()).findViewById(R.id.cv_birthday_calendar);
        inputView = getView().findViewById(R.id.et_birthday_input);
        textView = getView().findViewById(R.id.tv_birthday_info);

        //adding a textChangedListener, which works in unison with the dateChangedListener
        inputView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //gets the text from the input view
                String text = inputView.getText().toString();
                //converts the text into int
                number = ConverterAndInfoClass.convertToInt(text, 31);

                //makes sure the day can't be "the 0th"
                if (number == 0) {
                    number = 1;
                }

                updateTextView();
                rotationDetectorInitClass.setFullscreen();
            }
        });

        //sets the text for the first time to hide placeholders
        textView.setText(String.format(getResources().getString(R.string.str_tv_birthday_info), 1, "st", "January"));

        //adds a dateChangeListener, works parallel to the changeListener from the input field
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //converts the month into a string and saves it in the field
                setMonth(new DateFormatSymbols().getMonths()[month]);
                //saves the date as ints for later use
                setDate(month + 1 + "/" + dayOfMonth + "/" + year);

                updateTextView();
            }
        });

        //initializes the "date" field
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy", Locale.GERMAN);

        this.date = dateFormat.format(date);
    }

    /**
     * updates the textView from the {@link BirthdayFragment#date} and the {@link BirthdayFragment#month} fields, generates the ending for the number
     */
    private void updateTextView() {
        String end;
        //protects from a nullPointerException by using the current month for a month when the month is null
        String month = this.month == null ? new SimpleDateFormat("MMMM", Locale.GERMAN).format(new Date()) : this.month;
        this.month = month;

        //gets the last digit from the number and sets the ending
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

        //check if the input day is possible for the selected month
        LocalDate convertedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("M/d/yyyy"));
        convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));

        int lengthOfMonth = convertedDate.lengthOfMonth();

        if (number > lengthOfMonth) {
            number = lengthOfMonth;
        }

        //sets the text on the textView
        String sourceString = getResources().getString(R.string.str_tv_birthday_info);
        String text = String.format(sourceString, number, end, month);

        textView.setText(text);
    }

    @Override
    public void onRotationForward() {
        //checks if the number is just one digit, if yes it adds a "0"
        String text = (number >= 10 ? "" : "0") + number + " / " + month;

        //sets the value of the date in the datalist
        ((MainActivity) Objects.requireNonNull(getActivity())).addToDataList(text, MainActivity.BIRTHDAY_INDEX);

        action = BirthdayFragmentDirections.actionBirthdayFragmentToEndFragment();
        rotationDetectorInitClass.navigate(action);
    }

    @Override
    public void onRotationBackwards() {
        action = BirthdayFragmentDirections.actionBirthdayFragmentToAgeFragment();
        rotationDetectorInitClass.navigate(action);
    }

    /**
     * @param month the written month
     */
    private void setMonth(String month) {
        this.month = month;
    }

    /**
     * @param date the date as a string
     */
    private void setDate(String date) {
        this.date = date;
    }
}
