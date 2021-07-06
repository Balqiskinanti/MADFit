package sg.edu.np.mad.madfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewExerciseTimer extends AppCompatActivity {

    Button btnFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise_timer);

        btnFinished = (Button)findViewById(R.id.wFinishBtn);
        btnFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewExerciseTimer.this,ExerciseFinished.class);
                startActivity(intent);
            }
        });
    }
}