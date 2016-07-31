package com.mobilemonkeysoftware.fibarohomecenter.ui.activity.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobilemonkeysoftware.fibarohomecenter.MainApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by AR on 31/07/2016.
 */
public abstract class BaseFragment extends Fragment {

    private ProgressDialog mProgressDialog;

    private Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutResId(), container, false);
        bind(view);

        return view;
    }

    @LayoutRes protected abstract int getLayoutResId();

    public void bind(@NonNull View view) {
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();

        unbind();
    }

    private void unbind() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    public void showProgressDialog(@NonNull String message) {

        if (isAdded()) {
            hideProgressDialog();
            mProgressDialog = ProgressDialog.show(getActivity(), null, message, true, false);
        }
    }

    public void hideProgressDialog() {

        if (isShowingProgressDialog() && isAdded()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public boolean isShowingProgressDialog() {
        return mProgressDialog != null && mProgressDialog.isShowing();
    }

    public MainApplication getMainApplication() {
        return (MainApplication) getActivity().getApplication();
    }

}
