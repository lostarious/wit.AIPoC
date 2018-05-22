package mert.android.com.witaipoc.network;

import mert.android.com.witaipoc.responsedata.WitResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lostarious on 22.05.2018
 * Copyright (c) 2018 YGA to present
 * All rights reserved.
 */
public class RetrofitNetwork {
    public static final String BASE_URL = "https://api.wit.ai/";
    public static WitAPI client;
    public static Retrofit retrofit;
    public static final String AUTH_TOKEN = "Bearer 7PCGMCROF3A4W7RNXFO64SKTHVMYAKNR";
    public RetrofitNetwork(){
            Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
            retrofit = retrofitBuilder.build();
            client = retrofit.create(WitAPI.class);
    }
    public static Call<WitResponse> makeQuery(String value, String queryText){
            return client.makequery(AUTH_TOKEN,value,queryText);
    }

}
