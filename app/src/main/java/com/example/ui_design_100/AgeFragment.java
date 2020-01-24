package com.example.ui_design_100;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import safety.com.br.android_shake_detector.core.ShakeCallback;
import safety.com.br.android_shake_detector.core.ShakeDetector;
import safety.com.br.android_shake_detector.core.ShakeOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgeFragment extends Fragment implements RotationDetectorInitClass.OnRotationListener {


    private NavDirections action;
    private RotationDetectorInitClass rotationDetectorInitClass;
    private TextView valView;


    public AgeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_age, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rotationDetectorInitClass = new RotationDetectorInitClass(this, this);
        valView = getView().findViewById(R.id.tv_age_valView);
        valView.setText("0");


    }

    @Override
    public void onRotationForward() {
        ((MainActivity) requireActivity()).addToDataList(valView.getText().toString(), MainActivity.AGE_INDEX);

        action = AgeFragmentDirections.actionAgeFragmentToBirthdayFragment();
        rotationDetectorInitClass.navigate(action);
    }

    @Override
    public void onRotationBackwards() {
        action = AgeFragmentDirections.actionAgeFragmentToLastNameFragment();
        rotationDetectorInitClass.navigate(action);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ShakeOptions options = new ShakeOptions()
                .background(true)
                .interval(750)
                .shakeCount(1)
                .sensibility(2.0f);

        ShakeDetector shakeDetector = new ShakeDetector(options).start(requireContext(), new ShakeCallback() {
            @Override
            public void onShake() {
                String str = String.format(getResources().getString(R.string.str_tv_age_value), ((int) ((Math.random() * 99) + 1)));
                valView.setText(str);
            }
        });

    }
}
