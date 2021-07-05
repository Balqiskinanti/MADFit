package sg.edu.np.mad.madfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BmiCalculatorActivity extends AppCompatActivity {
    EditText myHeightInput, myWeightInput;
    Button cancelBtn, calBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        myHeightInput = findViewById(R.id.height_input);
        myWeightInput = findViewById(R.id.weight_input);

        calBtn = findViewById(R.id.cal_btn);
        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calculate BMI, check Status & Alert Dialog

            }
        });

        cancelBtn = findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BmiCalculatorActivity.this, BmiActivity.class);
                startActivity(intent);
            }
        });
    }
}