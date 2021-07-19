package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import sg.edu.np.mad.madfit.Model.Mode;

public class WorkoutSetting extends AppCompatActivity {

    private static final String TAG = "Workout Setting";
    Button turnOnReminderBtn, easyBtn, mediumBtn, hardBtn;
    BottomNavigationView navigationView;
    MADFitDBHandler madFitDBHandler = new MADFitDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_setting);

        easyBtn = findViewById(R.id.easyBtn);
        mediumBtn = findViewById(R.id.mediumBtn);
        hardBtn = findViewById(R.id.hardBtn);

        /*
        easyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Mode dbMode = new Mode();
                dbMode.setMode(1);
                madFitDBHandler.addMode(dbMode);
                Log.v(TAG,"db created");
            }
        });

         */


        int mode = madFitDBHandler.getSettingMode();
        setButton(mode);

        easyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyBtn.setBackgroundColor(Color.rgb(88,104,224));
                mediumBtn.setBackgroundColor(Color.WHITE);
                hardBtn.setBackgroundColor(Color.WHITE);
                madFitDBHandler.saveSettingMode(0);
                Toast.makeText(WorkoutSetting.this, "SAVED!",Toast.LENGTH_SHORT).show();
            }
        });
        mediumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediumBtn.setBackgroundColor(Color.rgb(88,104,224));
                easyBtn.setBackgroundColor(Color.WHITE);
                hardBtn.setBackgroundColor(Color.WHITE);
                madFitDBHandler.saveSettingMode(1);
                Toast.makeText(WorkoutSetting.this, "SAVED!",Toast.LENGTH_SHORT).show();
            }
        });
        hardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hardBtn.setBackgroundColor(Color.rgb(88,104,224));
                easyBtn.setBackgroundColor(Color.WHITE);
                mediumBtn.setBackgroundColor(Color.WHITE);
                madFitDBHandler.saveSettingMode(2);
                Toast.makeText(WorkoutSetting.this, "SAVED!",Toast.LENGTH_SHORT).show();
            }
        });




        // Go to notification activity
        turnOnReminderBtn = findViewById(R.id.turnOnReminderBtn);
        turnOnReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutSetting.this,NotificationActivity.class);
                startActivity(intent);
            }
        });

        // Bottom navigation
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_workout);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        Intent intent0 = new Intent(WorkoutSetting.this,MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(WorkoutSetting.this,WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(WorkoutSetting.this,FoodActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }

    private void setButton(int mode) {
        if(mode == 0){
            easyBtn.setBackgroundColor(Color.rgb(88,104,224));
        }
        else if(mode == 1){
            mediumBtn.setBackgroundColor(Color.rgb(88,104,224));
        }
        else if(mode == 2){
            hardBtn.setBackgroundColor(Color.rgb(88,104,224));
        }
    }

}