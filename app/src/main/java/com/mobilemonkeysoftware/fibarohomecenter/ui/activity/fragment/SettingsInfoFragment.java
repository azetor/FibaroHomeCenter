package com.mobilemonkeysoftware.fibarohomecenter.ui.activity.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobilemonkeysoftware.fibaroapi.Api;
import com.mobilemonkeysoftware.fibaroapi.model.SettingsInfo;
import com.mobilemonkeysoftware.fibarohomecenter.R;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AR on 31/07/2016.
 */
public class SettingsInfoFragment extends BaseFragment {

    private static final String TAG = SettingsInfoFragment.class.getName();

    @BindView(R.id.serial_number) TextView serialNumber;
    @BindView(R.id.mac) TextView mac;
    @BindView(R.id.soft_version) TextView softVersion;

    @Inject Api api;

    @LayoutRes
    @Override protected int getLayoutResId() {
        return R.layout.fragment_settings_info;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMainApplication().getApiComponent().inject(this);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadSettingsInfo();
    }

    private void loadSettingsInfo() {

        showProgressDialog("Loading Home Center info...");
        api.getSettingsInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SettingsInfo>() {
                    @Override public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                        hideProgressDialog();
                    }

                    @Override public void onError(Throwable e) {
                        Log.i(TAG, "onError", e);
                        hideProgressDialog();
                    }

                    @Override public void onNext(SettingsInfo settingsInfo) {

                        Log.i(TAG, "onNext: " + settingsInfo.toString());
                        serialNumber.setText(String.format(Locale.ENGLISH, "Serial number: %s", settingsInfo.serialNumber()));
                        mac.setText(String.format(Locale.ENGLISH, "MAC Address: %s",settingsInfo.mac()));
                        softVersion.setText(String.format(Locale.ENGLISH, "Software version: %s",settingsInfo.softVersion()));
                    }
                });
    }

}
