package com.mobilemonkeysoftware.fibarohomecenter;

import com.mobilemonkeysoftware.fibaroapi.ApiModule;
import com.mobilemonkeysoftware.fibarohomecenter.ui.activity.MainActivity;
import com.mobilemonkeysoftware.fibarohomecenter.ui.activity.fragment.SettingsInfoFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by AR on 31/07/2016.
 */
@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(MainActivity activity);

    void inject(SettingsInfoFragment fragment);

}
