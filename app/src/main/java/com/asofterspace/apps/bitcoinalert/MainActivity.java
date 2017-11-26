package com.asofterspace.apps.bitcoinalert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.asofterspace.apps.bitcoinalert.backend.BackendController;
import com.asofterspace.apps.bitcoinalert.frontend.FrontendController;

/**
 * This is the main activity of the UniversalTranslator, which concerns itself with - who would have
 * guessed - translating. =)
 *
 * @author Moya (a softer space, 2017)
 */
public class MainActivity extends AppCompatActivity {

    private BackendController backCtrl;

    private FrontendController frontCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backCtrl = new BackendController();

        frontCtrl = new FrontendController(this, backCtrl);
    }

}
