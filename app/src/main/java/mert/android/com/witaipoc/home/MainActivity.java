package mert.android.com.witaipoc.home;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import mert.android.com.witaipoc.R;
import mert.android.com.witaipoc.audio.AudioRecorder;
import mert.android.com.witaipoc.network.RetrofitNetwork;
import mert.android.com.witaipoc.responsedata.ow.OWResponse;
import mert.android.com.witaipoc.responsedata.wit.WitResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    Button mRecordButton;
    boolean currentlyRecording = false;
    AudioRecorder audioRecorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecordButton = findViewById(R.id.bt_record);
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

        mRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //permission check
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
                }
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
                }
                //Oynatıyorsa durdur , yoksa başlat
                if(!currentlyRecording) {
                    try {
                        audioRecorder = new AudioRecorder();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    audioRecorder.startRecording();
                    currentlyRecording = true;
                    }
                else{
                   audioRecorder.stopRecording();
                   currentlyRecording=false;
                }
                }
        });


    }
}
