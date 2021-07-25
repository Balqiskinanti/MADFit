package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import sg.edu.np.mad.madfit.Adapter.WorkoutPlanAdapter;
import sg.edu.np.mad.madfit.Model.Plan;

public class WorkoutPlan extends AppCompatActivity {
    BottomNavigationView navigationView;
    Button createNewPlanBtn;
    TextView clearAllPlans;
    static ArrayList<Plan> planList;
    PlanDBHandler planDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan);

        planDBHandler  = new PlanDBHandler(this);

        /*
        Go to create new plan page
        */
        createNewPlanBtn = findViewById(R.id.createNewPlanBtn);
        createNewPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutPlan.this, WorkoutPlanCreate.class);
                startActivity(intent);
            }
        });

        /*
        Clear all plans
         */
        clearAllPlans = findViewById(R.id.clearAllPlans);
        clearAllPlans.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WorkoutPlan.this);
                builder.setTitle("Clear All Plans");
                builder.setMessage("Are you sure to clear all your workout plans?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        planDBHandler.deleteAllPlans();
                        Toast.makeText(WorkoutPlan.this, "All plans have been cleared", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(WorkoutPlan.this, WorkoutPlan.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

                return false;
            }
        });

        /*
        List & RecyclerView for Workout Plans
         */
        planList = planDBHandler.getPlans();
        RecyclerView workoutPlanRV = findViewById(R.id.workoutPlanRV);
        WorkoutPlanAdapter workoutPlanAdapter = new WorkoutPlanAdapter(this, planList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        workoutPlanRV.setLayoutManager(linearLayoutManager);
        workoutPlanRV.setAdapter(workoutPlanAdapter);

        /*
        Bottom navigation
        */
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_workout);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        Intent intent0 = new Intent(WorkoutPlan.this,MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(WorkoutPlan.this,WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(WorkoutPlan.this,FoodActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }
}