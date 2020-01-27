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


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment implements RotationDetectorInitClass.OnRotationListener {


    private RotationDetectorInitClass rotationDetectorInitClass;

    private ImageView imageView;

    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rotationDetectorInitClass = new RotationDetectorInitClass(this, this);

        imageView = getView().findViewById(R.id.iv_start_soosoffice);

        if (ConverterAndInfoClass.isDarkMode(getContext())) {
            Glide.with(getContext()).load(R.drawable.soosoffice_td).into(imageView);
        } else {
            Glide.with(getContext()).load(R.drawable.soosoffice_t).into(imageView);
        }
    }

    @Override
    public void onRotationForward() {
        //right fragment nav
        Navigation.findNavController(getView()).navigate(StartFragmentDirections.actionStartFragmentToFirstNameMainFragment());
    }

    @Override
    public void onRotationBackwards() {

    }
}
