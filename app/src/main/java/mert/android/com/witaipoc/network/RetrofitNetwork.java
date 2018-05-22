package mert.android.com.witaipoc.network;

import mert.android.com.witaipoc.responsedata.ow.OWResponse;
import mert.android.com.witaipoc.responsedata.wit.WitResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lostarious on 22.05.2018
 * Copyright (c) 2018 YGA to present
 * All rights reserved.
 */
public class RetrofitNetwork {
    private static final String WIT_BASE_URL = "https://api.wit.ai/";
    private static final String OW_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/";
    private static WitAPI witClient;
    private static OpenWeatherAPI oWClient;
    private static Retrofit witRetrofit;
    private static Retrofit oWRetrofit;
    private static final String WIT_AUTH_TOKEN = "Bearer 7PCGMCROF3A4W7RNXFO64SKTHVMYAKNR";
    private static final String OW_API_KEY = "a20fee317f1e5d273b093864e6cb34f2";
    public RetrofitNetwork(){
            Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(WIT_BASE_URL).addConverterFactory(GsonConverterFactory.create());
            witRetrofit = retrofitBuilder.build();
            witClient = witRetrofit.create(WitAPI.class);
            oWRetrofit = new Retrofit.Builder().baseUrl(OW_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            oWClient = oWRetrofit.create(OpenWeatherAPI.class);
    }
    public static Call<WitResponse> makeQuery(String value, String queryText){
            return witClient.makequery(WIT_AUTH_TOKEN,value,queryText);
    }
    public static Call<OWResponse> request5DayForecast(String City){
        City = City+",TR";
        return oWClient.request5DayForecast(City,OW_API_KEY);
    }
}
