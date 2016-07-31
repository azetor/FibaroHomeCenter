package com.mobilemonkeysoftware.fibarohomecenter.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mobilemonkeysoftware.fibaroapi.Api;
import com.mobilemonkeysoftware.fibaroapi.model.Device;
import com.mobilemonkeysoftware.fibaroapi.model.DeviceType;
import com.mobilemonkeysoftware.fibarohomecenter.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by AR on 30/07/2016.
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getName();

    @BindView(R.id.refresh_fab) FloatingActionButton refreshFab;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @Inject Api api;

    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private View.OnClickListener mRefreshClickListener = new View.OnClickListener() {
        @Override public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    };
    private NavigationView.OnNavigationItemSelectedListener mItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override public boolean onNavigationItemSelected(MenuItem item) {

            int id = item.getItemId();
            if (id == R.id.nav_camera) {
                // Handle the camera action
            } else if (id == R.id.nav_gallery) {

            } else if (id == R.id.nav_slideshow) {

            } else if (id == R.id.nav_manage) {

            } else if (id == R.id.nav_share) {

            } else if (id == R.id.nav_send) {

            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
    };

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMainApplication().getApiComponent().inject(this);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        navigationView.setNavigationItemSelectedListener(mItemSelectedListener);
        refreshFab.setOnClickListener(mRefreshClickListener);

        api.getDevices()
                .flatMap(new Func1<List<Device>, Observable<List<Device>>>() {
                    @Override public Observable<List<Device>> call(final List<Device> devices) {
                        return Observable.create(new Observable.OnSubscribe<List<Device>>() {
                            @Override
                            public void call(Subscriber<? super List<Device>> subscriber) {
                                try {
                                    final List<Device> filteredDevices = new ArrayList<>();
                                    for (int i = 0, size = devices != null ? devices.size() : 0; i < size; i++) {
                                        Device device = devices.get(i);
                                        if (DeviceType.BINARY_LIGHT.equals(device.type())
                                                || DeviceType.DIMMABLE_LIGHT.equals(device.type())) {
                                            filteredDevices.add(device);
                                        }
                                    }
                                    subscriber.onNext(filteredDevices);
                                    subscriber.onCompleted();
                                } catch (Exception e) {
                                    subscriber.onError(e);
                                }
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Device>>() {
                    @Override public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override public void onError(Throwable e) {
                        Log.i(TAG, "onError", e);
                    }

                    @Override public void onNext(List<Device> sections) {
                        Log.i(TAG, "onNext: " + sections.toString());
                    }
                });
    }

    @LayoutRes
    @Override protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override protected void onResume() {
        super.onResume();

        drawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
    }

    @Override protected void onPause() {
        super.onPause();

        drawerLayout.removeDrawerListener(mActionBarDrawerToggle);
    }

    @Override public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
