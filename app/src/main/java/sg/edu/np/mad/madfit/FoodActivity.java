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
    TextView dateToday, calsNeeded, calsConsumed, myBreakfastCals, myLunchCals, myDinnerCals, myOthersCals;
    String myTargetCal;
    Button setTargetBtn, calCounterBtn;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        dailyCalInput = findViewById(R.id.etDailyCalTarget);
        calsNeeded = findViewById(R.id.caloriesNeeded);

        /*
        Display Date in dashboard
        */
        dateToday = findViewById(R.id.dateToday);
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        int dayWeek = c.get(Calendar.DAY_OF_WEEK);
        List dayWkList = initDayWeek();
        String date = dayWkList.get(dayWeek-1) + " " + day + "/" + (month+1) + "/" + year;
        dateToday.setText(date);

        /*
        Set Daily Calorie target and insert into TextView in dashboard
         */
        setTargetBtn = findViewById(R.id.setTarget_btn);
        setTargetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTargetCal = dailyCalInput.getText().toString();
                if(myTargetCal.isEmpty()){
                    calsNeeded.setText("0 cals");
                } else {
                    calsNeeded.setText(myTargetCal + " cals");
                }
                Toast.makeText(FoodActivity.this,"Calorie target set successfully!",Toast.LENGTH_SHORT).show();
            }
        });

        /*
        Go to Calorie Counter page
         */
        calCounterBtn = findViewById(R.id.calCounter_btn);
        calCounterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, CalorieCounter.class);
                intent.putExtra("Date", date);
                startActivity(intent);
            }
        });

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
