package com.example.ui_design_100;

import android.view.MotionEvent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

public class RotationDetectorInitClass implements RotationGestureDetector.OnRotationGestureListener {
    private final RotationGestureDetector mRotationDetector;
    private final OnRotationListener onRotationListener;
    private final Fragment fragment;

    /**
     * Constructor to initialize the rotationListener.
     *
     * @param fragment           the current fragment in which this is implemented
     * @param onRotationListener something which implements the {@link OnRotationListener} Interface (typically the fragment itself)
     */
    RotationDetectorInitClass(Fragment fragment, OnRotationListener onRotationListener) {
        this.fragment = fragment;
        this.onRotationListener = onRotationListener;
        this.mRotationDetector = new RotationGestureDetector(this);

        fragment.requireView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mRotationDetector.onTouchEvent(event);
                return true;
            }
        });

        setFullscreen();
    }

    /**
     * a method to quickly toggle fullscreen to avoid hardcodes
     */
    void setFullscreen() {
        fragment.requireView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void OnRotation(RotationGestureDetector rotationDetector) {
        float angle = rotationDetector.getAngle();
        int maxAngle = 60;


        if (angle < -maxAngle) {
            this.onRotationListener.onRotationForward();
        } else if (angle > maxAngle) {
            this.onRotationListener.onRotationBackwards();
        }
    }

    /**
     * a shortcut for navigating between fragments using the navGraph.
     *
     * @param action the NavDirection, for example FirstFragmentDirections.actionFirstFragmentToSecondFragment()
     */
    void navigate(NavDirections action) {
        Navigation.findNavController(fragment.requireView()).navigate(action);
    }

    /**
     * Interface which defines the {@link #onRotationForward()} and the {@link #onRotationBackwards()} methods.
     */
    public interface OnRotationListener {

        /**
         * the main method for changing to the next fragment.
         * uses a rotation of a set amount of degrees to decide.
         * Typically has a call to add something to a datalist in it.
         */
        void onRotationForward();

        /**
         * the main method for changing to the previous fragment.
         * uses a rotation of a set amount of degrees to decide.
         */
        void onRotationBackwards();

    }
}
