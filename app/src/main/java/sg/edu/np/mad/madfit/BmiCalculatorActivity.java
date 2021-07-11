package sg.edu.np.mad.madfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class BmiCalculatorActivity extends AppCompatActivity {
    EditText myHeightInput, myWeightInput;
    Button cancelBtn, calBtn;
    String myH, myW;
    double myHeight, myWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        myHeightInput = findViewById(R.id.height_input);
        myWeightInput = findViewById(R.id.weight_input);

        //calculate BMI, check Status & Alert Dialog
        calBtn = findViewById(R.id.cal_btn);
        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myH = myHeightInput.getText().toString();
                myW = myWeightInput.getText().toString();

                if(myH.isEmpty()){
                    myHeight = 0.0;
                } else {
                    myHeight = Double.parseDouble(myH);
                }
                if(myW.isEmpty()){
                    myWeight = 0.0;
                } else {
                    myWeight = Double.parseDouble(myW);
                }

                double myBMI = calBMI(myHeight, myWeight);
                String myStatus = bmiStatus(myBMI);
            }
        });

        // Go to bmi activity
        cancelBtn = findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BmiCalculatorActivity.this, BmiActivity.class);
                startActivity(intent);
            }
        });
    }

    /*
    Calculate BMI using Height & Weight from User and return BMI
    */
    private double calBMI(double myHeight, double myWeight){
        double bmi = myWeight / (myHeight * myHeight);
        // to return BMI in one decimal format
        DecimalFormat oneDForm = new DecimalFormat("#.#");
        return Double.valueOf(oneDForm.format(bmi));
    }

    /*
    Check User's BMI Status based on BMI calculated and return status
    */
    private String bmiStatus(double bmi){
        String status;
        if (bmi < 18.5){
            status = "Underweight";
        } else if (bmi <= 24.9){
            status = "Normal";
        } else if (bmi <= 29.9){
            status = "Overweight";
        } else if (bmi > 30.0){
            status = "Obese";
        } else {
            status = "None";
        }
        return status;
    }
}