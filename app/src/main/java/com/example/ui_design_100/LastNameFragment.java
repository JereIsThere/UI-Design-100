package com.example.ui_design_100;


import android.os.Bundle;
import android.view.View;

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

    //Minimum eight and maximum 10 characters, at least one uppercase letter, one lowercase letter, one number and one special character:
    //
    //"^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,10}$"

    @Override
    public void onRotationForward() {

    }

    @Override
    public void onRotationBackwards() {
        NavDirections action = LastNameFragmentDirections.actionLastNameFragmentToPhoneNumberFragment();
        Navigation.findNavController(getView()).navigate(action);
    }
}
