package com.example.reunion.Actions;

import android.view.View;

import org.hamcrest.Matcher;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.example.reunion.R;
public class DeleteViewAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.delete);
        button.performClick();
    }
}

