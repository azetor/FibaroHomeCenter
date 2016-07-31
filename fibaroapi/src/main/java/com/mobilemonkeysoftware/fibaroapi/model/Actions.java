package com.mobilemonkeysoftware.fibaroapi.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by AR on 31/07/2016.
 */
@AutoValue
public abstract class Actions implements Parcelable {

    public abstract int setValue();
    @Nullable public abstract String turnOff();
    @Nullable public abstract String turnOn();

    public static TypeAdapter<Actions> typeAdapter(Gson gson) {
        return new AutoValue_Actions.GsonTypeAdapter(gson);
    }

}
