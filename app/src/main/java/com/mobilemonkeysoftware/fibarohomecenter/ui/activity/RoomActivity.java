package com.mobilemonkeysoftware.fibarohomecenter.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.mobilemonkeysoftware.fibaroapi.model.Device;
import com.mobilemonkeysoftware.fibaroapi.model.Room;
import com.mobilemonkeysoftware.fibarohomecenter.R;
import com.mobilemonkeysoftware.fibarohomecenter.ui.model.RoomItem;
import com.mobilemonkeysoftware.fibarohomecenter.ui.view.DeviceView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AR on 02/08/2016.
 */
public class RoomActivity extends BaseActivity implements DeviceView.OnDeviceChangeListener {

    private static final String TAG = RoomActivity.class.getName();

    @BindView(R.id.devices) LinearLayout devicesLayout;

    @NonNull public static Intent buildIntent(@NonNull Context context, @NonNull RoomItem item) {
        return new Intent(context, RoomActivity.class).putExtra(EXTRA_ITEM, item);
    }

    @Override protected int getLayoutResId() {
        return R.layout.activity_room;
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RoomItem item = getIntent().getParcelableExtra(EXTRA_ITEM);
        setTitle(item.room().name());

        devicesLayout.removeAllViews();
        for (Device device : item.devices()) {
            DeviceView view = new DeviceView(this);
            view.setTag(device);
            devicesLayout.addView(view);
            view.setName(device.name());
            view.setType(device.type());
            view.setTurn(device.isTurnOn());
            int level = 0;
            try {
                level = Integer.valueOf(device.properties().value());
            } catch (NumberFormatException e) {
                Log.e(TAG, "Level parse error", e);
            }
            view.setLevel(level);
            view.setOnDeviceChangeListener(this);
        }
    }

    @Override public void onTurnOnChanged(View view, boolean checked) {

        Device device = (Device) view.getTag();

        Map<String, String> args = new HashMap<>();
        args.put("name", checked ? "turnOn" : "turnOff");
        sendDeviceChange(device.id(), args);
    }

    private void sendDeviceChange(int id, Map<String, String> args) {

        api.getCallAction(id, args)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override public void onError(Throwable e) {
                        Log.e(TAG, "Level change error", e);
                    }

                    @Override public void onNext(Object o) {
                        Log.i(TAG, "onNext: " + o);
                    }
                });
    }

    @Override public void onProgressChanged(View view, int level) {

        Device device = (Device) view.getTag();

        Map<String, String> args = new HashMap<>();
        args.put("name", "setValue");
        args.put("arg1", String.valueOf(level));
        sendDeviceChange(device.id(), args);
    }

}
