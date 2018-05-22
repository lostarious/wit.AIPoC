package mert.android.com.witaipoc.network;

import mert.android.com.witaipoc.responsedata.WitResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lostarious on 22.05.2018
 * Copyright (c) 2018 YGA to present
 * All rights reserved.
 */
public interface WitAPI {

    @GET("message?v=20180521")
    Call<WitResponse> makequery(
            @Query("q") String queryText);
}
