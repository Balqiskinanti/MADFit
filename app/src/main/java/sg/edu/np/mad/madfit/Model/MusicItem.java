package sg.edu.np.mad.madfit.Model;

public class MusicItem {
    private int id;
    private int catId;
    private String name;
    private String bgColour;
    private int image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBgColour() {
        return bgColour;
    }

    public void setBgColour(String bgColour) {
        this.bgColour = bgColour;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public MusicItem(int id, int catId, String name, String bgColour, int image) {
        this.id = id;
        this.catId = catId;
        this.name = name;
        this.bgColour = bgColour;
        this.image = image;
    }
}
