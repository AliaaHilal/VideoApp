package com.aliaa.sampleappassignment;

import android.app.Activity;
import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Aliaa on 21/01/2019.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/b_comps_bd.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
