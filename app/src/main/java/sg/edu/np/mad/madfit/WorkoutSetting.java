package sg.edu.np.mad.madfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WorkoutSetting extends AppCompatActivity {

    Button turnOnReminderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_setting);

        turnOnReminderBtn = findViewById(R.id.turnOnReminderBtn);
        turnOnReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutSetting.this,NotificationActivity.class);
                startActivity(intent);
            }
        });
    }
}