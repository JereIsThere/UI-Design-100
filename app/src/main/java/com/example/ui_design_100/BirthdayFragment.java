package com.example.ui_design_100;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;


/**
 * A simple {@link Fragment} subclass.
 */
public class BirthdayFragment extends Fragment implements RotationDetectorInitClass.OnRotationListener {

    private RotationDetectorInitClass rotationDetectorInitClass;
    private NavDirections action;

    private CalendarView calendarView;

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


    }

    @Override
    public void onRotationForward() {

    }

    @Override
    public void onRotationBackwards() {
        action = BirthdayFragmentDirections.actionBirthdayFragmentToAgeFragment();
        rotationDetectorInitClass.navigate(action);
    }
}
