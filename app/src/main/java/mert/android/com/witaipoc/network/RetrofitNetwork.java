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
    public RetrofitNetwork(){
            Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(new GsonConverterFactory);
            retrofit = retrofitBuilder.build();
            client = retrofit.create(WitAPI.class);
    }
    public Call<WitResponse> makeQuery(String queryText){
            return client.makequery(queryText);
    }

}
