package com.mobilemonkeysoftware.fibaroapi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AR on 31/07/2016.
 */
public enum DeviceType {

    @SerializedName("binary_light")
    BINARY_LIGHT,
    @SerializedName("dimmable_light")
    DIMMABLE_LIGHT,
    @SerializedName("HC_user")
    HC_USER,
    @SerializedName("weather")
    WEATHER,
    @SerializedName("blind")
    BLIND,
    @SerializedName("door_sensor")
    DOOR_SENSOR,
    @SerializedName("humidity_sensor")
    HUMIDITY_SENSOR,
    @SerializedName("motion_sensor")
    MOTION_SENSOR,
    @SerializedName("temperature_sensor")
    TEMPERATURE_SENSOR,
    @SerializedName("smoke_sensor")
    SMOKE_SENSOR,
    @SerializedName("water_sensor")
    WATER_SENSOR,
    @SerializedName("iOS_device")
    IOS_DEVICE,
    @SerializedName("IP_camera")
    IP_CAMERA,
    @SerializedName("satel")
    SATEL,
    @SerializedName("satel_zone")
    SATEL_ZONE,
    @SerializedName("satel_output")
    SATEL_OUTPUT,
    @SerializedName("satel_partition")
    SATEL_PARTITION,
    @SerializedName("unknown_device")
    UNKNOWN_DEVICE;

}
