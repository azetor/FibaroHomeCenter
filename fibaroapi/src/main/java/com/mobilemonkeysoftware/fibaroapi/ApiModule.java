package com.mobilemonkeysoftware.fibaroapi;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.GsonBuilder;
import com.mobilemonkeysoftware.fibaroapi.autovalue.GsonTypeAdapterFactory;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AR on 30/07/2016.
 */
@Module
public class ApiModule {

    private static final GsonConverterFactory GSON_CONVERTER_FACTORY = GsonConverterFactory
            .create(new GsonBuilder()
                    .registerTypeAdapterFactory(new GsonTypeAdapterFactory())
                    .serializeNulls()
                    .setPrettyPrinting()
                    .create());

    private String mUrl;
    private String mLogin;
    private String mPassword;

    public ApiModule(@NonNull String url, @NonNull String login, @NonNull String password) {
        mUrl = url;
        mLogin = login;
        mPassword = password;
    }

    @Provides
    @Singleton
    public Api provideApi() {
        return new Retrofit.Builder()
                .client(buildOkHttpClient())
                .baseUrl(mUrl)
                .addConverterFactory(GSON_CONVERTER_FACTORY)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(Api.class);
    }

    @Nullable private OkHttpClient buildOkHttpClient() {

        if (!TextUtils.isEmpty(mLogin) || !TextUtils.isEmpty(mPassword)) {
            final String authorization = new StringBuilder("Basic ")
                    .append(Base64.encodeToString((mLogin + ":" + mPassword).getBytes(), Base64.NO_WRAP))
                    .toString();

            return new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Interceptor.Chain chain) throws IOException {

                            Request originalRequest = chain.request();
                            Request.Builder newRequestBuilder = originalRequest.newBuilder()
                                    .header("Authorization", authorization)
                                    .header("Accept", "application/json")
                                    .method(originalRequest.method(), originalRequest.body());
                            return chain.proceed(newRequestBuilder.build());
                        }
                    })
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }
        return null;
    }

}
