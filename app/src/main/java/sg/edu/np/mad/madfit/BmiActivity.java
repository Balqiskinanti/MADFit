package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BmiActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    Button newCalButton;
    TextView myHeight, myWeight, myBMI, myStatus;
    SharedPreferences sharedPreferences;
    public String GLOBAL_PREFS = "MyPrefs";
    public String MY_HEIGHT = "MyHeight";
    public String MY_WEIGHT = "MyWeight";
    public String MY_BMI = "MyBMI";
    public String MY_STATUS = "MyStatus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        /*
        Go to bmi calculator activity
        */
        newCalButton = findViewById(R.id.calBMI_button);
        newCalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BmiActivity.this, BmiCalculatorActivity.class);
                startActivity(intent);
            }
        });

        /*
        Get BMI data from storage and display to User
        */
        if (readBMIData() == true){
            sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
            String sharedHeight = sharedPreferences.getString(MY_HEIGHT,"No Records");
            String sharedWeight = sharedPreferences.getString(MY_WEIGHT,"No Records");
            String sharedBMI = sharedPreferences.getString(MY_BMI,"No Records");
            String sharedStatus = sharedPreferences.getString(MY_STATUS,"No Records");

            myHeight = findViewById(R.id.height_record);
            myWeight = findViewById(R.id.weight_record);
            myBMI = findViewById(R.id.bmi_record);
            myStatus = findViewById(R.id.status_record);

            myHeight.setText(sharedHeight + "m");
            myWeight.setText(sharedWeight + "kg");
            myBMI.setText(sharedBMI);
            myStatus.setText(sharedStatus);
        }

        /*
        Bottom navigation
        */
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

    /*
    retrieve BMI record from SharedPreferences
     */
    private boolean readBMIData(){
        sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        String sharedHeight = sharedPreferences.getString(MY_HEIGHT,"");
        String sharedWeight = sharedPreferences.getString(MY_WEIGHT,"");
        String sharedBMI = sharedPreferences.getString(MY_BMI,"");
        String sharedStatus = sharedPreferences.getString(MY_STATUS,"");

        if(sharedHeight == null || sharedWeight == null || sharedBMI == null || sharedStatus == null) {
            return false;
        }
        return true;
    }
}