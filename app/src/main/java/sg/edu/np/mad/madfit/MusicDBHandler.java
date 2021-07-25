package sg.edu.np.mad.madfit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import sg.edu.np.mad.madfit.Model.MusicPlayer;

public class MusicDBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "workoutMusic.db";
    private static final int DB_VER = 1;
    private static final String TABLE_MUSICPLAYER = "MusicPlayer";
    private static final String COLUMN_IDITEM = "itemId";
    private static final String COLUMN_IDCAT = "categoryId";
    private static final String COLUMN_PLAYERS = "musicPlayer";

    public MusicDBHandler(Context context){
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MUSICPLAYER_TABLE = "CREATE TABLE " + TABLE_MUSICPLAYER + "(" + COLUMN_IDCAT + " INTEGER," + COLUMN_IDITEM + " INTEGER," + COLUMN_PLAYERS + " TEXT" + ")";
        db.execSQL(CREATE_MUSICPLAYER_TABLE);

        insertMusicPlayer(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MUSICPLAYER);
        onCreate(db);
    }

    private void executeInsertMusicPlayer(SQLiteDatabase db, String playerVal){
        String insertMusicPlayer = "INSERT INTO " + TABLE_MUSICPLAYER + playerVal;
        db.execSQL(insertMusicPlayer);
    }

    private void insertMusicPlayer(SQLiteDatabase db){
        // cat 1, item 1
        String musicPlayer11 = " VALUES( 1,1, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/playlists/725079831&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/playlists/1010199886&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/523062207&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true')";
        executeInsertMusicPlayer(db,musicPlayer11);

        //cat 1, item 2
        String musicPlayer12 = " VALUES( 1,2, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/873378757&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/639968292&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/playlists/1290759283&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true')";
        executeInsertMusicPlayer(db,musicPlayer12);

        // cat 1, item 3
        String musicPlayer13 =" VALUES( 1,3, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/playlists/1061668060&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/302998632&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/445753776&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true')";
        executeInsertMusicPlayer(db,musicPlayer13);

        // cat 2, item 1
        String musicPlayer21 = " VALUES( 2,1, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/527008875&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/583436769&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1063214623&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true')";
        executeInsertMusicPlayer(db,musicPlayer21);

        // cat 2, item 2
        String musicPlayer22 = " VALUES( 2,2, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/814678039&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/277062362&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/346585632&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/233719633&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/playlists/643050039&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/325513140&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/689498713&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/662005157&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true')";
        executeInsertMusicPlayer(db, musicPlayer22);

        // cat 2, item 3
        String musicPlayer23 = " VALUES( 2,3, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/737726419&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/425635065&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/167437563&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/309358556&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true')";
        executeInsertMusicPlayer(db, musicPlayer23);

        // cat 3, item 1
        String musicPlayer31 = " VALUES( 3,1, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/308277135&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/293835520&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true')";
        executeInsertMusicPlayer(db, musicPlayer31);

        // cat 3, item 2
        String musicPlayer32 = " VALUES( 3,2, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1089771466&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1088289991&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1039928815&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;')";
        executeInsertMusicPlayer(db, musicPlayer32);

        // cat 3, item 3
        String musicPlayer33 = " VALUES( 3,3, 'https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/106001154&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1016228809&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true;" +
                "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/912894796&color=%230e0e0e&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true')";
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
