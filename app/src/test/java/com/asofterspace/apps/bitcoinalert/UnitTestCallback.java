package com.asofterspace.apps.bitcoinalert;

import com.asofterspace.apps.bitcoinalert.api.RateCallback;

/**
 * {@inheritDoc}
 */
public class UnitTestCallback implements RateCallback {

    private String dataProvider;


    public UnitTestCallback(String dataProvider) {

        this.dataProvider = dataProvider;
    }

    /**
     * {@inheritDoc}
     */
    public void foundRate(double rate) {

        System.out.println("1 BTC = " + rate + " USD according to " + dataProvider);
    }

}
