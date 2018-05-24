package mert.android.com.witaipoc.home;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import mert.android.com.witaipoc.R;
import mert.android.com.witaipoc.network.RetrofitNetwork;
import mert.android.com.witaipoc.responsedata.ow.OWResponse;
import mert.android.com.witaipoc.responsedata.wit.WitResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    Button mRecordButton;
    private static final int RESULT_SPEECH =10;
    private static final String E_LOG_TAG = "SORUN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecordButton = findViewById(R.id.bt_record);



        mRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //permission check
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
                }
                    final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());
                            try {

                                startActivityForResult(intent, RESULT_SPEECH);
                            } catch (ActivityNotFoundException a) {

                                Toast.makeText(getApplicationContext(),

                                        "Opps! Your device doesn’t support Speech to Text",Toast.LENGTH_SHORT).show();
                            }

                            //The RecognizerIntent will convert the speech input to text and send back the result as ArrayList with key RecognizerIntent.EXTRA_RESULTS
                        }



        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    RetrofitNetwork.makeQuery("20180521",text.get(0)).enqueue(new Callback<WitResponse>() {
                        @Override
                        public void onResponse(Call<WitResponse> call, Response<WitResponse> response) {
                            //TODO:Yer bilgisi bulunamadığında veya sağlanmadığında otomatik almasını sağlamıyor şuan
                            if(response.body().getEntities()!=null) {
                                if(response.body().getEntities().getHavaDurumu()!=null)
                                System.out.println("WitAPI - Intent: " + response.body().getEntities().getHavaDurumu().get(0).getValue());
                                String city="NAN";

                                if(response.body().getEntities().getLocation()!=null) {
                                    System.out.println("WitAPI - Location: " + response.body().getEntities().getLocation().get(0).getValue());
                                    String location;
                                    location = response.body().getEntities().getLocation().get(0).getValue();
                                    if (location.contains("'")) {
                                        city = response.body().getEntities().getLocation().get(0).getValue().substring(0, location.length() - 3);
                                    } else {
                                        city = location;
                                    }
                                }
                                if(response.body().getEntities().getTime()!=null)
                                    System.out.println("WitAPI - Time: " + response.body().getEntities().getTime().get(0).getValue());
                                if(city.equals("NAN")) {
                                    Log.i(E_LOG_TAG, "Bir yer bilgisi bulunamadı!");
                                }else {
                                    //TODO:İstenen tarihteki hava durumunu vermiyor şuan listenin en başındakini veriyor.
                                        RetrofitNetwork.request5DayForecast(city).enqueue(new Callback<OWResponse>() {
                                        @Override
                                        public void onResponse(Call<OWResponse> call, Response<OWResponse> response) {

                                            System.out.println("OW - City :" + response.body().getCity().getName());
                                            System.out.println("OW - Temp :" + response.body().getList().get(0).getMain().getTemp());

                                        }

                                        @Override
                                        public void onFailure(Call<OWResponse> call, Throwable t) {

                                        }
                                    });
                                }
                            }else{
                                Log.i(E_LOG_TAG, "Herhangi bir entity bulunamadı!");}
                        }


                        @Override
                        public void onFailure(Call<WitResponse> call, Throwable t) {

                        }
                    });
                }break;
            }
        }
    }
}
