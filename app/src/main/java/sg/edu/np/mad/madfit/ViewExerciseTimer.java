package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pl.droidsonroids.gif.GifImageView;

public class ViewExerciseTimer extends AppCompatActivity {

    int image_id;
    String name;
    TextView title,time;
    GifImageView detail_image;
    Button btnFinished, btnPause;
    BottomNavigationView navigationView;
    CountDownTimer workoutTimer;
    boolean resume = true;
    long startMillis, milliLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise_timer);

        title = findViewById(R.id.workoutTitle);
        time = findViewById(R.id.timer_time);
        detail_image = findViewById(R.id.detail_image2);
        startMillis = 20000;

        workoutTimer = new CountDownTimer(startMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("" + millisUntilFinished/1000);
                milliLeft = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                Toast.makeText(ViewExerciseTimer.this, "Finish!!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }.start();

        btnPause = findViewById(R.id.wPauseBtn);
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resume){
                    btnPause.setText("Resume");
                    workoutTimer.cancel();
                }
                else {
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
                            Intent intent = new Intent(ViewExerciseTimer.this,ExerciseFinished.class);
                            startActivity(intent);
                            Toast.makeText(ViewExerciseTimer.this, "Finish!!!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }.start();
                }
                resume = !resume;
            }
        });

        // Go to exercise finished
        btnFinished = findViewById(R.id.wFinishBtn);
        btnFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewExerciseTimer.this,ExerciseFinished.class);
                startActivity(intent);
                workoutTimer.cancel();
            }
        });

        // Display image, title
        if(getIntent() != null){
            image_id = getIntent().getIntExtra("image_id",-1);
            name = getIntent().getStringExtra("name");

            detail_image.setImageResource(image_id);
            title.setText(name);
        }


        // Bottom navigation
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_workout);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        Intent intent0 = new Intent(ViewExerciseTimer.this,MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(ViewExerciseTimer.this,WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(ViewExerciseTimer.this,FoodActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }
}