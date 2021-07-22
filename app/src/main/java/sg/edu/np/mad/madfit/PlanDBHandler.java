package sg.edu.np.mad.madfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import sg.edu.np.mad.madfit.Model.Plan;

public class PlanDBHandler extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "workoutPlans.db";
    public static String TABLE_PLANS = "WorkoutPlans";
    public static String COLUMN_PLANID = "PlanId";
    public static String COLUMN_PLANTITLE = "PlanTitle";
    public static String COLUMN_PLANDESC = "PlanDesc";
    public static String COLUMN_PLANTYPE = "PlanType";
    public static String COLUMN_PLANDUR = "PlanDur";
    public static int DATABASE_VERSION = 1;

    public PlanDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PLAN_TABLE = "CREATE TABLE " + TABLE_PLANS +
                "(" + COLUMN_PLANID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PLANTITLE + " TEXT,"
                + COLUMN_PLANDESC + " TEXT,"
                + COLUMN_PLANTYPE + " TEXT,"
                + COLUMN_PLANDUR + " TEXT" + ")";
        db.execSQL(CREATE_PLAN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANS);
        onCreate(db);
    }

    public void addPlan(Plan plan) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLANTITLE, plan.getPlanTitle());
        values.put(COLUMN_PLANDESC, plan.getPlanDescription());
        values.put(COLUMN_PLANTYPE, plan.getPlanType());
        values.put(COLUMN_PLANDUR, plan.getPlanDuration());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PLANS, null, values);
        db.close();
    }

    /*
    public boolean deletePlan(int planId) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_PLANS + " WHERE "
                + COLUMN_PLANID + " = \""
                + planId + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Plan plan = new Plan();

        if (cursor.moveToFirst()) {
            plan.setPlanId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_PLANS, COLUMN_PLANID + " = ?",
                    new String[]{String.valueOf(plan.getPlanId())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    } */

    public ArrayList<Plan> getPlans()
    {
        ArrayList<Plan> list = new ArrayList<Plan> ();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM WorkoutPlans", null);

        while(cursor.moveToNext())
        {
            Plan plan = new Plan();
            plan.planTitle = cursor.getString(1);
            plan.planDescription = cursor.getString(2);
            plan.planType = cursor.getString(3);
            plan.planDuration = cursor.getString(4);
            list.add(plan);
        }
        cursor.close();
        db.close();
        return list;
    }
}