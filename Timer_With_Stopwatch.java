package com.arashad96.androiddeveloperintermidatekit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Timer_With_Stopwatch extends AppCompatActivity {
    Button github;
    Button info;
    SeekBar timebar;
    TextView timer;
    Button start;
    private int state = 0;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_timer__with__stopwatch);

        timebar = findViewById(R.id.timebar);
        timer = findViewById(R.id.timer);
        start = findViewById(R.id.start);

        timebar.setMax(3600000);
        timebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                NumberFormat f = new DecimalFormat("00");
                long hour = (i / 3600000) % 24;
                long min = (i / 60000) % 60;
                long sec = (i / 1000) % 60;

                timer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));

                //timer.setText(String.valueOf(i/1000));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (state == 0) {
                    countDownTimer = new CountDownTimer(timebar.getProgress(), 1000){
                        @Override
                        public void onTick(long l) {
                            //countdown is counting every second
                            timebar.setVisibility(view.INVISIBLE);
                            timebar.setProgress((int)l);
                            //Toast.makeText(MainActivity.this, " "+ l/1000, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFinish() {
                            timebar.setVisibility(view.VISIBLE);
                            start.setText("Start");
                            state=1;
                            //on finishing the count
                            Toast.makeText(Timer_With_Stopwatch.this, "Finished", Toast.LENGTH_SHORT).show();

                        }

                    }.start();

                    start.setText("Stop");
                    state=1;
                }//pressed stop
                else if (state == 1) {
                    Log.i("state ", timebar.getProgress()/1000+"");
                    timebar.setVisibility(view.VISIBLE);
                    countDownTimer.cancel();
                    start.setText("Start");
                    state=0;
                }
            }
        });


        github = findViewById(R.id.github);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ARashad96/Stopwatch"));
                startActivity(intent);
            }
        });
        info = findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new android.app.AlertDialog.Builder(Timer_With_Stopwatch.this)
                        .setIcon(R.drawable.profile)
                        .setTitle("App info")
                        .setMessage("This app is a simple stopwatch using textview, seekbar, button and linearlayout.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });
    }
}
