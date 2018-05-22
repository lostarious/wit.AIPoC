package mert.android.com.witaipoc;

import android.app.Application;

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
