package com.calculator.cscalculator;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Pranav on 12/2/2015.
 */
public class CalculatorView implements CalculatorViewInterface {
    private TextView view;

    public CalculatorView(TextView view) {
        this.view = view;
    }
    @Override
    public void display(String val) {
        view.setText(val);
    }

    @Override
    public void invalid() {
        //view.setTextColor(800000);
        Snackbar.make(view, "Error.", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).setActionTextColor(Color.RED).show();
    }
}
