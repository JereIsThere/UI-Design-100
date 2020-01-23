package com.example.ui_design_100;


import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.flexbox.FlexboxLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstNameFragment extends Fragment {

    private SeekBar seekBar;
    private TextView infoView;
    private TextView valView;
    private Resources res;
    private FlexboxLayout flexboxLayout;
    private Button nextButton;

    public FirstNameFragment() {
        // Required empty public constructor
    }


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
        this.infoView = view.findViewById(R.id.tv_firstName_Info);
        this.valView = view.findViewById(R.id.tv_firstName_sbVals);
        this.flexboxLayout = view.findViewById(R.id.ll_firstName_layout);
        this.nextButton = view.findViewById(R.id.btn_firstName_next);
        res = getResources();

        updateTv_firstName_dropDownCount(0);

        this.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) requireActivity()).addToDataList(getName(), MainActivity.FIRST_NAME_INDEX);

                NavDirections action = FirstNameFragmentDirections.actionFirstNameMainFragmentToPhoneNumberFragment();
                Navigation.findNavController(v).navigate(action);
            }
        });

        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTv_firstName_dropDownCount(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void updateTv_firstName_dropDownCount(int amount) {
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
        String[] arraySpinner = new String[]{
                "b", " ", "c", "a", "f", "g", "d", "h", "e", "i", "k", "j", "m", "4", "n", "l", "p", "o", "r", "q", "t", "s", "v", "u", "z", "y", "x", "B", " ", "C", "A", "F", "G", "D", "H", "E", "I", "K", "J", "M", "4", "N", "L", "P", "O", "R", "Q", "T", "S", "V", "U", "Z", "Y", "X"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setMinimumWidth(100);
        s.setMinimumHeight(100);
        s.setDropDownWidth(200);
        s.setTextAlignment(Spinner.TEXT_ALIGNMENT_CENTER);
        s.setAdapter(adapter);

        return s;
    }

    private String getName() {
        String name = "";
        boolean empty = true;

        for (int i = 0; i < this.flexboxLayout.getFlexItemCount(); i++) {
            name += ((Spinner) this.flexboxLayout.getFlexItemAt(i)).getSelectedItem();
        }

        return name;
    }

}
