package sg.edu.np.mad.madfit.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.np.mad.madfit.Model.MusicCategory;
import sg.edu.np.mad.madfit.Model.MusicItem;
import sg.edu.np.mad.madfit.R;

public class ListMusicCategoryViewHolder extends RecyclerView.ViewHolder{
    TextView musicCatTxt;
    ArrayList<MusicItem> musicItemArrayList;
    RecyclerView catRV;
    ItemMusicAdapter itemMusicAdapter;
    public ListMusicCategoryViewHolder(View itemView){
        super(itemView);
        musicCatTxt = itemView.findViewById(R.id.musicCatTxt);
        catRV = itemView.findViewById(R.id.musicCatRV);

        musicItemArrayList = new ArrayList<>();

        //init RV
        itemMusicAdapter = new ItemMusicAdapter(musicItemArrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        catRV.setLayoutManager(layoutManager);
        catRV.setAdapter(itemMusicAdapter);
    }

    public void setItemList(ArrayList<MusicItem> musicItemArrayList) {
        this.musicItemArrayList.addAll(musicItemArrayList);
    }
}
