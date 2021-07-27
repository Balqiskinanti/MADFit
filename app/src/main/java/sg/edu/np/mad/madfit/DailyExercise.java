package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
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

public class DailyExercise extends AppCompatActivity {

    TextView title,time,txtSkipTimer,txtStart;
    ProgressBar progressBar;
    LinearLayout layoutTutorial;
    GifImageView detail_image;
    Button btnSkip, btnStart;
    BottomNavigationView navigationView;
    MADFitDBHandler madFitDBHandler;

    int ex_id = 0, totalTime = 0;
    long timeLimit = 0;
    List<Exercise> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_exercise);

        // Bottom navigation
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_workout);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        Intent intent0 = new Intent(DailyExercise.this,MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(DailyExercise.this,WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(DailyExercise.this,FoodActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });

        initData();

        madFitDBHandler = new MADFitDBHandler(this);

        btnStart = findViewById(R.id.startButton);
        btnSkip = findViewById(R.id.skipButton);
        detail_image = findViewById(R.id.detail_image2);
        txtSkipTimer = findViewById(R.id.txtSkipTimer);
        time = findViewById(R.id.timer_time);
        title = findViewById(R.id.workoutTitle);
        txtStart = findViewById(R.id.StartText);

        layoutTutorial = findViewById(R.id.layout_tutorial);

        progressBar = findViewById(R.id.progressBar);

        //set data
        progressBar.setMax(list.size());

        detail_image.setImageResource(R.drawable.exersice_1);


        exercisesEasyModeCountDown.cancel();
        exercisesMediumModeCountDown.cancel();
        exercisesHardModeCountDown.cancel();

        //setExerciseInformation(ex_id);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnStart.getText().toString().toLowerCase().equals("start")){
                    if(madFitDBHandler.getTutorialSkip() == 0){
                        showGetReady();
                    }
                    else {
                        showExercise();
                    }
                    btnStart.setText("done");
                }
                else if(btnStart.getText().toString().toLowerCase().equals("done")){
                    if(madFitDBHandler.getSettingMode() == 0){
                        totalTime += Common.TIME_LIMIT_EASY - timeLimit;
                        exercisesEasyModeCountDown.cancel();
                    }
                    else if(madFitDBHandler.getSettingMode() == 1){
                        totalTime += Common.TIME_LIMIT_MEDIUM -  timeLimit;
                        exercisesMediumModeCountDown.cancel();
                    }
                    else if(madFitDBHandler.getSettingMode() == 2){
                        totalTime += Common.TIME_LIMIT_HARD -  timeLimit;
                        exercisesHardModeCountDown.cancel();
                    }

                    //restTimeCountDown.cancel();
                    if(ex_id < list.size()){
                        //showRestTime();
                        ex_id++;
                        progressBar.setProgress(ex_id);
                        time.setText("");

                        setExerciseInformation(ex_id);
                        btnStart.setText("Start");
                    }
                    else {
                        setExerciseInformation(ex_id);
                    }

                }

            }
        });
    }


    private void showFinished() {

        Toast.makeText(DailyExercise.this, "FINISHED!",Toast.LENGTH_SHORT).show();
        totalTime = totalTime/1000;
        //Toast.makeText(DailyExercise.this, "TotalTime: " + totalTime, Toast.LENGTH_SHORT).show();

        //save workout done to DB
        madFitDBHandler.saveDay("" + Calendar.getInstance().getTimeInMillis());

        //Go to exercise finish page
        Intent intent = new Intent(DailyExercise.this,ExerciseFinish.class);
        intent.putExtra("time",totalTime);
        startActivity(intent);
        exercisesEasyModeCountDown.cancel();
        exercisesMediumModeCountDown.cancel();
        exercisesHardModeCountDown.cancel();
    }

    private void showGetReady() {
        detail_image.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        btnSkip.setVisibility(View.VISIBLE);
        time.setVisibility(View.VISIBLE);

        layoutTutorial.setVisibility(View.VISIBLE);

        //txtGetReady.setText("GET READY!");
        txtStart.setText("GET READY!");

        skipTimer.start();

        // Go to view exercise timer
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipTimer.cancel();
                showExercise();
                //Toast.makeText(DailyExercise.this, "Skipped",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void showExercise() {
        if(ex_id < list.size()){
            detail_image.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            btnSkip.setVisibility(View.INVISIBLE);
            time.setVisibility(View.VISIBLE);
            txtStart.setText("START !");

            layoutTutorial.setVisibility(View.INVISIBLE);

            if(madFitDBHandler.getSettingMode() == 0){
                exercisesEasyModeCountDown.start();
            }
            else if(madFitDBHandler.getSettingMode() == 1){
                exercisesMediumModeCountDown.start();
            }
            else if(madFitDBHandler.getSettingMode() == 2){
                exercisesHardModeCountDown.start();
            }

            //set data
            detail_image.setImageResource(list.get(ex_id).getImage_id());
            title.setText(list.get(ex_id).getName() + " x10");
        }
    }

    //Countdown timer
    CountDownTimer exercisesEasyModeCountDown = new CountDownTimer(Common.TIME_LIMIT_EASY,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timeLimit = millisUntilFinished;
            time.setText("" + millisUntilFinished/1000);
        }

        @Override
        public void onFinish() {
            if(ex_id < list.size()){
                ex_id++;
                progressBar.setProgress(ex_id);
                time.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText("Start");
            }
            else {
                setExerciseInformation(ex_id);
            }
            totalTime += Common.TIME_LIMIT_EASY;
        }
    }.start();

    //Countdown timer
    CountDownTimer exercisesMediumModeCountDown = new CountDownTimer(Common.TIME_LIMIT_MEDIUM,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timeLimit = millisUntilFinished;
            time.setText("" + millisUntilFinished/1000);
        }

        @Override
        public void onFinish() {
            if(ex_id < list.size()){
                ex_id++;
                progressBar.setProgress(ex_id);
                time.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText("Start");
            }
            else {
                setExerciseInformation(ex_id);
            }
            totalTime += Common.TIME_LIMIT_MEDIUM;

        }
    }.start();

    //Countdown timer
    CountDownTimer exercisesHardModeCountDown = new CountDownTimer(Common.TIME_LIMIT_HARD,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timeLimit = millisUntilFinished;
            time.setText("" + millisUntilFinished/1000);
        }

        @Override
        public void onFinish() {
            if(ex_id < list.size()){
                ex_id++;
                progressBar.setProgress(ex_id);
                time.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText("Start");
            }
            else {
                setExerciseInformation(ex_id);
            }
            totalTime += Common.TIME_LIMIT_HARD;
        }
    }.start();

    CountDownTimer skipTimer = new CountDownTimer(6000,1000){

        @Override
        public void onTick(long millisUntilFinished) {
            txtSkipTimer.setText(""+millisUntilFinished/1000);
        }

        @Override
        public void onFinish() {
            showExercise();
        }
    }.start();

    private void setExerciseInformation(int id) {
        if(id < list.size()){
            detail_image.setImageResource(list.get(id).getImage_id());
            title.setText(list.get(id).getName() + " x10");
            btnStart.setText("Start");

            detail_image.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            time.setVisibility(View.VISIBLE);

            layoutTutorial.setVisibility(View.INVISIBLE);
            btnSkip.setVisibility(View.INVISIBLE);
        }
        else {
            showFinished();
        }


    }

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