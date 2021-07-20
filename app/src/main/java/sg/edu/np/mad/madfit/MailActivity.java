package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MailActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    EditText mEmail;
    Button sendEmail_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        sendEmail_btn = findViewById(R.id.sendEmail_Btn);
        sendEmail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });

        // Bottom navigation
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.nav_home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        Intent intent3 = new Intent(MailActivity.this,MainActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_workout:
                        Intent intent1 = new Intent(MailActivity.this,WorkoutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_food:
                        Intent intent2 = new Intent(MailActivity.this,FoodActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });

    }

    /*Execute Sending Email*/
    private void sendMail() {
        mEmail = findViewById(R.id.mEmail);
        String mail = mEmail.getText().toString().trim();
        String message = "";
        String subject = "MADFit Email Subscription";

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, subject, message);
        javaMailAPI.execute();
    }
}