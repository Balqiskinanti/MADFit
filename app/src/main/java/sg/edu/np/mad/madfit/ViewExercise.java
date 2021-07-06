package sg.edu.np.mad.madfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class ViewExercise extends AppCompatActivity {

    int image_id;
    String name;
    TextView timer,title;
    GifImageView detail_image;
    Button btnSkip;

    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);

        timer = (TextView)findViewById(R.id.skipTimer);
        title = (TextView)findViewById(R.id.workTitle);
        detail_image = (GifImageView)findViewById(R.id.detail_image);

        btnSkip = (Button)findViewById(R.id.wSkipBtn);

        new CountDownTimer(5000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(""+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(ViewExercise.this, "Skipped",Toast.LENGTH_SHORT).show();
                finish();
            }
        }.start();

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewExercise.this,ListExercises.class);
                startActivity(intent);
            }
        });

        if(getIntent() != null){
            image_id = getIntent().getIntExtra("image_id",-1);
            name = getIntent().getStringExtra("name");

            detail_image.setImageResource(image_id);
            title.setText(name);
        }
    }
}