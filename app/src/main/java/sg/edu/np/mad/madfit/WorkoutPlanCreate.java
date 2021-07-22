package sg.edu.np.mad.madfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sg.edu.np.mad.madfit.Model.Plan;

public class WorkoutPlanCreate extends AppCompatActivity {
    Button cancelBtn, createBtn;
    EditText inputTitle, inputDesc, inputType, inputDur;
    String myPlanTitle, myPlanDesc, myPlanType, myPlanDur;
    PlanDBHandler planDBHandler = new PlanDBHandler(this, null, null , 1);

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
                Add new Plan to Database
                */
                Plan dbPlan = new Plan();
                dbPlan.setPlanTitle(myPlanTitle);
                dbPlan.setPlanDescription(myPlanDesc);
                dbPlan.setPlanType(myPlanType);
                dbPlan.setPlanDuration(myPlanDur);
                planDBHandler.addPlan(dbPlan);

                Intent intent = new Intent(WorkoutPlanCreate.this, WorkoutPlan.class);
                Toast.makeText(WorkoutPlanCreate.this,"Plan created successfully!",Toast.LENGTH_SHORT).show();
                startActivity(intent);
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
    }
}