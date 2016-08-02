package com.mobilemonkeysoftware.fibarohomecenter.ui.fragment;

import android.support.annotation.NonNull;
import android.util.Log;

import com.mobilemonkeysoftware.fibarohomecenter.R;
import com.mobilemonkeysoftware.fibarohomecenter.rx.RxHelper;
import com.mobilemonkeysoftware.fibarohomecenter.ui.activity.SectionActivity;
import com.mobilemonkeysoftware.fibarohomecenter.ui.adapter.RoomListAdapter;
import com.mobilemonkeysoftware.fibarohomecenter.ui.model.RoomItem;
import com.mobilemonkeysoftware.fibarohomecenter.ui.model.SectionItem;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AR on 02/08/2016.
 */
public class RoomListFragment extends BaseListFragment<RoomItem, RoomListAdapter> {

    private static final String TAG = RoomListFragment.class.getName();

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
        final List<RoomItem> items = new ArrayList<>();
        SectionItem sectionItem = getActivity().getIntent().getParcelableExtra(SectionActivity.EXTRA_ITEM);

        RxHelper
                .loadRoomItems(sectionItem.rooms(), api.getDevices())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<RoomItem>>() {
                    @Override public void onCompleted() {

                        Log.d(TAG, "onCompleted");
                        clearItems();
                        addItems(items);
                        refresh(false);
                    }

                    @Override public void onError(Throwable e) {
                        Log.e(TAG, "Rooms and devices download error", e);
                        updateEmptyInfo();
                        refresh(false);
                    }

                    @Override public void onNext(List<RoomItem> roomItems) {

                        Log.d(TAG, "onNext: " + roomItems.toString());
                        items.addAll(roomItems);
                    }
                });
    }

    @NonNull @Override public RoomListAdapter createEmptyAdapter() {
        return new RoomListAdapter(this, new ArrayList<RoomItem>());
    }

}
