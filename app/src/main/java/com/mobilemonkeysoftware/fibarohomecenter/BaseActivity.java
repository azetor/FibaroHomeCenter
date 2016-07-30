package com.mobilemonkeysoftware.fibarohomecenter;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AR on 30/07/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

}
