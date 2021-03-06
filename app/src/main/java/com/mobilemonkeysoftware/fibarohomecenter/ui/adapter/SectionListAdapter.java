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
import com.mobilemonkeysoftware.fibarohomecenter.ui.activity.SectionActivity;
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
        return new ViewHolder(inflate(R.layout.adapter_base_list_item, parent));
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {

        SectionItem item = getItem(position);
        holder.view.setTag(item);
        holder.name.setText(item.section().name());
        holder.rooms.setText(connectNames(item.getRoomsNames()));
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.root_view) CardView view;
        @BindView(R.id.name) TextView name;
        @BindView(R.id.subitems) TextView rooms;

        public ViewHolder(View view) {
            super(view);
        }

        @OnClick(R.id.root_view) public void onClick(View v) {

            SectionItem item = (SectionItem) v.getTag();
            if (item != null) {
                mFragment.startActivity(SectionActivity.buildIntent(getContext(), item));
            }
        }
    }

}