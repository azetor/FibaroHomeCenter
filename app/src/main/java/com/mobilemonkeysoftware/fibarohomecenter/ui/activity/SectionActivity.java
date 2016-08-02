package com.mobilemonkeysoftware.fibarohomecenter.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mobilemonkeysoftware.fibarohomecenter.R;
import com.mobilemonkeysoftware.fibarohomecenter.ui.model.SectionItem;

/**
 * Created by AR on 02/08/2016.
 */
public class SectionActivity extends BaseActivity {

    @NonNull public static Intent buildIntent(@NonNull Context context, @NonNull SectionItem item) {
        return new Intent(context, SectionActivity.class).putExtra(EXTRA_ITEM, item);
    }

    @LayoutRes
    @Override protected int getLayoutResId() {
        return R.layout.activity_section;
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        SectionItem item = getIntent().getParcelableExtra(EXTRA_ITEM);
        setTitle(item.section().name());
    }

}
