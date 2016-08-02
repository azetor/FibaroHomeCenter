package com.mobilemonkeysoftware.fibarohomecenter.ui.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobilemonkeysoftware.fibarohomecenter.ui.model.Item;

import java.util.List;

/**
 * Created by AR on 01/08/2016.
 */
public abstract class BaseListAdapter<VH extends BaseViewHolder, T extends Parcelable & Item> extends RecyclerView.Adapter<VH> {

    private Context mContext;

    private List<T> mItems;

    public BaseListAdapter(@NonNull Context context, @NonNull List<T> items) {
        mContext = context;
        mItems = items;
    }

    @Nullable
    protected View inflate(@LayoutRes int layoutResId, @Nullable ViewGroup parent) {
        return LayoutInflater.from(getContext()).inflate(layoutResId, parent, false);
    }

    @NonNull public Context getContext() {
        return mContext;
    }

    @Override public int getItemCount() {
        return count();
    }

    protected int count() {
        return mItems != null ? mItems.size() : 0;
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    @NonNull protected T getItem(int position) {
        return mItems.get(position);
    }

    @NonNull public List<T> getItems() {
        return mItems;
    }

    public void addAll(@NonNull List<T> items) {

        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void clear() {

        mItems.clear();
        notifyDataSetChanged();
    }

    public boolean remove(T item) {

        boolean removed = mItems.remove(item);
        notifyDataSetChanged();
        return removed;
    }


    @NonNull public String connectNames(@NonNull List<String> names) {

        StringBuilder namesBuilder = new StringBuilder();
        for (String name : names) {
            namesBuilder.append(name).append("\n");
        }
        return namesBuilder.toString();
    }

}
