package com.asofterspace.apps.bitcoinalert.api;

/**
 * This interface describes a generic callback for getting an exchange rate
 *
 * @author Moya (a softer space, 2017)
 */
public interface RateCallback {

    /**
     * This method is called once the exchange rate has been found
     * @param rate  The exchange rate that has been found
     */
    void foundRate(double rate);

}
