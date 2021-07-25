package sg.edu.np.mad.madfit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
    int totalCalsConsumed, progressPercent;
    Button setTargetBtn, calCounterBtn;
    CalorieDBHandler calorieDBHandler;
    SharedPreferences sharedPreferences;
    public String GLOBAL_PREFS = "MyPrefs";
    public String TODAYDATE = "TodayDate";
    public String TOTALBREAKFAST = "TotalBreakfast";
    public String TOTALLUNCH = "TotalLunch";
    public String TOTALDINNER = "TotalDinner";
    public String TOTALOTHERS = "TotalOthers";
    public String CALSNEEDED = "CalsNeeded";

    private static final String TAG = "FoodActivity";

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        //Set calories values
        setCalsValues();

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
        Save date to SharedPreferences
         */
        SharedPreferences prefs = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        String valueDate = prefs.getString(TODAYDATE, " ");
        if (valueDate.equals(date) || valueDate.isEmpty())
        {
            //same day
            sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(TODAYDATE, date);
            editor.apply();
        } else {
            //different day
            sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(TODAYDATE, date);

            /*
            Remove data from previous day
             */
            calorieDBHandler = new CalorieDBHandler(this);
            calorieDBHandler.deleteAllFoodItems();
            editor.putString(CALSNEEDED, "0");
            editor.putString(TOTALBREAKFAST, "0");
            editor.putString(TOTALLUNCH, "0");
            editor.putString(TOTALDINNER, "0");
            editor.putString(TOTALOTHERS, "0");
            totalCalsConsumed = 0;
            setCalsValues();

            editor.apply();
        }

        /*
        Set Daily Calorie target and insert into TextView in dashboard
         */
        dailyCalInput = findViewById(R.id.etDailyCalTarget);

        setTargetBtn = findViewById(R.id.setTarget_btn);
        setTargetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTargetCal = dailyCalInput.getText().toString();

                if(myTargetCal.equals("")){
                    Toast.makeText(FoodActivity.this, "Please set a target!", Toast.LENGTH_SHORT).show();
                } else {
                    calsNeeded.setText(myTargetCal + " cals");

                    sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(CALSNEEDED, myTargetCal);
                    editor.apply();
                    Toast.makeText(FoodActivity.this,"Calorie target set successfully!",Toast.LENGTH_SHORT).show();

                    setProgress(Integer.parseInt(myTargetCal), totalCalsConsumed);
                }
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

    private void setCalsValues(){
        SharedPreferences prefs = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        String valueBreakfast = prefs.getString(TOTALBREAKFAST, "0");
        String valueLunch = prefs.getString(TOTALLUNCH, "0");
        String valueDinner = prefs.getString(TOTALDINNER, "0");
        String valueOthers = prefs.getString(TOTALOTHERS, "0");
        String valueNeeded = prefs.getString(CALSNEEDED, "0");

        myBreakfastCals = findViewById(R.id.calsBreakfast);
        myLunchCals = findViewById(R.id.calsLunch);
        myDinnerCals = findViewById(R.id.calsDinner);
        myOthersCals = findViewById(R.id.calsOthers);
        calsNeeded = findViewById(R.id.caloriesNeeded);

        myBreakfastCals.setText(valueBreakfast + " cals");
        myLunchCals.setText(valueLunch + " cals");
        myDinnerCals.setText(valueDinner + " cals");
        myOthersCals.setText(valueOthers + " cals");
        calsNeeded.setText(valueNeeded + " cals");

        totalCalsConsumed = Integer.parseInt(valueBreakfast) + Integer.parseInt(valueLunch) + Integer.parseInt(valueDinner) + Integer.parseInt(valueOthers);
        calsConsumed = findViewById(R.id.caloriesConsumed);
        calsConsumed.setText(totalCalsConsumed + " cals");

        setProgress(Integer.parseInt(valueNeeded), totalCalsConsumed);
    }

    /*
    Set ProgressBar based on calories needed & consumed
     */
    private void setProgress(int calsNeeded, int totalCalsConsumed){
        if (totalCalsConsumed != 0 && calsNeeded != 0)
        {
            progressPercent = totalCalsConsumed * 100 / calsNeeded;
            int progress = Math.round(progressPercent);

            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setMax(100);
            progressBar.setProgress(Integer.valueOf(progress));
        } else {
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setMax(100);
            progressBar.setProgress(0);
        }
    }
}
