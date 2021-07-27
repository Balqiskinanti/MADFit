package sg.edu.np.mad.madfit.Model;

public class Mode {
    //exercise mode (easy(0), medium(1), hard(2))
    private int Mode;

    public Mode(){

    }

    public Mode(int mode){
        this.Mode = mode;
    }

    public int getMode() {
        return Mode;
    }

    public void setMode(int mode) {
        Mode = mode;
    }
}
