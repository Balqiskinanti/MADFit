package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity {

    private static final String TAG = "Notification Activity";

    SharedPreferences sharedPreferences;
    public String GLOBAL_PREFS = "MyPrefs";
    public String MY_WORKOUT_TIME = "MyWorkoutTime";

    BottomNavigationView navigationView;
    Button remindBtn;
    int mHour , mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        displayTimePicker();

        createNotificationChannel();


        // Bottom navigation
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_home:
                        Intent intent0 = new Intent(NotificationActivity.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(NotificationActivity.this, WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(NotificationActivity.this, FoodActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }

    /*Create notification channel*/
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = "MADFit Exercise Reminder Channel";
            String description = "This channel is to remind you to exercise";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("notifyExercise", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /*Display time picker, update edit text time */
    private void displayTimePicker() {
        sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        String timeInput = sharedPreferences.getString(MY_WORKOUT_TIME, "00:00");
        if(timeInput != null){
            String[] timeParts = timeInput.split(":");
            mHour = Integer.valueOf(timeParts[0]);
            mMinute = Integer.valueOf(timeParts[1]);
        }
        else{
            mHour = 00;
            mMinute = 00;
        }

        remindBtn = findViewById(R.id.remind_btn);
        remindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(NotificationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        mHour = hourOfDay;
                        mMinute = minutes;
                        setNotification(mHour, mMinute);
                        saveLastNotifs(mHour + ":" + mMinute);
                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
    }

    /*Set notification*/
    public void setNotification(int hour, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);

        Intent intent = new Intent(NotificationActivity.this, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(NotificationActivity.this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,
                pendingIntent);

        Toast.makeText(NotificationActivity.this, "Reminder set", Toast.LENGTH_SHORT).show();
    }

    public void saveLastNotifs(String timeInput){
        sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MY_WORKOUT_TIME, timeInput);
        editor.apply();
    }
}
