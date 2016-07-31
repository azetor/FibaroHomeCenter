package com.mobilemonkeysoftware.fibaroapi;

import com.mobilemonkeysoftware.fibaroapi.model.Device;
import com.mobilemonkeysoftware.fibaroapi.model.Room;
import com.mobilemonkeysoftware.fibaroapi.model.Section;
import com.mobilemonkeysoftware.fibaroapi.model.SettingsInfo;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by AR on 30/07/2016.
 */
public interface Api {

    @GET("settings/info") Observable<SettingsInfo> getSettingsInfo();

    @GET("sections") Observable<List<Section>> getSections();

    @GET("rooms") Observable<List<Room>> getRooms();

    @GET("devices") Observable<List<Device>> getDevices();

    @GET("callAction") Observable<Object> getCallAction(
            @Query("deviceID") int deviceId,
            @QueryMap Map<String, String> args);

}
