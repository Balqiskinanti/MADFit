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
    private static final String TABLE_MUSICPLAYER = "MusicPlayer";
    private static final String COLUMN_IDITEM = "itemId";
    private static final String COLUMN_IDCAT = "categoryId";
    private static final String COLUMN_PLAYERS = "musicPlayer";
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

        String CREATE_MUSICPLAYER_TABLE = "CREATE TABLE " + TABLE_MUSICPLAYER + "(" + COLUMN_IDCAT + " INTEGER," + COLUMN_IDITEM + " INTEGER," + COLUMN_PLAYERS + " TEXT" + ")";
        db.execSQL(CREATE_MUSICPLAYER_TABLE);

        insertMusicPlayer(db);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MUSICPLAYER);
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

    public void addMode(Mode mode) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_MODE, mode.getMode());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_SETTING, null, values);
        db.close();
    }

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

    private void executeInsertMusicPlayer(SQLiteDatabase db, String playerVal){
        String insertMusicPlayer = "INSERT INTO " + TABLE_MUSICPLAYER + playerVal;
        db.execSQL(insertMusicPlayer);
    }

    private void insertMusicPlayer(SQLiteDatabase db){
        // cat 1, item 1
        String musicPlayer11 = " VALUES( 1,1, '<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2FDJ_Mattrixx%2Fdrake-scorpion-album-mix%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2FDJNUT_N_NICE%2Ftravis-scott-x-don-toliver-astro-in-hell%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2FDjKxspa%2Fjustin-bieber-mixx%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fdjtigu%2Fbest-of-2020-hiphop-2nd-half%2F\" frameborder=\"0\" ></iframe>')";
        executeInsertMusicPlayer(db,musicPlayer11);

        //cat 1, item 2
        String musicPlayer12 = " VALUES( 1,2, '<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fakeymusicfactory%2Fall-sza-selection%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fcatalinbalan%2Ftropical-deep-house-2018-kygo-sia-zayn-deamn-ed-sheeran%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fdaikaa9%2Fmaroon-5s-songs%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fdj_dawn_mixes%2Fbazzimix%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fmuzicaurbana%2Fbillie-eilish-mix%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fdj_dawn_mixes%2F5sosmix%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fdj_general%2Fjuice-wrld-best-mix%2F\" frameborder=\"0\" ></iframe>')";
        executeInsertMusicPlayer(db,musicPlayer12);

        // cat 1, item 3
        String musicPlayer13 =" VALUES( 1,3, '<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Froxyboi%2Fblackpink-mix-2020%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fhatahatahata%2F2020btslast-summer-mix%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Ftsutomu-sugino%2Ftwice-mix-2017-mixed-by-dj-zhyme%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2FHard_Candy_Tokyo%2Fgirls-generation-continuous-mix%2F\" frameborder=\"0\" ></iframe>')";
        executeInsertMusicPlayer(db,musicPlayer13);

        // cat 2, item 1
        String musicPlayer21 = " VALUES( 2,1, '<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2FRich_Guy%2Flil-uzi-vert-mix%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fpatlam1%2Fdrake-partynextdoor-2020-ovo%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fpatlam1%2F2018-rb-hiphop-feat-travis-scott-juice-wrld-mac-miller-bhad-bhabie-tory-lanez-6lack%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2FDJKellz%2Fthe-hits-vol3-post-malone%2F\" frameborder=\"0\" ></iframe>')";
        executeInsertMusicPlayer(db,musicPlayer21);

        // cat 2, item 2
        String musicPlayer22 = " VALUES( 2,2, '<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fboxxkazukiz%2Fariana-grande-best-of-mix-mixed-by-kazukiz-boxx%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Froxyboi%2Fed-sheeran-mix%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2FPop_That%2Flove-lies-a-khalid-mix%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fraiden94%2Fone-direction-singles-mix%2F\" frameborder=\"0\" ></iframe>')";
        executeInsertMusicPlayer(db, musicPlayer22);

        // cat 2, item 3
        String musicPlayer23 = " VALUES( 2,3, '<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2F%EC%97%B0%EC%9D%982%2Fexo-mix-set%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fdjurb-swingup%2Fk-mix-2019-april-17-kpop-blackpink-redvelvet-twice-cl-mamamoo-bigbang-edm-rnb%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Farveeofficial%2Fmixmondays-justin-bieber-mix-2016-djarvee%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Ffrancisco-javier24%2Fdespacito-luis-fonsi-ft-justin-bieber%2F\" frameborder=\"0\" ></iframe>')";
        executeInsertMusicPlayer(db, musicPlayer23);

        // cat 3, item 1
        String musicPlayer31 = " VALUES( 3,1, '<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fdj_hacks%2Fdj-hacks-edm-mix-vol1-by-dj-shota%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fnutchil-naraka%2Fedm-dj-nutchil%2F\" frameborder=\"0\" ></iframe>')";
        executeInsertMusicPlayer(db, musicPlayer31);

        // cat 3, item 2
        String musicPlayer32 = " VALUES( 3,2, '<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2FBOONTHESHOP_CHEONGDAM%2Faddicted-to-background-noise-boontheshop-exclusive-january-2018%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2FOskarKoch%2Fjazzmix%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2FFurio_Frakes%2Fdinner-for-one-hundred-part-11-background-music-un-pique-nique-au-bord-de-la-seine%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2FFurio_Frakes%2Fdinner-for-one-hundred-part-5-background-music-acoustic-guitar%2F\" frameborder=\"0\" ></iframe>')";
        executeInsertMusicPlayer(db, musicPlayer32);

        // cat 3, item 3
        String musicPlayer33 = " VALUES( 3,3, '<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fdlesaner%2Fzenias-dance-jazzfusion%2F\" frameborder=\"0\" ></iframe>;" +
                "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2Fgedlocks%2F70s-80s-dance-jazz-funk-n-groove-special%2F\" frameborder=\"0\" ></iframe>')";
        executeInsertMusicPlayer(db, musicPlayer33);
    }

    public MusicPlayer getMusicPlayer(int catId , int itemId)
    {
        String query = "SELECT * FROM " + TABLE_MUSICPLAYER + " WHERE "
                + COLUMN_IDCAT + "= " + catId + " AND " + COLUMN_IDITEM + "= " + itemId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        MusicPlayer queryData = new MusicPlayer();
        ArrayList<String> playerList = new ArrayList<>();
        queryData.setCatId(catId);
        queryData.setItemId(itemId);
        if(cursor.moveToFirst())
        {
            String str= cursor.getString(2);
            String[] list = str.split(";");

            for (int i = 0; i < list.length; i++)
            {
                playerList.add(list[i]);
            }
            queryData.setMusicPlayerArrayList(playerList);
        }
        else
        {
            queryData = null;
        }
        cursor.close();
        db.close();
        return queryData;
    }
}
