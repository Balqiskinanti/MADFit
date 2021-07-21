package sg.edu.np.mad.madfit.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.np.mad.madfit.Model.MusicCategory;
import sg.edu.np.mad.madfit.R;

public class ListMusicCategoryAdapter extends RecyclerView.Adapter<ListMusicCategoryViewHolder>{
    ArrayList<MusicCategory> musicCategoryArrayList;
    public ListMusicCategoryAdapter(ArrayList<MusicCategory> input){
        musicCategoryArrayList = input;
    }

    public ListMusicCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item;
        item = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_music_category,parent,false);
        return new ListMusicCategoryViewHolder(item);
    }

    public void onBindViewHolder(ListMusicCategoryViewHolder holder, int position) {
        MusicCategory category = musicCategoryArrayList.get(position);
        holder.musicCatTxt.setText(category.getName());
        holder.setItemList(category.getMusicItemArrayList()); // set list of music item for the category
    }

    public int getItemCount(){
        return musicCategoryArrayList.size();
    }
}
