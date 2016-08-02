package com.mobilemonkeysoftware.fibarohomecenter.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mobilemonkeysoftware.fibaroapi.model.SettingsInfo;
import com.mobilemonkeysoftware.fibarohomecenter.R;

import java.util.Locale;

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

    private View.OnClickListener mRefreshClickListener = new View.OnClickListener() {
        @Override public void onClick(View view) {

            Snackbar.make(view, "Refresh Settings Info", Snackbar.LENGTH_LONG).show();
            loadSettingsInfo();
        }
    };

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
                        mac.setText(String.format(Locale.ENGLISH, "MAC Address: %s", settingsInfo.mac()));
                        softVersion.setText(String.format(Locale.ENGLISH, "Software version: %s", settingsInfo.softVersion()));
                    }
                });
    }

    @LayoutRes
    @Override protected int getLayoutResId() {
        return R.layout.fragment_settings_info;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getMainActivity().registerRefreshClickListener(mRefreshClickListener);
        loadSettingsInfo();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();

        getMainActivity().unregisterRefreshClickListener();
    }

}
