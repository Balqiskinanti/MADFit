package sg.edu.np.mad.madfit.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

public class MADFitDBHandler extends SQLiteOpenHelper{

    private static final String DB_NAME = "madFitWorkout.db";
    private static final int DB_VER = 1;
    private static final String TABLE_SETTING = "setting";
    private static final String COLUMN_MODE = "mode";

            public MADFitDBHandler(Context context){
                super(context, DB_NAME, null, DB_VER);
            }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SETTING_TABLE = "CREATE TABLE " + TABLE_SETTING +
                "(" + COLUMN_MODE + " INTEGER" + ")";
        db.execSQL(CREATE_SETTING_TABLE);
        addMode(1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTING);
        onCreate(db);
    }

    public int getSettingMode(int mode){

        String query = "SELECT * FROM " + TABLE_SETTING + " WHERE "
                + COLUMN_MODE + " = \"" + mode + "\"";

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        db.close();
        return cursor.getInt(cursor.getColumnIndex("Mode"));

        /*
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = ("Mode");
        String sqlTable = "Setting";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, new sqlSelect,null,null,null,null,null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("Mode"));

         */
    }

    public void saveSettingMode (int value){
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE Setting SET Mode = " + value;
        db.execSQL(query);
    }

    public void addMode(int mode) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_MODE, mode);
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_SETTING, null, values);
        db.close();
    }

}
