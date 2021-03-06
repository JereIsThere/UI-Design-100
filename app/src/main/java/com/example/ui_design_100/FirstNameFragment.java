package com.example.ui_design_100;


import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstNameFragment extends Fragment implements RotationDetectorInitClass.OnRotationListener {

    private RotationDetectorInitClass rotationDetectorInitClass;

    private SeekBar seekBar;
    private TextView valView;
    private Resources res;
    private FlexboxLayout flexboxLayout;
    private TextView nameView;
    private ArrayList<String> valuesOfSpinners;
    private final String[] arraySpinner = new String[]{
            "b", " ", "c", "a", "f", "g", "d", "h", "e", "i", "k", "j", "m", "4", "n", "l", "p", "o", "r", "q", "t", "s", "v", "u", "z", "y", "x", "B", " ", "C", "A", "F", "G", "D", "H", "E", "I", "K", "J", "M", "5", "N", "L", "P", "O", "R", "Q", "T", "S", "V", "U", "Z", "Y", "X"
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        seekBar = view.findViewById(R.id.sb_firstName_dropdownCount);
        valView = view.findViewById(R.id.tv_firstName_sbVals);
        flexboxLayout = view.findViewById(R.id.ll_firstName_layout);
        rotationDetectorInitClass = new RotationDetectorInitClass(this, this);
        nameView = view.findViewById(R.id.tv_firstName_nameValue);
        valuesOfSpinners = new ArrayList<>();
        res = getResources();

        //sets the value of the tv for the first time to hide the placeholder
        updateTv_firstName_dropDownCount(0);

        //sets the changeListener for the seekbar
        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //sets tht text in the dropDownCount, sets the spinners to the value
                // they had before anything changed and adds new ones
                updateTv_firstName_dropDownCount(progress);
                setSpinners();
                updateNameView();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //sets the name for the first time to hide the placeholder
        String nameInit = String.format(res.getString(R.string.str_tv_firstName_value), "");
        nameView.setText(nameInit);
    }

    /**
     * Sets the spinners to the values saved in the {@link FirstNameFragment#valuesOfSpinners} ArrayList
     */
    private void setSpinners() {
        int min = Integer.min(valuesOfSpinners.size(), seekBar.getProgress());

        for (int i = 0; i < min; i++) {

            Spinner spinner = ((Spinner) flexboxLayout.getFlexItemAt(i));

            for (int j = 0; j < arraySpinner.length; j++) {
                if (valuesOfSpinners.get(i).equals(arraySpinner[j])) {
                    spinner.setSelection(j);
                    break;
                }
            }
        }

    }

    /**
     * updates the dropDownCount textView and creates new spinners in the {@link FlexboxLayout}
     *
     * @param amount the amount of spinners to create
     */
    private void updateTv_firstName_dropDownCount(int amount) {
        String seekBarText = String.format(res.getString(R.string.str_firstName_sbInfo), this.seekBar.getProgress());
        this.valView.setText(seekBarText);

        if (amount == 0) {
            flexboxLayout.setVisibility(View.GONE);
        } else {
            flexboxLayout.removeAllViews();
            flexboxLayout.setVisibility(View.VISIBLE);

            for (int j = 0; j < amount; j++) {
                Spinner s = createSpinner();
                flexboxLayout.addView(s);
            }
        }
    }

    /**
     * creates an amount of {@link Spinner} in the {@link FlexboxLayout}
     */
    private Spinner createSpinner() {
        Spinner s = new Spinner(getContext());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setMinimumWidth(100);
        s.setMinimumHeight(100);
        s.setDropDownWidth(200);
        s.setTextAlignment(Spinner.TEXT_ALIGNMENT_CENTER);
        s.setAdapter(adapter);

        //creates listeners for every spinner
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateNameView();
                rotationDetectorInitClass.setFullscreen();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updateNameView();
                rotationDetectorInitClass.setFullscreen();
            }
        });
        return s;
    }

    /**
     * updates the name textView placeholder
     */
    private void updateNameView() {
        String insertedText = getName();

        if (seekBar.getProgress() == 0) {
            insertedText = "";
        }

        String text = String.format(res.getString(R.string.str_tv_firstName_value), insertedText);

        nameView.setText(text);
    }

    /**
     * creates the name all the spinners spell and returns it
     *
     * @return the name all the spinners spell
     */
    private String getName() {
        StringBuilder name = new StringBuilder();
        valuesOfSpinners.clear();

        for (int i = 0; i < this.flexboxLayout.getFlexItemCount(); i++) {
            Spinner spinner = ((Spinner) this.flexboxLayout.getFlexItemAt(i));
            String str = (spinner.getSelectedItem()).toString();
            name.append(str);
            valuesOfSpinners.add(str);
        }

        return name.toString();
    }

    @Override
    public void onRotationForward() {
        ((MainActivity) requireActivity()).addToDataList(getName(), MainActivity.FIRST_NAME_INDEX);

        NavDirections action = FirstNameFragmentDirections.actionFirstNameMainFragmentToPhoneNumberFragment();

        Navigation.findNavController(Objects.requireNonNull(getView())).navigate(action);
    }

    @Override
    public void onRotationBackwards() {

    }
}
