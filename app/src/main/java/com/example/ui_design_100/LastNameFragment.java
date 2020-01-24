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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class LastNameFragment extends Fragment implements RotationDetectorInitClass.OnRotationListener {

    // /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])(?=.*\s)[A-Za-z\d\s@$!%*?&]{12,13}$/g

    private RotationDetectorInitClass rotationDetectorInitClass;
    private NavDirections action;
    private Pattern p = Pattern.compile("/^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])(?=.*\\s)[A-Za-z\\d\\s@$!%*?&]{12,13}$/g");
    private TextView regexCheckView;
    private EditText inputField;
    private boolean b = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.rotationDetectorInitClass = new RotationDetectorInitClass(this, this);
        regexCheckView = getView().findViewById(R.id.tv_lastName_regexCheck);
        inputField = getView().findViewById(R.id.et_lastName_input);

        inputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = inputField.getText().toString();
                Matcher m = p.matcher(text);
                b = Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])(?=.*\\s)[A-Za-z\\d\\s@$!%*?&]{12,14}", text);

                MyLog.d(b + " " + text + " " + text.length());


                if (b) {
                    regexCheckView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                } else {
                    regexCheckView.setTextColor(getResources().getColor(R.color.colorAccent));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onRotationForward() {
        ((MainActivity) requireActivity()).addToDataList(inputField.getText().toString(), MainActivity.LAST_NAME_INDEX);
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
