package sg.edu.np.mad.madfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;
import sg.edu.np.mad.madfit.Model.Exercise;

public class DailyTraining extends AppCompatActivity {

    int image_id;
    String name;
    TextView title,time,txtGetReady,txtSkipTimer;
    ProgressBar progressBar;
    LinearLayout layoutTutorial;
    GifImageView detail_image;
    Button btnFinished, btnPause;
    BottomNavigationView navigationView;
    CountDownTimer workoutTimer;
    boolean resume = true;
    long startMillis, milliLeft;
    MADFitDBHandler madFitDBHandler;

    int ex_id = 0;
    List<Exercise> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_training);

        initData();
        btnFinished = findViewById(R.id.wFinishBtn);
        btnPause = findViewById(R.id.wPauseBtn);
        title = findViewById(R.id.workoutTitle);
        time = findViewById(R.id.timer_time);

        detail_image = findViewById(R.id.detail_image);
        txtGetReady = findViewById(R.id.txtGetReady);
        txtSkipTimer = findViewById(R.id.txtSkipTimer);
        layoutTutorial = findViewById(R.id.layout_tutorial);
        progressBar = findViewById(R.id.progressBar);

        //set data
        progressBar.setMax(list.size());
        setExerciseInformation(ex_id);

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