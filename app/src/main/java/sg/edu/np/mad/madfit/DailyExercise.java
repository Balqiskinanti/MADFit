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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;
import sg.edu.np.mad.madfit.Model.Common;
import sg.edu.np.mad.madfit.Model.Exercise;

public class DailyExercise extends AppCompatActivity {

    int image_id;
    String name;
    TextView title,time,txtGetReady,txtSkipTimer;
    ProgressBar progressBar;
    LinearLayout layoutTutorial;
    GifImageView detail_image;
    Button btnFinished, btnPause, btnStart;
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
        detail_image = findViewById(R.id.detail_image2);
        txtSkipTimer = findViewById(R.id.txtSkipTimer);
        txtGetReady = findViewById(R.id.txtGetReady);
        time = findViewById(R.id.timer_time);
        title = findViewById(R.id.workoutTitle);

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
                    showGetReady();
                    btnStart.setText("done");
                }
                else if(btnStart.getText().toString().toLowerCase().equals("done")){
                    if(madFitDBHandler.getSettingMode() == 0){
                        exercisesEasyModeCountDown.cancel();
                    }
                    else if(madFitDBHandler.getSettingMode() == 1){
                        exercisesMediumModeCountDown.cancel();
                    }
                    else if(madFitDBHandler.getSettingMode() == 2){
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
                        showFinished();
                    }
                }

            }
        });



    }

    private void showRestTime() {
        detail_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        time.setVisibility(View.INVISIBLE);

        layoutTutorial.setVisibility(View.VISIBLE);

        btnStart.setText("Skip");

        //restTimeCountDown.start();

        txtGetReady.setText("Rest Time");
    }

    private void showFinished() {

        detail_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);

        layoutTutorial.setVisibility(View.VISIBLE);

        txtGetReady.setText("FINISHED!!");
        txtSkipTimer.setText("Congrats! \nYou are done with today exercises");
        txtSkipTimer.setTextSize(20);

        //save workout done to DB
        madFitDBHandler.saveDay("" + Calendar.getInstance().getTimeInMillis());

        /*
        //Go to exercise finish page
        Intent intent = new Intent(DailyExercise.this,ExerciseFinished.class);
        startActivity(intent);
        if(madFitDBHandler.getSettingMode() == 0){
            exercisesEasyModeCountDown.cancel();
        }
        else if(madFitDBHandler.getSettingMode() == 1){
            exercisesMediumModeCountDown.cancel();
        }
        else if(madFitDBHandler.getSettingMode() == 2){
            exercisesHardModeCountDown.cancel();
        }

         */
    }

    private void showGetReady() {
        detail_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        time.setVisibility(View.VISIBLE);

        layoutTutorial.setVisibility(View.VISIBLE);

        txtGetReady.setText("GET READY!");
        new CountDownTimer(6000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                txtSkipTimer.setText(""+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                showExercise();
            }
        }.start();
    }

    private void showExercise() {
        if(ex_id < list.size()){
            detail_image.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            time.setVisibility(View.VISIBLE);

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
            title.setText(list.get(ex_id).getName());
        }
        else {
            showFinished();
        }
    }

    //Countdown timer
    CountDownTimer exercisesEasyModeCountDown = new CountDownTimer(Common.TIME_LIMIT_EASY,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
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
                showFinished();
            }
        }
    }.start();

    //Countdown timer
    CountDownTimer exercisesMediumModeCountDown = new CountDownTimer(Common.TIME_LIMIT_MEDIUM,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
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
                showFinished();
            }
        }
    }.start();

    //Countdown timer
    CountDownTimer exercisesHardModeCountDown = new CountDownTimer(Common.TIME_LIMIT_HARD,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
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
                showFinished();
            }
        }
    }.start();

    /*
    //Countdown timer
    CountDownTimer restTimeCountDown = new CountDownTimer(10000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            time.setText("" + millisUntilFinished/1000);
        }

        @Override
        public void onFinish() {
            setExerciseInformation(ex_id);
            showExercise();
        }
    }.start();

     */


    private void setExerciseInformation(int id) {
        detail_image.setImageResource(list.get(id).getImage_id());
        title.setText(list.get(id).getName());
        btnStart.setText("Start");

        detail_image.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        time.setVisibility(View.VISIBLE);

        layoutTutorial.setVisibility(View.INVISIBLE);

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