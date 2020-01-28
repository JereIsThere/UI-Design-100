package com.example.ui_design_100;

import android.view.MotionEvent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

public class RotationDetectorInitClass implements RotationGestureDetector.OnRotationGestureListener {
    private RotationGestureDetector mRotationDetector;
    private OnRotationListener onRotationListener;
    private Fragment fragment;

    RotationDetectorInitClass(Fragment fragment, OnRotationListener onRotationListener) {
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

    public interface OnRotationListener {

        /**
         * the main method for changing to the next fragment.
         * uses a rotation of a set amount of degrees to decide.
         */
        void onRotationForward();

        /**
         * the main method for changing to the previous fragment.
         * uses a rotation of a set amount of degrees to decide.
         */
        void onRotationBackwards();

    }
}
