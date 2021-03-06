package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import sg.edu.np.mad.madfit.Model.WorkoutDoneDecorator;

public class WorkoutCalendar extends AppCompatActivity {

    BottomNavigationView navigationView;
    MaterialCalendarView materialCalendarView;
    HashSet<CalendarDay> list = new HashSet<>();
    MADFitDBHandler madFitDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_calendar);

        madFitDBHandler = new MADFitDBHandler(this);
        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendar);

        //get workout day from database
        List<String> workoutDay = madFitDBHandler.getWorkoutDays();
        HashSet<CalendarDay> convertedList = new HashSet<>();
        //convert calendar day to date
        for(String value:workoutDay){
            convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
        }

        //display on calendar
        materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedList));

        // Bottom navigation
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_workout);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        Intent intent0 = new Intent(WorkoutCalendar.this,MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(WorkoutCalendar.this,WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(WorkoutCalendar.this,FoodActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }
}