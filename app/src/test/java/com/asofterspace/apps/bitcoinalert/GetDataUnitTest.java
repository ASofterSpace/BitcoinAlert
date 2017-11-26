package com.asofterspace.apps.bitcoinalert;

import com.asofterspace.apps.bitcoinalert.backend.BackendController;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test concerning getting data about the BTC price
 *
 * @author Moya (a softer space, 2017)
 */
public class GetDataUnitTest {

    @Test
    public void getDataFromGoogle() throws Exception {

        BackendController backCtrl = new BackendController();

        backCtrl.getGoogleRate(new UnitTestCallback("google"));
    }

    @Test
    public void getDataFromCoindesk() throws Exception {

        BackendController backCtrl = new BackendController();

        backCtrl.getCoindeskRate(new UnitTestCallback("coindesk"));
    }

    @Test
    public void getDataFromBlockchainInfo() throws Exception {

        BackendController backCtrl = new BackendController();

        backCtrl.getBlockchainInfoRate(new UnitTestCallback("blockchain.info"));
    }
}