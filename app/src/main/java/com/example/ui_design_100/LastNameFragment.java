package com.example.ui_design_100;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import java.util.Objects;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class LastNameFragment extends Fragment implements RotationDetectorInitClass.OnRotationListener {

    private RotationDetectorInitClass rotationDetectorInitClass;
    private NavDirections action;

    private final Pattern p = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])(?=.*\\s)[A-Za-z\\d\\s@$!%*?&]{12,13}$");
    private TextView regexCheckView;
    private EditText inputField;
    private boolean b = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rotationDetectorInitClass = new RotationDetectorInitClass(this, this);
        regexCheckView = Objects.requireNonNull(getView()).findViewById(R.id.tv_lastName_regexCheck);
        inputField = getView().findViewById(R.id.et_lastName_input);

        //adds a textChangedListener to set b and change the textColor
        inputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //sets b
                String text = inputField.getText().toString();
                b = Pattern.matches(p.pattern(), text);

                //changes the textColor
                if (b) {
                    regexCheckView.setTextColor(getResources().getColor(R.color.colorAccent, Objects.requireNonNull(getActivity()).getTheme()));

                } else {
                    regexCheckView.setTextColor(getResources().getColor(R.color.colorAccent2, Objects.requireNonNull(getActivity()).getTheme()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                rotationDetectorInitClass.setFullscreen();
            }
        });
    }

    @Override
    public void onRotationForward() {
        //adds the name to the datalist
        ((MainActivity) requireActivity()).addToDataList(inputField.getText().toString(), MainActivity.LAST_NAME_INDEX);

        //validation for changing to the next fragment
        if (b) {
            action = LastNameFragmentDirections.actionLastNameFragmentToAgeFragment();
            rotationDetectorInitClass.navigate(action);
        }
    }

    @Override
    public void onRotationBackwards() {
        action = LastNameFragmentDirections.actionLastNameFragmentToPhoneNumberFragment();
        rotationDetectorInitClass.navigate(action);
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
