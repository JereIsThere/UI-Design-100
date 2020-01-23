package com.example.ui_design_100;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class LastNameFragment extends Fragment implements RotationDetectorInitClass.OnRotationListener {

    private RotationDetectorInitClass rotationDetectorInitClass;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.rotationDetectorInitClass = new RotationDetectorInitClass(this, this);

    }

    @Override
    public void onRotationForward() {

    }

    @Override
    public void onRotationBackwards() {
        NavDirections action = LastNameFragmentDirections.actionLastNameFragmentToPhoneNumberFragment();
        Navigation.findNavController(getView()).navigate(action);
    }


    public LastNameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_name, container, false);
    }
}
