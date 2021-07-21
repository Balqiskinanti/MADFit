package sg.edu.np.mad.madfit.Model;

import java.util.ArrayList;

public class MusicPlayer {
    private int itemId;
    private int catId;
    private ArrayList<String> musicPlayerArrayList;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public ArrayList<String> getMusicPlayerArrayList() {
        return musicPlayerArrayList;
    }

    public void setMusicPlayerArrayList(ArrayList<String> musicPlayerArrayList) {
        this.musicPlayerArrayList = musicPlayerArrayList;
    }

    public MusicPlayer(){

    }

    public MusicPlayer(int itemId, int catId, ArrayList<String> musicPlayerArrayList) {
        this.itemId = itemId;
        this.catId = catId;
        this.musicPlayerArrayList = musicPlayerArrayList;
    }
}
