package com.example.ui_design_100;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment implements RotationDetectorInitClass.OnRotationListener {


    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    /**
     * sets the image depending on the UI-mode
     *
     * @param view               {@link View}
     * @param savedInstanceState {@link Bundle}
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RotationDetectorInitClass rotationDetectorInitClass = new RotationDetectorInitClass(this, this);

        ImageView imageView = Objects.requireNonNull(getView()).findViewById(R.id.iv_start_soosoffice);

        //sets the image depending on if the dark mode is on
        if (ConverterAndInfoClass.isDarkMode(Objects.requireNonNull(getContext()))) {
            Glide.with(getContext()).load(R.drawable.soosoffice_td).into(imageView);
        } else {
            Glide.with(getContext()).load(R.drawable.soosoffice_t).into(imageView);
        }
    }

    @Override
    public void onRotationForward() {
        long time = System.currentTimeMillis();

        ((MainActivity) Objects.requireNonNull(getActivity())).setStoredTime(time);

        Navigation.findNavController(Objects.requireNonNull(getView())).navigate(StartFragmentDirections.actionStartFragmentToFirstNameMainFragment());
    }

    @Override
    public void onRotationBackwards() {

    }
}
