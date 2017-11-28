package com.asofterspace.apps.bitcoinalert.backend;

import com.asofterspace.apps.bitcoinalert.api.RateCallback;
import com.asofterspace.toolbox.web.WebAccessdCallback;
import com.asofterspace.toolbox.web.WebAccessor;

/**
 * This class controls the backend of BitcoinAlert
 *
 * @author Moya (a softer space, 2017)
 */
public class BackendController {

    private Double lastGoogleRate;

    private Double lastBlockchainInfoRate;

    private Double lastCoindeskRate;

    private Double lastCoinifyRate;

    private RateCallback overallCallback;


    public BackendController() {

        lastGoogleRate = null;

        lastBlockchainInfoRate = null;

        lastCoindeskRate = null;

        lastCoinifyRate = null;
    }

    /**
     * Call this one before the others, such that every update to the other callbacks will also
     * trigger an update here
     * @param callback  The callback that can be called several times (!) with increasing precision
     */
    public void getOverallRate(RateCallback callback) {

        overallCallback = callback;
    }

    public void getGoogleRate(RateCallback callback) {

        WebAccessdCallback ourCallback = new GoogleRateCallback(this, callback);

        WebAccessor.getAsynch("https://www.google.de/search?q=1+btc+to+usd", ourCallback);
    }

    public void getBlockchainInfoRate(RateCallback callback) {

        WebAccessdCallback ourCallback = new BlockchainInfoRateCallback(this, callback);

        WebAccessor.getAsynch("https://blockchain.info/ticker", ourCallback);
    }

    public void getCoindeskRate(RateCallback callback) {

        WebAccessdCallback ourCallback = new CoindeskRateCallback(this, callback);

        WebAccessor.getAsynch("https://api.coindesk.com/v1/bpi/currentprice.json", ourCallback);
    }

    public void getCoinifyRate(RateCallback callback) {

        WebAccessdCallback ourCallback = new CoinifyRateCallback(this, callback);

        WebAccessor.getAsynch("https://api.coinify.com/v3/rates", ourCallback);
    }

    void updateLastGoogleRate(double newrate) {

        lastGoogleRate = newrate;

        sendOverallCallbackUpdate();
    }

    void updateLastBlockchainInfoRate(double newrate) {

        lastBlockchainInfoRate = newrate;

        sendOverallCallbackUpdate();
    }

    void updateLastCoindeskRate(double newrate) {

        lastCoindeskRate = newrate;

        sendOverallCallbackUpdate();
    }

    void updateLastCoinifyRate(double newrate) {

        lastCoinifyRate = newrate;

        sendOverallCallbackUpdate();
    }

    private void sendOverallCallbackUpdate() {

        if (overallCallback != null) {

            Double overallRate = getLastOverallRate();

            if (overallRate > 0) {
                overallCallback.foundRate(overallRate);
            }
        }
    }

    public Double getLastOverallRate() {

        double rollingRate = 0;

        double rollingAmount = 0;

        if (lastGoogleRate != null) {
            rollingRate += lastGoogleRate;
            rollingAmount++;
        }

        if (lastBlockchainInfoRate != null) {
            rollingRate += lastBlockchainInfoRate;
            rollingAmount++;
        }

        if (lastCoindeskRate != null) {
            rollingRate += lastCoindeskRate;
            rollingAmount++;
        }

        if (lastCoinifyRate != null) {
            rollingRate += lastCoinifyRate;
            rollingAmount++;
        }

        if (rollingAmount > 0) {
            return rollingRate / rollingAmount;
        }

        return 0.0;
    }

    public Double getLastGoogleRate() {
        if (lastGoogleRate == null) {
            return 0.0;
        }
        return lastGoogleRate;
    }

    public Double getLastBlockchainInfoRate() {
        if (lastBlockchainInfoRate == null) {
            return 0.0;
        }
        return lastBlockchainInfoRate;
    }

    public Double getLastCoindeskRate() {
        if (lastCoindeskRate == null) {
            return 0.0;
        }
        return lastCoindeskRate;
    }

    public Double getLastCoinifyRate() {
        if (lastCoinifyRate == null) {
            return 0.0;
        }
        return lastCoinifyRate;
    }

}
