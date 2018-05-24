package mert.android.com.witaipoc;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import java.io.IOException;

import mert.android.com.witaipoc.home.MainActivity;
import mert.android.com.witaipoc.network.RetrofitNetwork;

/**
 * Created by lostarious on 22.05.2018
 * Copyright (c) 2018 YGA to present
 * All rights reserved.
 */
public class WitaipocApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitNetwork retrofitNetwork = new RetrofitNetwork();


    }

}
