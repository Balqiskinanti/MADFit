package sg.edu.np.mad.madfit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import sg.edu.np.mad.madfit.Model.Calories;
import sg.edu.np.mad.madfit.Model.Plan;

public class CalorieCounter extends AppCompatActivity {
    TextView dateToday, myBreakfast, myLunch, myDinner, myOthers, foodItem;
    Button doneBtn, btnAddBreakfast, btnAddLunch, btnAddDinner, btnAddOthers;
    EditText etFood, etCals;
    String mealType, myFoodInput, myCalsInput, foodName, foodCals;
    boolean validInput;
    int totalBreakfastCals, totalLunchCals, totalDinnerCals, totalOthersCals;
    static ArrayList<Calories> calsList;
    CalorieDBHandler calorieDBHandler;
    SharedPreferences sharedPreferences;
    public String GLOBAL_PREFS = "MyPrefs";
    public String TOTALBREAKFAST = "TotalBreakfast";
    public String TOTALLUNCH = "TotalLunch";
    public String TOTALDINNER = "TotalDinner";
    public String TOTALOTHERS = "TotalOthers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);

        calorieDBHandler = new CalorieDBHandler(this);
        displayCals();
        displayFoodItems();

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
                Toast.makeText(CalorieCounter.this, "Calorie details saved!", Toast.LENGTH_SHORT).show();
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
                etFood = fooditemLayout.findViewById(R.id.myFoodInput);
                myFoodInput = etFood.getText().toString();
                etCals = fooditemLayout.findViewById(R.id.myCalsInput);
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
                } else {
                    try {
                        // check if input is numerical
                        Integer.parseInt(myCalsInput);
                        validInput = true;
                    } catch (NumberFormatException e) {
                        Toast.makeText(CalorieCounter.this,"Please enter a numerical value for calorie",Toast.LENGTH_SHORT).show();
                        validInput = false;
                    }
                }

                if (validInput == true)
                {
                    /*
                    Check meal types
                     */
                    if (mealType == "Breakfast"){
                        totalBreakfastCals += Integer.parseInt(myCalsInput);
                        myBreakfast = findViewById(R.id.myBreakfast);
                        myBreakfast.setText(String.valueOf(totalBreakfastCals) + " cals");

                        sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(TOTALBREAKFAST, String.valueOf(totalBreakfastCals));
                        editor.apply();
                    } else if (mealType == "Lunch"){
                        totalLunchCals += Integer.parseInt(myCalsInput);
                        myLunch = findViewById(R.id.myLunch);
                        myLunch.setText(String.valueOf(totalLunchCals) + " cals");

                        sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(TOTALLUNCH, String.valueOf(totalLunchCals));
                        editor.apply();
                    } else if (mealType == "Dinner"){
                        totalDinnerCals += Integer.parseInt(myCalsInput);
                        myDinner = findViewById(R.id.myDinner);
                        myDinner.setText(String.valueOf(totalDinnerCals) + " cals");

                        sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(TOTALDINNER, String.valueOf(totalDinnerCals));
                        editor.apply();
                    } else if (mealType == "Others"){
                        totalOthersCals += Integer.parseInt(myCalsInput);
                        myOthers = findViewById(R.id.myOthers);
                        myOthers.setText(String.valueOf(totalOthersCals) + " cals");

                        sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(TOTALOTHERS, String.valueOf(totalOthersCals));
                        editor.apply();
                    }
                    createFoodText(mealType, myFoodInput, myCalsInput);

                    /*
                    Add input to DB
                     */
                    Calories calories = new Calories(mealType, myFoodInput, Integer.parseInt(myCalsInput));
                    calorieDBHandler.addCalories(calories);

                    Toast.makeText(CalorieCounter.this, "Item added successfully!", Toast.LENGTH_SHORT).show();
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
            LinearLayout breakfastLayout = findViewById(R.id.breakfastLayout);
            breakfastLayout.addView(foodItem);
        } else if (mealType == "Lunch"){
            LinearLayout lunchLayout = findViewById(R.id.lunchLayout);
            lunchLayout.addView(foodItem);
        } else if (mealType == "Dinner"){
            LinearLayout dinnerLayout = findViewById(R.id.dinnerLayout);
            dinnerLayout.addView(foodItem);
        } else if (mealType == "Others"){
            LinearLayout othersLayout = findViewById(R.id.othersLayout);
            othersLayout.addView(foodItem);
        }
    }

    /*
    Display calorie values from SharedPreferences
     */
    private void displayCals(){
        SharedPreferences prefs = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        String valueBreakfast = prefs.getString(TOTALBREAKFAST, "0");
        myBreakfast = findViewById(R.id.myBreakfast);
        myBreakfast.setText(valueBreakfast + " cals");
        totalBreakfastCals += Integer.parseInt(valueBreakfast);

        String valueLunch = prefs.getString(TOTALLUNCH, "0");
        myLunch = findViewById(R.id.myLunch);
        myLunch.setText(valueLunch + " cals");
        totalLunchCals += Integer.parseInt(valueLunch);

        String valueDinner = prefs.getString(TOTALDINNER, "0");
        myDinner = findViewById(R.id.myDinner);
        myDinner.setText(valueDinner + " cals");
        totalDinnerCals += Integer.parseInt(valueDinner);

        String valueOthers = prefs.getString(TOTALOTHERS, "0");
        myOthers = findViewById(R.id.myOthers);
        myOthers.setText(valueOthers + " cals");
        totalOthersCals += Integer.parseInt(valueOthers);
    }

    /*
    Display food items from DB
     */
    private void displayFoodItems(){
        calorieDBHandler = new CalorieDBHandler(this);
        calsList = calorieDBHandler.getFoodItems();
        Log.v("main", "display");
        if (calsList.isEmpty())
        {
            Log.v("main", "no create");
        }
        else
        {
            for (int i = 0; i < calsList.size(); ++i)
            {
                Calories calories = calsList.get(i);
                mealType = calories.getMealType();
                foodName = calories.getFoodName();
                foodCals = String.valueOf(calories.getFoodCals());

                /*
                Check meal types before adding TextViews
                 */
                if (String.valueOf(mealType).equals("Breakfast"))
                    createFoodText(
                            "Breakfast",
                            foodName,
                            foodCals
                    );
                if (String.valueOf(mealType).equals("Lunch"))
                    createFoodText(
                            "Lunch",
                            foodName,
                            foodCals
                    );
                if (String.valueOf(mealType).equals("Dinner"))
                    createFoodText(
                            "Dinner",
                            foodName,
                            foodCals
                    );
                if (String.valueOf(mealType).equals("Others"))
                    createFoodText(
                            "Others",
                            foodName,
                            foodCals
                    );
            }
        }
    }
}