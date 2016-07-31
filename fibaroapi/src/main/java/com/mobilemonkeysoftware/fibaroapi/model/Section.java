package com.mobilemonkeysoftware.fibaroapi.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by AR on 31/07/2016.
 */
@AutoValue
public abstract class Section implements Parcelable {

    public abstract int id();
    public abstract String name();
    public abstract int sortOrder();

    public static TypeAdapter<Section> typeAdapter(Gson gson) {
        return new AutoValue_Section.GsonTypeAdapter(gson);
    }

}
