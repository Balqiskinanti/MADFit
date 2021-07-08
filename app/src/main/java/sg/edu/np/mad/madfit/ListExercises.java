package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import sg.edu.np.mad.madfit.Adapter.RecyclerViewAdapter;
import sg.edu.np.mad.madfit.Model.Exercise;

public class ListExercises extends AppCompatActivity {

    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercises);

        initData();
        recyclerView = (RecyclerView) findViewById(R.id.list_ex);
        adapter = new RecyclerViewAdapter(exerciseList,getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_workout);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        Intent intent0 = new Intent(ListExercises.this,MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(ListExercises.this,WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(ListExercises.this,FoodActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }

    private void initData() {
        exerciseList.add(new Exercise(R.drawable.exersice_1,"Push Up"));
        exerciseList.add(new Exercise(R.drawable.exersice_2,"Crunches"));
        exerciseList.add(new Exercise(R.drawable.exersice_3,"Triceps Dips"));
        exerciseList.add(new Exercise(R.drawable.exersice_4,"Bicycle Crunches"));
        exerciseList.add(new Exercise(R.drawable.exersice_5,"Leg Raise"));
        exerciseList.add(new Exercise(R.drawable.exersice_6,"Heel Touch"));
        exerciseList.add(new Exercise(R.drawable.exersice_7,"Leg Up Crunches"));
        exerciseList.add(new Exercise(R.drawable.exersice_8,"Sit Up"));
        exerciseList.add(new Exercise(R.drawable.exersice_9,"V Ups"));
        exerciseList.add(new Exercise(R.drawable.exersice_10,"Plank Rotation"));
        exerciseList.add(new Exercise(R.drawable.exersice_11,"Plank With Leg Left"));
        exerciseList.add(new Exercise(R.drawable.exersice_12,"Russian Twist"));
        exerciseList.add(new Exercise(R.drawable.exersice_13,"Bridge"));
        exerciseList.add(new Exercise(R.drawable.exersice_14,"Vertical Leg Crunches"));
        exerciseList.add(new Exercise(R.drawable.exersice_15,"Vertical Heel Touch"));
    }
}