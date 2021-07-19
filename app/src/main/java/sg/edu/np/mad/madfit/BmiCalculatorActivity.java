package sg.edu.np.mad.madfit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class BmiCalculatorActivity extends AppCompatActivity {
    EditText myHeightInput, myWeightInput;
    Button cancelBtn, calBtn;
    String myH, myW;
    double myHeight, myWeight;
    boolean validInput;
    SharedPreferences sharedPreferences;
    public String GLOBAL_PREFS = "MyPrefs";
    public String MY_HEIGHT = "MyHeight";
    public String MY_WEIGHT = "MyWeight";
    public String MY_BMI = "MyBMI";
    public String MY_STATUS = "MyStatus";

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
                    Toast.makeText(BmiCalculatorActivity.this,"Please enter a height",Toast.LENGTH_SHORT).show();
                    validInput = false;
                } else {
                    myHeight = Double.parseDouble(myH);
                    validInput = true;
                }
                if(myW.isEmpty()){
                    Toast.makeText(BmiCalculatorActivity.this,"Please enter a weight",Toast.LENGTH_SHORT).show();
                    validInput = false;
                } else {
                    myWeight = Double.parseDouble(myW);
                    validInput = true;
                }

                if (validInput == true)
                {
                    double myBMI = calBMI(myHeight, myWeight);
                    String myStatus = bmiStatus(myBMI);
                    bmiAlert(myBMI, myStatus, myHeight, myWeight);
                }
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
            status = "No Records";
        }
        return status;
    }

    /*
    Alert dialog to show BMI & Status, and ask whether to store results
     */
    private void bmiAlert(double myBMI, String myStatus, double myHeight, double myWeight){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your BMI status is " + myStatus + ". Do you wish to save this record?");
        builder.setTitle("Your BMI is: " + String.format("%.1f", myBMI));
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*Intent displayRecord = new Intent(MainActivity2.this, MainActivity.class);
                displayRecord.putExtra("Height", myHeight);
                displayRecord.putExtra("Weight", myWeight);
                displayRecord.putExtra("BMI", myBMI);
                displayRecord.putExtra("Status", myStatus);
                startActivity(displayRecord);*/

                sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(MY_HEIGHT, String.valueOf(myHeight));
                editor.putString(MY_WEIGHT, String.valueOf(myWeight));
                editor.putString(MY_BMI, String.valueOf(myBMI));
                editor.putString(MY_STATUS, myStatus);
                editor.apply();

                Intent intent = new Intent(BmiCalculatorActivity.this, BmiActivity.class);
                Toast.makeText(BmiCalculatorActivity.this,"BMI recorded successfully!",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(BmiCalculatorActivity.this, BmiActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}