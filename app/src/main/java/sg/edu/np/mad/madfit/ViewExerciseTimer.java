package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ViewExerciseTimer extends AppCompatActivity {

    Button btnFinished;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise_timer);

        btnFinished = (Button)findViewById(R.id.wFinishBtn);
        btnFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewExerciseTimer.this,ExerciseFinished.class);
                startActivity(intent);
            }
        });

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