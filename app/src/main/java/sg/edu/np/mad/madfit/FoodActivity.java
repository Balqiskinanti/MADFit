package sg.edu.np.mad.madfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FoodActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    EditText dailyCalInput;
    TextView calNeeded;
    String myCal;
    Button setTargetBtn, calCounterBtn;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        dailyCalInput = findViewById(R.id.etDailyCalTarget);
        calNeeded = findViewById(R.id.caloriesNeeded);

        /*
        Go to Calorie Counter page
         */
        calCounterBtn = findViewById(R.id.calCounter_btn);
        calCounterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, CalorieCounter.class);
                startActivity(intent);
            }
        });

        /*
        Display Date in dashboard
         */
        TextView currentDT = findViewById(R.id.currentDT);
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        int dayWeek = c.get(Calendar.DAY_OF_WEEK);
        List dayWkList = initDayWeek();
        String date = dayWkList.get(dayWeek-1) + " " + day + "/" + (month+1) + "/" + year;
        currentDT.setText(date);

        /*
        Bottom navigation
        */
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_food);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        Intent intent0 = new Intent(FoodActivity.this,MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(FoodActivity.this,WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:

                        break;
                }
                return false;
            }
        });
    }

    /*
    initialise list of Days in a Week
     */
    private List initDayWeek(){
        List dayWkList = new ArrayList();
        dayWkList.add("Sunday");
        dayWkList.add("Monday");
        dayWkList.add("Tuesday");
        dayWkList.add("Wednesday");
        dayWkList.add("Thursday");
        dayWkList.add("Friday");
        dayWkList.add("Saturday");
        return dayWkList;
    }
}
