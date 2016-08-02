package com.mobilemonkeysoftware.fibarohomecenter.ui.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.mobilemonkeysoftware.fibaroapi.model.Device;
import com.mobilemonkeysoftware.fibaroapi.model.Room;
import com.mobilemonkeysoftware.fibaroapi.model.Section;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AR on 01/08/2016.
 */
@AutoValue
public abstract class SectionItem implements Parcelable, Item {

    public abstract Section section();
    public abstract List<Room> rooms();

    public static SectionItem create(@NonNull Section section) {
        return new AutoValue_SectionItem(section, new ArrayList<Room>());
    }

    public static TypeAdapter<SectionItem> typeAdapter(Gson gson) {
        return new AutoValue_SectionItem.GsonTypeAdapter(gson);
    }

    @Nullable public Room getRoom(int position) {
        try {
            return rooms().get(position);
        } catch (Exception e) {
            return null;
        }
    }

    @NonNull public List<String> getRoomsNames() {

        List<String> results = new ArrayList<>();
        for (Room room: rooms()) {
            results.add(room.name());
        }
        return results;
    }

}
