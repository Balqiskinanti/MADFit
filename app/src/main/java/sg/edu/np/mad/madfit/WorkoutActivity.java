package sg.edu.np.mad.madfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WorkoutActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    Button workStartButton,workSettingButton,workCalendarButton;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_workout);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        Intent intent0 = new Intent(WorkoutActivity.this,MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_workout:

                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(WorkoutActivity.this,FoodActivity.class);
                        startActivity(intent2);
                        break;

                }
                return false;
            }

        });

        workStartButton = findViewById(R.id.workStartBtn);
        workStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this,ListExercises.class);
                startActivity(intent);
            }
        });

        workSettingButton = findViewById(R.id.workSettingBtn);
        workSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this,WorkoutSetting.class);
                startActivity(intent);
            }
        });
    }
}
