package com.mobilemonkeysoftware.fibarohomecenter.ui.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.mobilemonkeysoftware.fibaroapi.model.Device;
import com.mobilemonkeysoftware.fibaroapi.model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AR on 02/08/2016.
 */
@AutoValue
public abstract class RoomItem implements Parcelable, Item {

    public abstract Room room();
    public abstract List<Device> devices();

    public static RoomItem create(@NonNull Room room) {
        return new AutoValue_RoomItem(room, new ArrayList<Device>());
    }

    public static TypeAdapter<RoomItem> typeAdapter(Gson gson) {
        return new AutoValue_RoomItem.GsonTypeAdapter(gson);
    }

    @Nullable public Device getDevices(int position) {
        try {
            return devices().get(position);
        } catch (Exception e) {
            return null;
        }
    }

    @NonNull public List<String> getDevicesNames() {

        List<String> results = new ArrayList<>();
        for (Device device: devices()) {
            results.add(device.name());
        }
        return results;
    }

}
