package com.mobilemonkeysoftware.fibarohomecenter.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobilemonkeysoftware.fibaroapi.model.Room;
import com.mobilemonkeysoftware.fibarohomecenter.R;
import com.mobilemonkeysoftware.fibarohomecenter.ui.model.SectionItem;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by AR on 01/08/2016.
 */
public class SectionListAdapter extends BaseListAdapter<SectionListAdapter.ViewHolder, SectionItem> {

    private Fragment mFragment;

    public SectionListAdapter(@NonNull Fragment fragment, @NonNull List<SectionItem> items) {
        super(fragment.getContext(), items);
        mFragment = fragment;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(R.layout.adapter_section_list_item, parent));
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {

        SectionItem item = getItem(position);
        holder.view.setTag(item);
        holder.name.setText(item.section().name());
        holder.rooms.setText(getRoomsInfo(item.rooms()));
    }

    @NonNull private String getRoomsInfo(@Nullable List<Room> rooms) {

        StringBuilder roomsBuilder = new StringBuilder();
        for (int i = 0, size = rooms != null ? rooms.size() : 0; i < size; i++) {
            Room room = rooms.get(i);
            if (room != null) {
                roomsBuilder.append(room.name()).append(" ");
            }
        }
        return roomsBuilder.toString();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.root_view) CardView view;
        @BindView(R.id.name) TextView name;
        @BindView(R.id.rooms) TextView rooms;

        public ViewHolder(View view) {
            super(view);
        }

        @OnClick(R.id.root_view) public void onClick(View v) {

            SectionItem item = (SectionItem) v.getTag();
            if (item != null) {
//                mFragment.startActivity(UserActivity.buildIntent(getContext(), item));
            }
        }
    }

}