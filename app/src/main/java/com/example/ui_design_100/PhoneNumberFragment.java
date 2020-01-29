package com.example.ui_design_100;


import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneNumberFragment extends Fragment implements RotationDetectorInitClass.OnRotationListener {

    private TextView valueView;
    private SeekBar seekBar1;
    private SeekBar seekBar2;

    public PhoneNumberFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_number, container, false);
    }

    /**
     * adds changeListeners for the seekbars, declares the fields
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.valueView = view.findViewById(R.id.tv_phoneNumber_value);
        this.seekBar1 = view.findViewById(R.id.sb_phoneNumber_inputSlider1);
        this.seekBar2 = view.findViewById(R.id.sb_phoneNumber_inputSlider2);
        RotationDetectorInitClass rotationDetectorInitClass = new RotationDetectorInitClass(this, this);

        updateValTv();

        //adds a changeListener to the seekbars to update the valTv
        this.seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateValTv();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateValTv();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    /**
     * updates the value TextView with the number from the {@link PhoneNumberFragment#getNumber()} method.
     */
    private void updateValTv() {
        Resources res = getResources();
        String newText = String.format(res.getString(R.string.str_tv_phoneNumber_sliderValue), this.seekBar1.getProgress(), this.seekBar2.getProgress());
        this.valueView.setText(newText);
    }

    /**
     * generates a long by combining the two numbers returned by the seekbar
     *
     * @return a long
     */
    private long getNumber() {
        String str = this.seekBar1.getProgress() + "" + this.seekBar2.getProgress();
        return Long.valueOf(str);
    }

    @Override
    public void onRotationForward() {

        //adds the number to the datalist, after adding a 0 to the start
        ((MainActivity) requireActivity()).addToDataList("0" + getNumber(), MainActivity.PHONE_NUMBER_INDEX);

        NavDirections action = PhoneNumberFragmentDirections.actionPhoneNumberFragmentToLastNameFragment();
        Navigation.findNavController(Objects.requireNonNull(getView())).navigate(action);
    }

    @Override
    public void onRotationBackwards() {
        NavDirections action = PhoneNumberFragmentDirections.actionPhoneNumberFragmentToFirstNameMainFragment();
        Navigation.findNavController(Objects.requireNonNull(getView())).navigate(action);
    }
}
