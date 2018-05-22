package mert.android.com.witaipoc.network;

import mert.android.com.witaipoc.responsedata.WitResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by lostarious on 22.05.2018
 * Copyright (c) 2018 YGA to present
 * All rights reserved.
 */
public interface WitAPI {

    @GET("message")
    Call<WitResponse> makequery(
            @Header("Authorization") String auth,
            @Query("v") String value ,
            @Query("q") String queryText
        );
}
