package com.edmond.jynet;

import android.app.Application;

/**
 * Created by edmond on 17-3-19.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Net.init(this);
    }
}
