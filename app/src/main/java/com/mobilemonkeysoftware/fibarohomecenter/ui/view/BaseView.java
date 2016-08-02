package com.mobilemonkeysoftware.fibarohomecenter.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.ButterKnife;

/**
 * Created by AR on 02/08/2016.
 */
public abstract class BaseView extends FrameLayout implements ViewInitialization {

    public BaseView(Context context) {
        super(context);
        initialize();
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public BaseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    public void initialize() {

        View child = onInitialize();
        ButterKnife.bind(this, child);
        addView(child);
    }

}
