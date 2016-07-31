package com.mobilemonkeysoftware.fibarohomecenter;

import android.app.Application;

import com.mobilemonkeysoftware.fibaroapi.ApiModule;

/**
 * Created by AR on 31/07/2016.
 */
public class MainApplication extends Application {

    private ApiComponent mApiComponent;

    @Override public void onCreate() {
        super.onCreate();

        mApiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule(BuildConfig.API_URL, BuildConfig.API_LOGIN, BuildConfig.API_PASSWORD))
                .build();
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }

}
