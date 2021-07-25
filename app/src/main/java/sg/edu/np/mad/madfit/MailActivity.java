package sg.edu.np.mad.madfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MailActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    EditText mEmail;
    Button sendEmail_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        mEmail = findViewById(R.id.mEmail);
        sendEmail_btn = findViewById(R.id.sendEmail_Btn);
        sendEmail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkAvailable()){
                    if(mEmail.getText().toString().equals("")){
                        Toast.makeText(MailActivity.this, "Please provide your email", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        sendMail();
                    }
                }
                else{
                    noInternetAlert();
                }
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private void noInternetAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MailActivity.this);
        builder.setMessage("You are offline. Please check your internet connection to use this feature.");
        builder.setTitle("No Internet Connection");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}