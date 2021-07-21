package sg.edu.np.mad.madfit.Model;

import java.util.ArrayList;

public class MusicCategory {
    private int id;
    private String name;
    private ArrayList<MusicItem> musicItemArrayList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MusicItem> getMusicItemArrayList() {
        return musicItemArrayList;
    }

    public void setMusicItemArrayList(ArrayList<MusicItem> musicItemArrayList) {
        this.musicItemArrayList = musicItemArrayList;
    }

    public MusicCategory(int id, String name, ArrayList<MusicItem> musicItemArrayList) {
        this.id = id;
        this.name = name;
        this.musicItemArrayList = musicItemArrayList;
    }
}
