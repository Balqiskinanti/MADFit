package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pl.droidsonroids.gif.GifImageView;

public class ExerciseFinish extends AppCompatActivity {

    BottomNavigationView navigationView;
    TextView congrats;
    SharedPreferences sharedPreferences;
    public String GLOBAL_PREFS = "MyPrefs";
    public String WORKOUTTIME = "MyWorkoutTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_finish);

        //set text with emoji
        congrats = findViewById(R.id.congrat);
        int unicode = 0X1F389;
        String emoji = getEmoji(unicode);
        congrats.setText("Congrats! " +emoji);

        //get int extra total time for exercise
        int totalTiming = getIntent().getIntExtra("time", 1);
        Toast.makeText(ExerciseFinish.this, "Total Time: " + totalTiming, Toast.LENGTH_SHORT).show();
        TextView total = (TextView) findViewById(R.id.totalTime);

        //store timing in SharePrefs
        sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(WORKOUTTIME, String.valueOf(totalTiming));
        editor.apply();

        try {
            total.setText(totalTiming + " s");
        } catch (NullPointerException e) {
            Log.v("Debug: " , "" + e);
        }

        // Bottom navigation
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_workout);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        Intent intent0 = new Intent(ExerciseFinish.this,MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(ExerciseFinish.this,WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(ExerciseFinish.this,FoodActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }

    /*
    Unicode integer -> String
    */
    public String getEmoji(int uni){
        return new String(Character.toChars(uni));
    }

}

