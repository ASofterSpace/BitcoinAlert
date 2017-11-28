package com.asofterspace.apps.bitcoinalert.backend;

import com.asofterspace.apps.bitcoinalert.api.RateCallback;
import com.asofterspace.toolbox.web.JSON;
import com.asofterspace.toolbox.web.WebAccessdCallback;

/**
 * {@inheritDoc}
 */
public class CoinifyRateCallback implements WebAccessdCallback {

    private BackendController parent;

    private RateCallback forwardToCallback;


    public CoinifyRateCallback(BackendController parent, RateCallback callback) {

        this.parent = parent;

        this.forwardToCallback = callback;
    }

    /**
     * {@inheritDoc}
     */
    public void gotError() {
        // let's ignore errors?
    }

    /**
     * {@inheritDoc}
     */
    public void gotContent(String content) {

        try {
            JSON data = new JSON(content);

            JSON usd = data.get("data").get("USD");

            double rate = Double.parseDouble(usd.getString("buy").replace(",", "")) +
                    Double.parseDouble(usd.getString("sell").replace(",", ""));
            rate /= 2;

            forwardToCallback.foundRate(rate);

            parent.updateLastCoinifyRate(rate);

        } catch (Throwable t) {
            // TODO :: do not just ignore this!
        }
    }
}
