package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
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
        TextView calBMI_btn = findViewById(R.id.calBMI_btn);
        calBMI_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MainActivity.this, BmiActivity.class);
                startActivity(intent);
                return false;
            }
        });

        // Go to music activity
        Button exploreMusic_btn = findViewById(R.id.exploreMusic_btn);
        exploreMusic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });

        // Go to notification activity
        Button setNotification_btn = findViewById(R.id.setNotification_btn);
        setNotification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
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
}