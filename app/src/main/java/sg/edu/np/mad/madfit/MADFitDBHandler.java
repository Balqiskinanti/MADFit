package sg.edu.np.mad.madfit;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import sg.edu.np.mad.madfit.Model.Mode;

public class MADFitDBHandler extends SQLiteOpenHelper{

    private static final String DB_NAME = "madWorkout.db"; //filename
    private static final int DB_VER = 1;
    private static final String TABLE_SETTING = "Setting"; //table
    private static final String COLUMN_MODE = "mode";
    private static final String TAG = "DBHandler";
    public static String COLUMN_ID = "id";

    public MADFitDBHandler(Context context){
                super(context, DB_NAME, null, DB_VER);
            }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        Log.v(TAG,"Table created");
        String CREATE_SETTING_TABLE = "CREATE TABLE " + TABLE_SETTING + "(" + COLUMN_MODE + " TEXT" + ")";

        String create = "CREATE TABLE setting (mode TEXT)";
        db.execSQL(CREATE_SETTING_TABLE);

         */
        //addMode(1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTING);
        onCreate(db);

         */
    }

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

    public void saveSettingMode (int value){
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE Setting SET mode = " + value;
        db.execSQL(query);
        db.close();
    }


    public void addMode(Mode mode) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_MODE, mode.getMode());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_SETTING, null, values);
        db.close();
    }




}