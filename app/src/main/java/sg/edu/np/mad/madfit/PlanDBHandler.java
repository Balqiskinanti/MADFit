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
    private static final int DATABASE_VERSION = 1;

    public PlanDBHandler(Context context) {
        super(context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);
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

    public void deleteAllPlans(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLANS, null, null);
        db.close();
    }

    public ArrayList<Plan> getPlans()
    {
        ArrayList<Plan> list = new ArrayList<> ();
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