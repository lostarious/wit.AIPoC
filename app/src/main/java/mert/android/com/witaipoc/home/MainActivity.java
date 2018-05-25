package mert.android.com.witaipoc.home;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import mert.android.com.witaipoc.R;
import mert.android.com.witaipoc.databinding.ActivityMainBinding;
import mert.android.com.witaipoc.network.RetrofitNetwork;
import mert.android.com.witaipoc.responsedata.ow.OWResponse;
import mert.android.com.witaipoc.responsedata.wit.WitResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final int RESULT_SPEECH = 10;
    Button mRecordButton;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mRecordButton = findViewById(R.id.bt_record);
        mRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //permission check
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
                }
                final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                } catch (ActivityNotFoundException a) {

                    Toast.makeText(getApplicationContext(),

                            "Opps! Your device doesn’t support Speech to Text", Toast.LENGTH_SHORT).show();
                }

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
                    RetrofitNetwork.makeQuery("20180521", text.get(0)).enqueue(new Callback<WitResponse>() {
                        @Override
                        public void onResponse(Call<WitResponse> call, Response<WitResponse> response) {
                            //TODO:Yer bilgisi bulunamadığında veya sağlanmadığında otomatik almasını sağlamıyor şuan
                            if (response.body().getEntities() != null) {
                                binding.setEntities(response.body().getEntities());
                                String city = "";

                                if (binding.getEntities().getLocation() != null) {
                                    String location = binding.getEntities().getLocation().get(0).getValue();

                                    if (location.contains("'")) {
                                        city = response.body().getEntities().getLocation().get(0).getValue().substring(0, location.length() - 3);
                                    } else {
                                        city = location;
                                    }
                                }
                                //TODO:İstenen tarihteki hava durumunu vermiyor şuan listenin en başındakini veriyor.
                                if (binding.getEntities().getHavaDurumu() != null && binding.getEntities().getLocation() != null) {
                                    RetrofitNetwork.request5DayForecast(city).enqueue(new Callback<OWResponse>() {
                                        @Override
                                        public void onResponse(Call<OWResponse> call, Response<OWResponse> response) {
                                            binding.setHava(response.body());
                                        }

                                        @Override
                                        public void onFailure(Call<OWResponse> call, Throwable t) {
                                        }
                                    });
                                } else {
                                    binding.setHava(null);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<WitResponse> call, Throwable t) {

                        }
                    });
                }
                break;
            }
        }
    }
}
