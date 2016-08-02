package com.mobilemonkeysoftware.fibarohomecenter.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.mobilemonkeysoftware.fibaroapi.Api;
import com.mobilemonkeysoftware.fibarohomecenter.MainApplication;
import com.mobilemonkeysoftware.fibarohomecenter.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AR on 30/07/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM = "extra_item";

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Inject Api api;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMainApplication().getApiComponent().inject(this);

        setContentView(getLayoutResId());
        setSupportActionBar(toolbar);
    }

    @Override public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        bind();
    }

    private void bind() {
        ButterKnife.bind(this);
    }

    @Override public void setContentView(View view) {
        super.setContentView(view);

        bind();
    }

    @LayoutRes protected abstract int getLayoutResId();

    public MainApplication getMainApplication() {
        return (MainApplication) getApplication();
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
