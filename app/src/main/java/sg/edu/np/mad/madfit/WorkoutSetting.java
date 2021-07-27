package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import sg.edu.np.mad.madfit.Model.Mode;

public class WorkoutSetting extends AppCompatActivity {

    private static final String TAG = "Workout Setting";

    // instantiate MADFitDBHandler
    MADFitDBHandler madFitDBHandler;

    // instantiate SharedPreferences
    SharedPreferences sharedPreferences;
    public String GLOBAL_PREFS = "MyPrefs";
    public String MUTE_PUSH_NOTIFS_SETTINGS = "MutePushNotifsSettings";

    // Layout
    Button easyBtn, mediumBtn, hardBtn;
    Switch muteReminderSwitch, skipTutorialSwitch;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_setting);

        setMuteWorkout();

        madFitDBHandler = new MADFitDBHandler(this);
        easyBtn = findViewById(R.id.easyBtn);
        mediumBtn = findViewById(R.id.mediumBtn);
        hardBtn = findViewById(R.id.hardBtn);
        skipTutorialSwitch = findViewById(R.id.skipTutorial);

        //get setting mode in database

        int mode = madFitDBHandler.getSettingMode();
        setButton(mode);

        int skip = madFitDBHandler.getTutorialSkip();
        setSkipSwitch(skip);


        //set mode and store in database
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

        skipTutorialSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isMuted) {
                if(isMuted){
                    madFitDBHandler.saveTutorialSkip(1);
                    Toast.makeText(WorkoutSetting.this, "Tutorial off", Toast.LENGTH_SHORT).show();
                }
                else{
                    madFitDBHandler.saveTutorialSkip(0);
                    Toast.makeText(WorkoutSetting.this, "Tutorial on", Toast.LENGTH_SHORT).show();
                }
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

    //set selected button in database to different color
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

    //set switch according to database database
    private void setSkipSwitch(int skip) {
        if(skip == 0){
            skipTutorialSwitch.setChecked(false);
        }
        else if(skip == 1){
            skipTutorialSwitch.setChecked(true);
        }
        else {
            skipTutorialSwitch.setChecked(false);
        }
    }

    /* Set Mute Workout*/
    private void setMuteWorkout(){
        sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        muteReminderSwitch = findViewById(R.id.muteReminderSwitch);
        muteReminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isMuted) {
                if(isMuted){
                    editor.putBoolean(MUTE_PUSH_NOTIFS_SETTINGS, true);
                    Toast.makeText(WorkoutSetting.this, "Muted Notification", Toast.LENGTH_SHORT).show();
                }
                else{
                    editor.putBoolean(MUTE_PUSH_NOTIFS_SETTINGS, false);
                    Toast.makeText(WorkoutSetting.this, "Unmuted Notification", Toast.LENGTH_SHORT).show();
                }
                editor.apply();
            }
        });

        // Get pref bool for first start up
        if(sharedPreferences.getBoolean(MUTE_PUSH_NOTIFS_SETTINGS,false)){
            muteReminderSwitch.setChecked(true);
        }
    }
}