package mert.android.com.witaipoc.network;

import mert.android.com.witaipoc.responsedata.ow.OWResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lostarious on 23.05.2018
 * Copyright (c) 2018 YGA to present
 * All rights reserved.
 */
public interface OpenWeatherAPI {
    @GET("?mode=json&lang=tr&units=metric")
    Call<OWResponse> request5DayForecast(
    @Query("q") String City,
    @Query("APPID") String API_KEY
    );


}
