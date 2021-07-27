package sg.edu.np.mad.madfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WorkoutActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    Button workStartButton;
    CardView workCalendarButton,workPlanButton;
    ImageView workSettingButton;
    TextView planEmoji, calendarEmoji;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        //set text with emoji
        planEmoji = findViewById(R.id.planTV);
        int unicode = 0X1f4DD;
        String emojiPlan = getEmoji(unicode);
        planEmoji.setText(emojiPlan);

        calendarEmoji = findViewById(R.id.calendarTV);
        int unicode2 = 0X1f4C5;
        String emojiCalendar = getEmoji(unicode2);
        calendarEmoji.setText(emojiCalendar);

        // Bottom navigation
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

        // Go to list exercises
        workStartButton = findViewById(R.id.workStartBtn);
        workStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here
                Intent intent = new Intent(WorkoutActivity.this,ListExercises.class);
                startActivity(intent);
            }
        });

        // Go to workout settings
        workSettingButton = findViewById(R.id.workSettingBtn);
        workSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this,WorkoutSetting.class);
                startActivity(intent);
            }
        });

        // Go to workout calendar
        workCalendarButton = findViewById(R.id.workCalendarBtn);
        workCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this,WorkoutCalendar.class);
                startActivity(intent);
            }
        });

        // Go to workout plan
        workPlanButton = findViewById(R.id.workPlanBtn);
        workPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this,WorkoutPlan.class);
                startActivity(intent);
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
