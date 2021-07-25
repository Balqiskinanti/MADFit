package sg.edu.np.mad.madfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import sg.edu.np.mad.madfit.Model.Calories;

public class CalorieDBHandler extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "calories.db";
    public static  String TABLE_CALORIES = "Calories";
    public static  String COLUMN_MEALTYPE = "mealType";
    public static  String COLUMN_FOODNAME = "foodName";
    public static  String COLUMN_FOODCALS = "foodCals";
    private static final int DATABASE_VERSION = 1;

    public CalorieDBHandler(Context context) {
        super(context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CALORIES = "CREATE TABLE " + TABLE_CALORIES + "(" +
                COLUMN_MEALTYPE + " TEXT," +
                COLUMN_FOODNAME + " TEXT, " +
                COLUMN_FOODCALS + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_CALORIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALORIES);
        onCreate(db);
    }

    public void addCalories(Calories calories){
        ContentValues values = new ContentValues();
        values.put(COLUMN_MEALTYPE, calories.getMealType());
        values.put(COLUMN_FOODNAME, calories.getFoodName());
        values.put(COLUMN_FOODCALS, calories.getFoodCals());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_CALORIES, null, values);
        db.close();
    }

    public void deleteAllFoodItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CALORIES, null, null);
        db.close();
    }

    public ArrayList<Calories> getFoodItems(){
        ArrayList<Calories> list = new ArrayList<> ();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Calories", null);
        while(cursor.moveToNext())
        {
            Calories calories = new Calories(
                    cursor.getString(0),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2))
            );
            list.add(calories);
        }
        cursor.close();
        db.close();
        return list;
    }
}
