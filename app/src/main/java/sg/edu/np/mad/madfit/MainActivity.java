package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    SharedPreferences sharedPreferences;
    public String GLOBAL_PREFS = "MyPrefs";
    public String MY_BMI = "MyBMI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set hello text with emoji
        TextView helloTxt = findViewById(R.id.textView16);
        int unicode = 0x1F44B;
        String emoji = getEmoji(unicode);
        String txt = "HELLO! "+ emoji;
        helloTxt.setText(txt);

        // Go to bmi activity
        ConstraintLayout calBMI_btn = findViewById(R.id.calBMI_btn);
        calBMI_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MainActivity.this, BmiActivity.class);
                startActivity(intent);
                return false;
            }
        });

        // display BMI
        if (readBMIData() == true){
            sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
            String sharedBMI = sharedPreferences.getString(MY_BMI,"0");

            TextView myBMI = findViewById(R.id.myBMI);
            myBMI.setText(sharedBMI);
        }

        // Go to music activity
        CardView musicCard = findViewById(R.id.musicCard);
        musicCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });

        // Go to notification activity
        CardView workoutReminderCard = findViewById(R.id.workoutReminderCard);
        workoutReminderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        //Go to mail activity
        CardView mailCard = findViewById(R.id.emailNotifCard);
        mailCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MailActivity.class);
                startActivity(intent);
            }
        });

        // Bottom navigation
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:

                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(MainActivity.this,WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(MainActivity.this,FoodActivity.class);
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

    private boolean readBMIData(){
        sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        String sharedBMI = sharedPreferences.getString(MY_BMI,"");

        if(sharedBMI == null) {
            return false;
        }
        return true;
    }
}