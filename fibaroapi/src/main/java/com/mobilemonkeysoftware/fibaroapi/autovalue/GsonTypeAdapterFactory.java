package com.mobilemonkeysoftware.fibaroapi.autovalue;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.mobilemonkeysoftware.fibaroapi.model.Actions;
import com.mobilemonkeysoftware.fibaroapi.model.Device;
import com.mobilemonkeysoftware.fibaroapi.model.Properties;
import com.mobilemonkeysoftware.fibaroapi.model.Room;
import com.mobilemonkeysoftware.fibaroapi.model.Section;
import com.mobilemonkeysoftware.fibaroapi.model.SettingsInfo;

/**
 * Created by AR on 31/07/2016.
 */
public final class GsonTypeAdapterFactory implements TypeAdapterFactory {

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

        Class<? super T> rawType = type.getRawType();
        if (rawType.equals(SettingsInfo.class)) {
            return (TypeAdapter<T>) SettingsInfo.typeAdapter(gson);
        } else if (rawType.equals(Section.class)) {
            return (TypeAdapter<T>) Section.typeAdapter(gson);
        } else if (rawType.equals(Room.class)) {
            return (TypeAdapter<T>) Room.typeAdapter(gson);
        } else if (rawType.equals(Device.class)) {
            return (TypeAdapter<T>) Device.typeAdapter(gson);
        } else if (rawType.equals(Properties.class)) {
            return (TypeAdapter<T>) Properties.typeAdapter(gson);
        } else if (rawType.equals(Actions.class)) {
            return (TypeAdapter<T>) Actions.typeAdapter(gson);
        }
        return null;
    }

}
