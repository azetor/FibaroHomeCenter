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
public abstract class Properties implements Parcelable {

    @Nullable public abstract String dead();
    @Nullable public abstract String disabled();
    @Nullable public abstract String value();

    public static TypeAdapter<Properties> typeAdapter(Gson gson) {
        return new AutoValue_Properties.GsonTypeAdapter(gson);
    }

}
