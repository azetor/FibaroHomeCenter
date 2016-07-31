package com.mobilemonkeysoftware.fibaroapi.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by AR on 31/07/2016.
 */
@AutoValue
public abstract class SettingsInfo implements Parcelable {

    public abstract String serialNumber();
    public abstract String mac();
    public abstract String softVersion();
    public abstract boolean beta();
    public abstract String zwaveVersion();
    public abstract int serverStatus();
    public abstract String defaultLanguage();
    public abstract String sunsetHour();
    public abstract String sunriseHour();
    public abstract boolean hotelMode();
    public abstract boolean updateStableAvailable();
    public abstract String temperatureUnit();
    public abstract boolean updateBetaAvailable();
    public abstract boolean batteryLowNotification();
    public abstract boolean smsManagement();

    public static TypeAdapter<SettingsInfo> typeAdapter(Gson gson) {
        return new AutoValue_SettingsInfo.GsonTypeAdapter(gson);
    }

}
