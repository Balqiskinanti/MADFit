package sg.edu.np.mad.madfit;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sg.edu.np.mad.madfit.Model.Mode;
import sg.edu.np.mad.madfit.Model.MusicPlayer;

public class MADFitDBHandler extends SQLiteOpenHelper{

    private static final String DB_NAME = "madWorkout.db"; //filename
    private static final int DB_VER = 1;
    private static final String TABLE_SETTING = "Setting"; //table
    private static final String COLUMN_MODE = "mode";   //column
    private static final String TABLE_DAYS = "WorkoutDays"; //table
    private static final String COLUMN_ID = "ID";   //column
    private static final String COLUMN_DAY = "Day";   //column
    private static final String TABLE_TUTORIAL = "Tutorial"; //table
    private static final String COLUMN_SKIP = "skip";   //column
    private static final String TAG = "DBHandler";

    public MADFitDBHandler(Context context){
                super(context, DB_NAME, null, DB_VER);
            }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // create table
        Log.v(TAG,"Table created");
        String CREATE_SETTING_TABLE = "CREATE TABLE " + TABLE_SETTING + "(" + COLUMN_MODE + " TEXT" + ")";
        db.execSQL(CREATE_SETTING_TABLE);

        // set default mode
        String insertDefaultMode = "INSERT INTO "+ TABLE_SETTING + " VALUES (0)";
        db.execSQL(insertDefaultMode);

        String CREATE_DAY_TABLE = "CREATE TABLE " + TABLE_DAYS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DAY + " TEXT" + ")";
        db.execSQL(CREATE_DAY_TABLE);

        String CREATE_TUTORIAL_TABLE = "CREATE TABLE " + TABLE_TUTORIAL + "(" + COLUMN_SKIP + " TEXT" + ")";
        db.execSQL(CREATE_TUTORIAL_TABLE);

        // set default skip
        String insertDefaultSkip = "INSERT INTO "+ TABLE_TUTORIAL + " VALUES (0)";
        db.execSQL(insertDefaultSkip);

      /*
        for (int i = 0; i < 1; ++i) {
            Log.v("Create Table", "");
            ContentValues values = new ContentValues();
            values.put(COLUMN_MODE, "1");
            db.insert(TABLE_SETTING, null, values);
        }
        */

        //addMode(1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAYS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TUTORIAL);
        onCreate(db);
    }

    //get setting mode in database
    public int getSettingMode(){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"mode"};
        String sqlTable = "Setting";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect,null,null,null,null,null);
        c.moveToFirst();
        db.close();
        return c.getInt(c.getColumnIndex("mode"));
    }

    //save setting mode to database
    public void saveSettingMode (int value){
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE Setting SET mode = " + value;
        db.execSQL(query);
        db.close();
    }

    /*
    public void addMode(Mode mode) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_MODE, mode.getMode());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_SETTING, null, values);
        db.close();
    }

     */

    public List<String> getWorkoutDays(){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Day"};
        String sqlTable = "WorkoutDays";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect,null,null,null,null,null);

        List<String> result = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                result.add(c.getString(c.getColumnIndex("Day")));
            }while (c.moveToNext());

        }
        return result;
    }

    public void saveDay (String value){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO WorkoutDays(Day) VALUES('%s');",value);
        db.execSQL(query);
        db.close();
    }

    //get tutorial skip in database
    public int getTutorialSkip(){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"skip"};
        String sqlTable = "Tutorial";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect,null,null,null,null,null);
        c.moveToFirst();
        db.close();
        return c.getInt(c.getColumnIndex("skip"));
    }

    //save tutorial skip to database
    public void saveTutorialSkip (int value){
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE Tutorial SET skip = " + value;
        db.execSQL(query);
        db.close();
    }
}
