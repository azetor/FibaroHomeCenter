package com.mobilemonkeysoftware.fibarohomecenter.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.mobilemonkeysoftware.fibarohomecenter.R;
import com.mobilemonkeysoftware.fibarohomecenter.rx.RxHelper;
import com.mobilemonkeysoftware.fibarohomecenter.ui.adapter.SectionListAdapter;
import com.mobilemonkeysoftware.fibarohomecenter.ui.model.SectionItem;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AR on 01/08/2016.
 */
public class SectionListFragment extends BaseListFragment<SectionItem, SectionListAdapter> {

    private static final String TAG = SectionListFragment.class.getName();

    private View.OnClickListener mRefreshClickListener = new View.OnClickListener() {
        @Override public void onClick(View view) {

            Snackbar.make(view, "Refresh Sections", Snackbar.LENGTH_LONG).show();
            startItemsLoad();
        }
    };

    @Override public int getSpanResId() {
        return R.integer.list_span_count;
    }

    @Override public int getEmptyListTextResId() {
        return R.string.empty_list_users;
    }

    @Override public boolean canRefreshOnSwipe() {
        return true;
    }

    @Override public void refreshOnSwipe() {
        startItemsLoad();
    }

    @Override public void startItemsLoad() {

        Log.d(TAG, "Loading Sections...");
        refresh(true);
        getAdapter().clear();
        final List<SectionItem> items = new ArrayList<>();

        RxHelper.
                loadSectionItems(api.getSections(), api.getRooms())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SectionItem>>() {
                    @Override public void onCompleted() {

                        Log.d(TAG, "onCompleted");
                        clearItems();
                        addItems(items);
                        refresh(false);
                    }

                    @Override public void onError(Throwable e) {

                        Log.e(TAG, "Sections download error", e);
                        updateEmptyInfo();
                        refresh(false);
                    }

                    @Override public void onNext(List<SectionItem> sectionItems) {

                        Log.d(TAG, "onNext: " + sectionItems.toString());
                        items.addAll(sectionItems);
                    }
                });
    }

    @NonNull @Override public SectionListAdapter createEmptyAdapter() {
        return new SectionListAdapter(SectionListFragment.this, new ArrayList<SectionItem>());
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getMainActivity().registerRefreshClickListener(mRefreshClickListener);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();

        getMainActivity().unregisterRefreshClickListener();
    }

}
