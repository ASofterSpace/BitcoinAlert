package com.asofterspace.apps.bitcoinalert.backend;

import com.asofterspace.apps.bitcoinalert.api.RateCallback;
import com.asofterspace.toolbox.web.JSON;
import com.asofterspace.toolbox.web.WebAccessdCallback;

/**
 * {@inheritDoc}
 */
public class BlockchainInfoRateCallback implements WebAccessdCallback {

    private BackendController parent;

    private RateCallback forwardToCallback;


    public BlockchainInfoRateCallback(BackendController parent, RateCallback callback) {

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

            double rate = Double.parseDouble(data.get("USD").getString("15m"));

            forwardToCallback.foundRate(rate);

            parent.updateLastBlockchainInfoRate(rate);

        } catch (Throwable t) {
            // TODO :: do not just ignore this!
        }
    }
}
