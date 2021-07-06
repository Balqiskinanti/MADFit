package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BmiActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    Button newCalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        newCalButton = findViewById(R.id.calBMI_button);
        newCalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BmiActivity.this, BmiCalculatorActivity.class);
                startActivity(intent);
            }
        });

        // Get BMI data from storage and display to User

        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        Intent intent3 = new Intent(BmiActivity.this,MainActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(BmiActivity.this,WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(BmiActivity.this,FoodActivity.class);
                        startActivity(intent2);
                        break;

                }
                return false;
            }

        });
    }
}