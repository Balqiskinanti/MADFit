package sg.edu.np.mad.madfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;
import sg.edu.np.mad.madfit.Model.Common;
import sg.edu.np.mad.madfit.Model.Exercise;

public class DailyTraining extends AppCompatActivity {

    int image_id;
    String name;
    TextView title,time,txtGetReady,txtSkipTimer;
    ProgressBar progressBar;
    LinearLayout layoutTutorial;
    GifImageView detail_image;
    Button btnFinished, btnPause, start;
    BottomNavigationView navigationView;
    //CountDownTimer workoutTimer;
    boolean resume = true;
    long startMillis, milliLeft;
    MADFitDBHandler madFitDBHandler;

    int ex_id = 0, timeLimit = 0;
    List<Exercise> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_training);

        initData();
        //showGetReady();
        btnFinished = findViewById(R.id.wFinishBtn);
        btnPause = findViewById(R.id.wPauseBtn);
        title = findViewById(R.id.workoutTitle);
        time = findViewById(R.id.timer_time);

        detail_image = findViewById(R.id.detail_image2);
        txtGetReady = findViewById(R.id.txtGetReady);
        txtSkipTimer = findViewById(R.id.txtSkipTimer);
        layoutTutorial = findViewById(R.id.layout_tutorial);
        progressBar = findViewById(R.id.progressBar);
        start = findViewById(R.id.startBtn);

        //set data
        progressBar.setMax(list.size());
        setExerciseInformation(ex_id);

        madFitDBHandler = new MADFitDBHandler(this);
        //startMillis = 20000;

        //set time limit according to different mode
        if(madFitDBHandler.getSettingMode() == 0){
            timeLimit = Common.TIME_LIMIT_EASY;
        }
        else if(madFitDBHandler.getSettingMode() == 1){
            timeLimit = Common.TIME_LIMIT_MEDIUM;
        }
        else if(madFitDBHandler.getSettingMode() == 2){
            timeLimit = Common.TIME_LIMIT_HARD;
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGetReady();
            }
        });

        /*
        //pause button
        btnPause = findViewById(R.id.wPauseBtn);
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resume){
                    //Cancel timer when pause
                    btnPause.setText("Resume");
                    workoutTimer.cancel();
                }
                else {
                    //resume timer (start new timer with milliLeft)
                    btnPause.setText("Pause");
                    startMillis = milliLeft;
                    workoutTimer = new CountDownTimer(startMillis,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            time.setText("" + millisUntilFinished/1000);
                            milliLeft = millisUntilFinished;
                        }

                        @Override
                        public void onFinish() {
                            if(ex_id < list.size()){
                                ex_id++;
                                progressBar.setProgress(ex_id);
                                time.setText("");

                                setExerciseInformation(ex_id);
                            }
                            //Go to exercise finish page
                            //Intent intent = new Intent(DailyTraining.this,ExerciseFinished.class);
                            //startActivity(intent);
                            //Toast.makeText(DailyTraining.this, "Finish!!!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }.start();
                }
                resume = !resume;
            }
        });

         */

        /*
        // Go to exercise finished
        btnFinished = findViewById(R.id.wFinishBtn);
        btnFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ex_id < list.size()){
                    ex_id++;
                    progressBar.setProgress(ex_id);
                    time.setText("");

                    setExerciseInformation(ex_id);
                }

                else {
                    //Go to exercise finish page
                    Intent intent = new Intent(DailyTraining.this,ExerciseFinished.class);
                    startActivity(intent);
                }


                workoutTimer.cancel();
            }
        });

         */

        /*
        // Display image, title
        if(getIntent() != null){
            image_id = getIntent().getIntExtra("image_id",-1);
            name = getIntent().getStringExtra("name");

            detail_image.setImageResource(image_id);
            title.setText(name);
        }

         */

    }

    private void showGetReady() {
        /*
        detail_image.setVisibility(View.INVISIBLE);
        btnPause.setVisibility(View.INVISIBLE);
        btnFinished.setVisibility(View.INVISIBLE);
        time.setVisibility(View.VISIBLE);

        layoutTutorial.setVisibility(View.VISIBLE);

         */
        txtGetReady.setText("GET READY!");
        new CountDownTimer(6000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                txtSkipTimer.setText(""+ (millisUntilFinished)/1000);
            }

            @Override
            public void onFinish() {
                showExercise();
            }
        }.start();

    }

    private void showExercise() {
        //list size contains all exercises
        if(ex_id < list.size()){
            detail_image.setVisibility(View.VISIBLE);
            btnPause.setVisibility(View.VISIBLE);
            btnFinished.setVisibility(View.VISIBLE);
            time.setVisibility(View.VISIBLE);
            layoutTutorial.setVisibility(View.INVISIBLE);

            //set data
            detail_image.setImageResource(list.get(ex_id).getImage_id());
            title.setText(list.get(ex_id).getName());

            workoutTimer.start();
        }
        else{
            showFinished();
        }
    }

    private void showFinished() {
        //here tton
        Intent intent = new Intent(DailyTraining.this,ExerciseFinished.class);
        startActivity(intent);

        //save workout done to DB
        Toast.makeText(DailyTraining.this, "Finish!!!", Toast.LENGTH_SHORT).show();
        madFitDBHandler.saveDay("" + Calendar.getInstance().getTimeInMillis());
    }

    private void setExerciseInformation(int id) {
        detail_image.setImageResource(list.get(id).getImage_id());
        title.setText(list.get(id).getName());
        //btnStart???

        detail_image.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.VISIBLE);
        btnFinished.setVisibility(View.VISIBLE);
        time.setVisibility(View.VISIBLE);

        layoutTutorial.setVisibility(View.INVISIBLE);
    }

    //workoutTimer
    CountDownTimer workoutTimer = new CountDownTimer(timeLimit,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            time.setText("" + millisUntilFinished/1000);
            milliLeft = millisUntilFinished;
        }

        @Override
        public void onFinish() {
            if(ex_id < list.size()){
                ex_id++;
                progressBar.setProgress(ex_id);
                time.setText("");

                setExerciseInformation(ex_id);
            }
            //Toast.makeText(DailyTraining.this, "Finish!!!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }.start();

    private void initData() {
        list.add(new Exercise(R.drawable.exersice_1,"Push Up"));
        list.add(new Exercise(R.drawable.exersice_2,"Crunches"));
        list.add(new Exercise(R.drawable.exersice_3,"Triceps Dips"));
        list.add(new Exercise(R.drawable.exersice_4,"Bicycle Crunches"));
        list.add(new Exercise(R.drawable.exersice_5,"Leg Raise"));
        list.add(new Exercise(R.drawable.exersice_6,"Heel Touch"));
        list.add(new Exercise(R.drawable.exersice_7,"Leg Up Crunches"));
        list.add(new Exercise(R.drawable.exersice_8,"Sit Up"));
        list.add(new Exercise(R.drawable.exersice_9,"V Ups"));
        list.add(new Exercise(R.drawable.exersice_10,"Plank Rotation"));
        list.add(new Exercise(R.drawable.exersice_11,"Plank With Leg Left"));
        list.add(new Exercise(R.drawable.exersice_12,"Russian Twist"));
        list.add(new Exercise(R.drawable.exersice_13,"Bridge"));
        list.add(new Exercise(R.drawable.exersice_14,"Vertical Leg Crunches"));
        list.add(new Exercise(R.drawable.exersice_15,"Vertical Heel Touch"));
    }
}