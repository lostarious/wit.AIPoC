package mert.android.com.witaipoc.audio;

import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by lostarious on 23.05.2018
 * Copyright (c) 2018 YGA to present
 * All rights reserved.
 */
public class AudioRecorder {
    private  MediaRecorder recorder = new MediaRecorder();

    public AudioRecorder() throws IOException {
        File audioFile = new File(Environment.getExternalStorageDirectory(),
                "input.3gp");
            recorder.setOutputFile(audioFile.getAbsolutePath());
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);

            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            recorder.prepare();

    }

    public  void startRecording(){

        recorder.start();

    }
    public  void stopRecording(){
        recorder.stop();
        recorder.release();
    }


}
