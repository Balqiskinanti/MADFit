package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import sg.edu.np.mad.madfit.Adapter.ItemMusicPlayerAdapter;
import sg.edu.np.mad.madfit.Adapter.ListMusicCategoryAdapter;
import sg.edu.np.mad.madfit.Model.MusicPlayer;

public class MusicPlayerActivity extends AppCompatActivity {
    private String TAG = "Music Player Activity";
    BottomNavigationView navigationView;
    ArrayList<String> musicPlayerArrayList;
    MADFitDBHandler db= new MADFitDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        // get list of music player from music category & item id
        Intent receivingEnd = getIntent();
        int itemId = receivingEnd.getIntExtra("itemId",1);
        int catId = receivingEnd.getIntExtra("catId",1);

        MusicPlayer mPlayer = db.getMusicPlayer(catId,itemId);
        musicPlayerArrayList = mPlayer.getMusicPlayerArrayList();

        // init RV
        ItemMusicPlayerAdapter musicPlayerAdapter = new ItemMusicPlayerAdapter(musicPlayerArrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView musicRV = findViewById(R.id.musicPlayerRV);
        musicRV.setLayoutManager(layoutManager);
        musicRV.setAdapter(musicPlayerAdapter);

        // Bottom navigation
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        Intent intent0 = new Intent(MusicPlayerActivity.this,MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(MusicPlayerActivity.this,WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(MusicPlayerActivity.this,FoodActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
    }
}