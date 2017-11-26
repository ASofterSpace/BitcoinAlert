package com.asofterspace.apps.bitcoinalert.backend;

import com.asofterspace.apps.bitcoinalert.api.RateCallback;
import com.asofterspace.toolbox.web.WebAccessdCallback;

/**
 * {@inheritDoc}
 */
public class GoogleRateCallback implements WebAccessdCallback {

    private BackendController parent;

    private RateCallback forwardToCallback;


    public GoogleRateCallback(BackendController parent, RateCallback callback) {

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
            if (!content.contains("<em>1 BTC</em> = ")) {
                gotError();
            }

            content = content.substring(content.indexOf("<em>1 BTC</em> = ") + 17);
            content = content.substring(0, content.indexOf("<em>") - 1);

            content = content.replace(',', '.');

            double rate = Double.parseDouble(content);

            forwardToCallback.foundRate(rate);

            parent.updateLastGoogleRate(rate);

        } catch (Throwable t) {
            // TODO :: do not just ignore this!
        }
    }
}
