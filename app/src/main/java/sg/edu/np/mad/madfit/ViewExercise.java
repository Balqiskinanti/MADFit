package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pl.droidsonroids.gif.GifImageView;

public class ViewExercise extends AppCompatActivity {

    int image_id;
    String name;
    TextView timer,title;
    GifImageView detail_image;
    Button btnSkip;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);

        timer = (TextView)findViewById(R.id.skipTimer);
        title = (TextView)findViewById(R.id.workTitle);
        detail_image = (GifImageView)findViewById(R.id.detail_image);

        // Display countdown timer
        // Go to view exercise timer when finish
        CountDownTimer skipTimer = new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(""+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(ViewExercise.this,ViewExerciseTimer.class);
                intent.putExtra("image_id",image_id);
                intent.putExtra("name",name);
                startActivity(intent);
                finish();
            }
        }.start();

        // Display image, title
        if(getIntent() != null){
            image_id = getIntent().getIntExtra("image_id",-1);
            name = getIntent().getStringExtra("name");

            detail_image.setImageResource(image_id);
            title.setText(name);
        }

        // Go to view exercise timer
        btnSkip = findViewById(R.id.wSkipBtn);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipTimer.cancel();
                Intent intent = new Intent(ViewExercise.this,ViewExerciseTimer.class);
                intent.putExtra("image_id",image_id);
                intent.putExtra("name",name);
                startActivity(intent);
                Toast.makeText(ViewExercise.this, "Skipped",Toast.LENGTH_SHORT).show();
            }
        });

        // Bottom navigation
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_workout);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        Intent intent0 = new Intent(ViewExercise.this,MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(ViewExercise.this,WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(ViewExercise.this,FoodActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }
}