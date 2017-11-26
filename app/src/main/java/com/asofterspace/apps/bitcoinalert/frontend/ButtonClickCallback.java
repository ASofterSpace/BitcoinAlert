package com.asofterspace.apps.bitcoinalert.frontend;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.asofterspace.apps.bitcoinalert.api.RateCallback;

/**
 * {@inheritDoc}
 */
public class ButtonClickCallback implements RateCallback {

    private AppCompatActivity parent;

    private TextView outputTextView;


    public ButtonClickCallback(AppCompatActivity parent, TextView outputTextView) {

        this.parent = parent;

        this.outputTextView = outputTextView;
    }

    /**
     * {@inheritDoc}
     */
    public void foundRate(final double rate) {

        parent.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                outputTextView.setText("1 BTC = " + (Math.round(rate * 100) / 100) + " USD");
            }
        });
    }

}
