package mert.android.com.witaipoc.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mert.android.com.witaipoc.R;
import mert.android.com.witaipoc.network.RetrofitNetwork;
import mert.android.com.witaipoc.responsedata.ow.OWResponse;
import mert.android.com.witaipoc.responsedata.wit.WitResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitNetwork.makeQuery("20180521","Trabzonda hava perşembe günü nasıl?").enqueue(new Callback<WitResponse>() {
            @Override
            public void onResponse(Call<WitResponse> call, Response<WitResponse> response) {
                System.out.println("SUCCES");

            }

            @Override
            public void onFailure(Call<WitResponse> call, Throwable t) {

            }
        });
        RetrofitNetwork.request5DayForecast("Trabzon").enqueue(new Callback<OWResponse>() {
            @Override
            public void onResponse(Call<OWResponse> call, Response<OWResponse> response) {
                System.out.println("SUCCES");
            }

            @Override
            public void onFailure(Call<OWResponse> call, Throwable t) {

            }
        });
    }
}
