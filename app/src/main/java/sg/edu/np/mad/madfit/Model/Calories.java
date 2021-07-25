package sg.edu.np.mad.madfit.Model;

public class Calories {

    private String mealType;
    private String foodName;
    private int foodCals;

    public Calories(String mealType, String foodName, int foodCals) {
        this.mealType = mealType;
        this.foodName = foodName;
        this.foodCals = foodCals;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodCals() {
        return foodCals;
    }

    public void setFoodCals(int foodCals) {
        this.foodCals = foodCals;
    }
}
