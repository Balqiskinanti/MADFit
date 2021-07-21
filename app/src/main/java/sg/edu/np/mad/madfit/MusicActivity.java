package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import sg.edu.np.mad.madfit.Adapter.ListMusicCategoryAdapter;
import sg.edu.np.mad.madfit.Model.MusicCategory;
import sg.edu.np.mad.madfit.Model.MusicItem;

public class MusicActivity extends AppCompatActivity {
    ArrayList<MusicCategory> musicCategoryArrayList;
    ArrayList<MusicItem> musicItemArrayList;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        // init data
        musicCategoryArrayList = new ArrayList<>();
        initMusicItemArray(1);
        MusicCategory mc1 = new MusicCategory(1,"Workout Marathon", musicItemArrayList);
        musicCategoryArrayList.add(mc1);

        initMusicItemArray(2);
        MusicCategory mc2 = new MusicCategory(2,"Quick Workout", musicItemArrayList);
        musicCategoryArrayList.add(mc2);

        initMusicItemArray(3);
        MusicCategory mc3 = new MusicCategory(3,"Background Music", musicItemArrayList);
        musicCategoryArrayList.add(mc3);

        // init RV
        ListMusicCategoryAdapter musicCategoryAdapter = new ListMusicCategoryAdapter(musicCategoryArrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView musicRV = findViewById(R.id.musicRV);
        musicRV.setLayoutManager(layoutManager);
        musicRV.setAdapter(musicCategoryAdapter);

        // Bottom navigation
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        Intent intent0 = new Intent(MusicActivity.this,MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(MusicActivity.this,WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(MusicActivity.this,FoodActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }

    /* init music item array -> repeated for all category*/
    private void initMusicItemArray(int catId){
        MusicItem m1 = new MusicItem(1,catId,"Cardio", "#ADD8E6",R.drawable.cardio_cover);
        MusicItem m2 = new MusicItem(2,catId,"Chill Run", "#FF87B0",R.drawable.chill_workout_cover);
        MusicItem m3 = new MusicItem(3,catId,"Dance", "#ECBAF4",R.drawable.dance_cover);
        musicItemArrayList = new ArrayList<>();
        musicItemArrayList.add(m1);
        musicItemArrayList.add(m2);
        musicItemArrayList.add(m3);
    }
}