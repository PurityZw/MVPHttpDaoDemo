package com.bestvike.mvphttpdaodemo.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by use on 2017/7/15.
 * hqx
 *
 */

public class AppApplication extends Application {

    public static Context CONTEXT;

    public static final List<Activity> activities = new ArrayList<>();



    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = getApplicationContext();

    }
}
