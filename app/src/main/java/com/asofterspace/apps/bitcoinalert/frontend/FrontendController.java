package com.asofterspace.apps.bitcoinalert.frontend;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asofterspace.apps.bitcoinalert.R;
import com.asofterspace.apps.bitcoinalert.backend.BackendController;

/**
 * This class controls the frontend of BitcoinAlert
 *
 * @author Moya (a softer space, 2017)
 */
public class FrontendController {

    private AppCompatActivity parent;

    private BackendController backCtrl;


    public FrontendController(AppCompatActivity parent, BackendController backCtrl) {

        this.parent = parent;

        this.backCtrl = backCtrl;

        // DEBUG updateLabels();

        addRefreshButtonListener();
    }

    private void updateLabels() {

        TextView btcRateOverall = (TextView) parent.findViewById(R.id.btcRateOverall);
        backCtrl.getOverallRate(new ButtonClickCallback(parent, btcRateOverall));

        TextView btcRateGoogle = (TextView) parent.findViewById(R.id.btcRateGoogle);
        backCtrl.getGoogleRate(new ButtonClickCallback(parent, btcRateGoogle));

        TextView btcRateBlockchain = (TextView) parent.findViewById(R.id.btcRateBlockchain);
        backCtrl.getBlockchainInfoRate(new ButtonClickCallback(parent, btcRateBlockchain));

        TextView btcRateCoindesk = (TextView) parent.findViewById(R.id.btcRateCoindesk);
        backCtrl.getCoindeskRate(new ButtonClickCallback(parent, btcRateCoindesk));

        TextView btcRateCoinify = (TextView) parent.findViewById(R.id.btcRateCoinify);
        backCtrl.getCoinifyRate(new ButtonClickCallback(parent, btcRateCoinify));
    }

    private void addRefreshButtonListener() {

        Button refreshButton = (Button) parent.findViewById(R.id.refreshBtn);

        refreshButton.setOnClickListener(
            new View.OnClickListener() {
                public void onClick(View v) {
                    updateLabels();
                }
            }
        );
    }

}
