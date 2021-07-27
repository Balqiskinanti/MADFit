package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import sg.edu.np.mad.madfit.Model.Plan;

public class WorkoutPlanCreate extends AppCompatActivity {
    BottomNavigationView navigationView;
    Button cancelBtn, createBtn;
    EditText inputTitle, inputDesc, inputType, inputDur;
    String myPlanTitle, myPlanDesc, myPlanType, myPlanDur;
    PlanDBHandler planDBHandler = new PlanDBHandler(this);
    boolean validInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_create);

        inputTitle = findViewById(R.id.planTitle_Input);
        inputDesc = findViewById(R.id.planDesc_Input);
        inputType = findViewById(R.id.planType_Input);
        inputDur = findViewById(R.id.planDuration_Input);

        /*
        Create new workout plan
        */
        createBtn = findViewById(R.id.create_btn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPlanTitle = inputTitle.getText().toString();
                myPlanDesc = inputDesc.getText().toString();
                myPlanType = inputType.getText().toString();
                myPlanDur = inputDur.getText().toString();

                /*
                Check for null values
                 */
                if (myPlanTitle.isEmpty()) {
                    Toast.makeText(WorkoutPlanCreate.this, "Please enter a title", Toast.LENGTH_SHORT).show();
                    validInput = false;
                } else
                    validInput = true;
                if (myPlanDesc.isEmpty()) {
                    Toast.makeText(WorkoutPlanCreate.this, "Please enter a description", Toast.LENGTH_SHORT).show();
                    validInput = false;
                } else
                    validInput = true;
                if (myPlanType.isEmpty()) {
                    Toast.makeText(WorkoutPlanCreate.this, "Please enter exercise type", Toast.LENGTH_SHORT).show();
                    validInput = false;
                } else
                    validInput = true;
                if (myPlanDur.isEmpty()) {
                    Toast.makeText(WorkoutPlanCreate.this, "Please enter exercise duration", Toast.LENGTH_SHORT).show();
                    validInput = false;
                } else
                    validInput = true;

                /*
                Add new Plan to Database
                */
                if (validInput == true) {
                    Plan dbPlan = new Plan();
                    dbPlan.setPlanTitle(myPlanTitle);
                    dbPlan.setPlanDescription(myPlanDesc);
                    dbPlan.setPlanType(myPlanType);
                    dbPlan.setPlanDuration(myPlanDur);
                    planDBHandler.addPlan(dbPlan);

                    Intent intent = new Intent(WorkoutPlanCreate.this, WorkoutPlan.class);
                    Toast.makeText(WorkoutPlanCreate.this, "Plan created successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });

        cancelBtn = findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutPlanCreate.this, WorkoutPlan.class);
                startActivity(intent);
            }
        });

                /*
        Bottom navigation
        */
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_workout);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_home:
                        Intent intent3 = new Intent(WorkoutPlanCreate.this, MainActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(WorkoutPlanCreate.this, WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(WorkoutPlanCreate.this, FoodActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }
}