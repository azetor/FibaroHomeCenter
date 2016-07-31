package com.mobilemonkeysoftware.fibaroapi.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AR on 31/07/2016.
 */
@AutoValue
public abstract class Room implements Parcelable {

    public abstract int id();
    public abstract String name();
    @SerializedName("sectionID") public abstract int sectionId();
    public abstract int sortOrder();

    public static TypeAdapter<Room> typeAdapter(Gson gson) {
        return new AutoValue_Room.GsonTypeAdapter(gson);
    }

}
