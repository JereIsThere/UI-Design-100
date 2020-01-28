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
    private String[] arraySpinner = new String[]{
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

        this.seekBar = view.findViewById(R.id.sb_firstName_dropdownCount);
        this.valView = view.findViewById(R.id.tv_firstName_sbVals);
        this.flexboxLayout = view.findViewById(R.id.ll_firstName_layout);
        this.rotationDetectorInitClass = new RotationDetectorInitClass(this, this);
        this.nameView = view.findViewById(R.id.tv_firstName_nameValue);
        this.valuesOfSpinners = new ArrayList<>();
        res = getResources();

        updateTv_firstName_dropDownCount(0);

        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
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

        String nameInit = String.format(res.getString(R.string.str_tv_firstName_value), "");
        nameView.setText(nameInit);
    }

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

    private Spinner createSpinner() {
        Spinner s = new Spinner(getContext());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setMinimumWidth(100);
        s.setMinimumHeight(100);
        s.setDropDownWidth(200);
        s.setTextAlignment(Spinner.TEXT_ALIGNMENT_CENTER);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateNameView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updateNameView();
            }
        });
        return s;
    }

    private void updateNameView() {
        String insertedText = getName();

        if (seekBar.getProgress() == 0) {
            insertedText = "";
        }

        String text = String.format(res.getString(R.string.str_tv_firstName_value), insertedText);

        nameView.setText(text);
    }

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

        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void onRotationBackwards() {

    }
}
