package sg.edu.np.mad.madfit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CalorieCounter extends AppCompatActivity {
    TextView dateToday, myBreakfast, myLunch, myDinner, myOthers, foodItem;
    Button doneBtn, btnAddBreakfast, btnAddLunch, btnAddDinner, btnAddOthers;
    EditText etFood, etCals;
    String mealType, myFoodInput, myCalsInput;
    boolean validInput;
    int totalBreakfastCals, totalLunchCals, totalDinnerCals, totalOthersCals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);

        /*
        Get today date from FoodActivity
         */
        Intent recDate = getIntent();
        String date = recDate.getStringExtra("Date");
        dateToday = findViewById(R.id.dateToday);
        dateToday.setText(date);

        /*
        Buttons to add new food item
         */
        btnAddBreakfast = findViewById(R.id.btnAddBreakfast);
        btnAddBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealType = "Breakfast";
                alertFoodItem(mealType);
            }
        });
        btnAddLunch = findViewById(R.id.btnAddLunch);
        btnAddLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealType = "Lunch";
                alertFoodItem(mealType);
            }
        });
        btnAddDinner = findViewById(R.id.btnAddDinner);
        btnAddDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealType = "Dinner";
                alertFoodItem(mealType);
            }
        });
        btnAddOthers = findViewById(R.id.btnAddOthers);
        btnAddOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealType = "Others";
                alertFoodItem(mealType);
            }
        });

        /*
        Navigate back to dashboard
         */
        doneBtn = findViewById(R.id.done_btn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save details to DB
                Intent intent = new Intent(CalorieCounter.this, FoodActivity.class);
                Toast.makeText(CalorieCounter.this, "Calorie details saved successfully!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    /*
    Dialog for user input on food item and calories
     */
    private void alertFoodItem(String mealType){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View fooditemLayout = this.getLayoutInflater().inflate(R.layout.fooditem_input, null);
        builder.setView(fooditemLayout);
        builder.setTitle("Calories for " + mealType);
        builder.setCancelable(false);
        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                etFood = (EditText) fooditemLayout.findViewById(R.id.myFoodInput);
                myFoodInput = etFood.getText().toString();
                etCals = (EditText) fooditemLayout.findViewById(R.id.myCalsInput);
                myCalsInput = etCals.getText().toString();

                /*
                Check for null values
                 */
                if (myFoodInput.isEmpty()){
                    Toast.makeText(CalorieCounter.this,"Please enter a food item",Toast.LENGTH_SHORT).show();
                    validInput = false;
                } else
                    validInput = true;
                if (myCalsInput.isEmpty()){
                    Toast.makeText(CalorieCounter.this,"Please enter the calorie value",Toast.LENGTH_SHORT).show();
                    validInput = false;
                } else
                    validInput = true;

                if (validInput == true)
                {
                    /*
                    Check meal types
                     */
                    if (mealType == "Breakfast"){
                        totalBreakfastCals += Integer.parseInt(myCalsInput);
                        myBreakfast = findViewById(R.id.myBreakfast);
                        myBreakfast.setText(String.valueOf(totalBreakfastCals) + " cals");
                    } else if (mealType == "Lunch"){
                        totalLunchCals += Integer.parseInt(myCalsInput);
                        myLunch = findViewById(R.id.myLunch);
                        myLunch.setText(String.valueOf(totalLunchCals) + " cals");
                    } else if (mealType == "Dinner"){
                        totalDinnerCals += Integer.parseInt(myCalsInput);
                        myDinner = findViewById(R.id.myDinner);
                        myDinner.setText(String.valueOf(totalDinnerCals) + " cals");
                    } else if (mealType == "Others"){
                        totalOthersCals += Integer.parseInt(myCalsInput);
                        myOthers = findViewById(R.id.myOthers);
                        myOthers.setText(String.valueOf(totalOthersCals) + " cals");
                    }
                    createFoodText(mealType, myFoodInput, myCalsInput);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /*
    Create TextView in meal section to display user inputs
     */
    private void createFoodText(String mealType, String myFoodInput, String myCalsInput){
        foodItem = new TextView(getApplicationContext());

        LinearLayout.LayoutParams txtparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // Width Of The TextView
                LinearLayout.LayoutParams.WRAP_CONTENT); // Height Of The TextView

        foodItem.setLayoutParams(txtparams);
        foodItem.setText(myCalsInput + " cals : " + myFoodInput);
        foodItem.setTextSize(16);

        if (mealType == "Breakfast") {
            LinearLayout breakfastLayout = (LinearLayout) findViewById(R.id.breakfastLayout);
            breakfastLayout.addView(foodItem);
        } else if (mealType == "Lunch"){
            LinearLayout lunchLayout = (LinearLayout) findViewById(R.id.lunchLayout);
            lunchLayout.addView(foodItem);
        } else if (mealType == "Dinner"){
            LinearLayout dinnerLayout = (LinearLayout) findViewById(R.id.dinnerLayout);
            dinnerLayout.addView(foodItem);
        } else if (mealType == "Others"){
            LinearLayout othersLayout = (LinearLayout) findViewById(R.id.othersLayout);
            othersLayout.addView(foodItem);
        }
    }
}