package com.example.ui_design_100;

import android.view.MotionEvent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

public class RotationDetectorInitClass implements RotationGestureDetector.OnRotationGestureListener {
    private RotationGestureDetector mRotationDetector;
    private OnRotationListener onRotationListener;
    private int maxAngle = 60;
    private Fragment fragment;

    public RotationDetectorInitClass(Fragment fragment, OnRotationListener onRotationListener) {
        this.fragment = fragment;
        this.onRotationListener = onRotationListener;
        this.mRotationDetector = new RotationGestureDetector(this);

        fragment.getView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mRotationDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    @Override
    public void OnRotation(RotationGestureDetector rotationDetector) {
        float angle = rotationDetector.getAngle();
        if (angle < -this.maxAngle) {
            this.onRotationListener.onRotationForward();
        } else if (angle > this.maxAngle) {
            this.onRotationListener.onRotationBackwards();
        }
    }

    public void navigate(NavDirections action) {
        Navigation.findNavController(fragment.getView()).navigate(action);
    }

    public interface OnRotationListener {

        void onRotationForward();

        void onRotationBackwards();

    }
}
