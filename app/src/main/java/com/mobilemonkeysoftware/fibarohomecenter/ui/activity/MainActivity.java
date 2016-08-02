package com.mobilemonkeysoftware.fibarohomecenter.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mobilemonkeysoftware.fibarohomecenter.R;
import com.mobilemonkeysoftware.fibarohomecenter.ui.fragment.SectionListFragment;
import com.mobilemonkeysoftware.fibarohomecenter.ui.fragment.SettingsInfoFragment;

import java.util.Map;

import butterknife.BindView;

/**
 * Created by AR on 30/07/2016.
 */
public class MainActivity extends BaseActivity {

    private static final Map<Integer, String> NAVIGATION_VIEW_ITEM_MAP = new ArrayMap<Integer, String>() {{
        put(R.id.nav_settings_info, SettingsInfoFragment.class.getName());
        put(R.id.nav_home, SectionListFragment.class.getName());
    }};

    @BindView(R.id.refresh_fab) FloatingActionButton refreshFab;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.nav_view) NavigationView navigationView;

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private NavigationView.OnNavigationItemSelectedListener mItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override public boolean onNavigationItemSelected(MenuItem item) {

            int id = item.getItemId();
            String fragmentName = NAVIGATION_VIEW_ITEM_MAP.get(id);
            if (!TextUtils.isEmpty(fragmentName)) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_fragment);
                if (fragment == null || !fragment.getClass().getName().equals(fragmentName)) {
                    fragment = Fragment.instantiate(getApplicationContext(), fragmentName);
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_fragment, fragment)
                        .commit();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
    };

    @LayoutRes
    @Override protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        navigationView.setNavigationItemSelectedListener(mItemSelectedListener);
    }

    @Override protected void onResume() {
        super.onResume();

        drawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        selectFragment();
    }

    private void selectFragment() {

        Menu menu = navigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            if (item.isChecked()) {
                mItemSelectedListener.onNavigationItemSelected(item);
            }
        }
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

    public void registerRefreshClickListener(View.OnClickListener refreshClickListener) {
        refreshFab.setOnClickListener(refreshClickListener);
    }

    public void unregisterRefreshClickListener() {
        refreshFab.setOnClickListener(null);
    }

}
