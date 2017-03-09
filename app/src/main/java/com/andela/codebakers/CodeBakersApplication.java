package com.andela.codebakers;

import android.app.Application;

import teaspoon.TeaSpoon;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Mayokun on 2/20/2017.
 */

public class CodeBakersApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TeaSpoon.initialize();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
