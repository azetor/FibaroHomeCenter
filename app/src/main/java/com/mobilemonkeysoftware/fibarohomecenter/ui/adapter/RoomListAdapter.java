package com.mobilemonkeysoftware.fibarohomecenter.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobilemonkeysoftware.fibarohomecenter.R;
import com.mobilemonkeysoftware.fibarohomecenter.ui.activity.RoomActivity;
import com.mobilemonkeysoftware.fibarohomecenter.ui.model.RoomItem;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by AR on 02/08/2016.
 */
public class RoomListAdapter extends BaseListAdapter<RoomListAdapter.ViewHolder, RoomItem> {

    private Fragment mFragment;

    public RoomListAdapter(@NonNull Fragment fragment, @NonNull List<RoomItem> items) {
        super(fragment.getContext(), items);
        mFragment = fragment;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(R.layout.adapter_base_list_item, parent));
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {

        RoomItem item = getItem(position);
        holder.view.setTag(item);
        holder.name.setText(item.room().name());
        holder.devices.setText(connectNames(item.getDevicesNames()));
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.root_view) CardView view;
        @BindView(R.id.name) TextView name;
        @BindView(R.id.subitems) TextView devices;

        public ViewHolder(View view) {
            super(view);
        }

        @OnClick(R.id.root_view) public void onClick(View v) {

            RoomItem item = (RoomItem) v.getTag();
            if (item != null) {
                mFragment.startActivity(RoomActivity.buildIntent(getContext(), item));
            }
        }
    }

}
