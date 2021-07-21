package sg.edu.np.mad.madfit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.np.mad.madfit.BmiCalculatorActivity;
import sg.edu.np.mad.madfit.Model.MusicCategory;
import sg.edu.np.mad.madfit.Model.MusicItem;
import sg.edu.np.mad.madfit.MusicPlayerActivity;
import sg.edu.np.mad.madfit.R;

public class ItemMusicAdapter extends RecyclerView.Adapter<ItemMusicViewHolder>{
    ArrayList<MusicItem> musicItemArrayList;
    public ItemMusicAdapter(ArrayList<MusicItem> input){
        musicItemArrayList = input;
    }

    public ItemMusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item;
        item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music,parent,false);
        return new ItemMusicViewHolder(item);
    }

    public void onBindViewHolder(ItemMusicViewHolder holder, int position) {
        MusicItem musicItem = musicItemArrayList.get(position);
        holder.itemTxt.setText(musicItem.getName());
        holder.itemImg.setImageResource(musicItem.getImage());
        holder.itemCard.setCardBackgroundColor(Color.parseColor(musicItem.getBgColour()));

        // click event, pass Ids
        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context mContext = holder.itemCard.getContext();
                Toast.makeText(mContext,"Going to your music playlist..",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, MusicPlayerActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("itemId",musicItem.getId());
                extras.putInt("catId",musicItem.getCatId());
                intent.putExtras(extras);
                mContext.startActivity(intent);
            }
        });
    }

    public int getItemCount(){
        return musicItemArrayList.size();
    }

}
