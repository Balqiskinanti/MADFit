package sg.edu.np.mad.madfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import sg.edu.np.mad.madfit.Adapter.RecyclerViewAdapter;
import sg.edu.np.mad.madfit.Model.Exercise;

public class ListExercises extends AppCompatActivity {

    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

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