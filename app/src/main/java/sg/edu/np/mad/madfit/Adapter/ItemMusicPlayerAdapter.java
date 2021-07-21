package sg.edu.np.mad.madfit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.np.mad.madfit.Model.MusicItem;
import sg.edu.np.mad.madfit.MusicPlayerActivity;
import sg.edu.np.mad.madfit.R;

public class ItemMusicPlayerAdapter extends RecyclerView.Adapter<ItemMusicPlayerViewHolder>{
    ArrayList<String> musicPlayerArrayList;
    public ItemMusicPlayerAdapter(ArrayList<String> input){
        musicPlayerArrayList = input;
    }

    public ItemMusicPlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item;
        item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music_player,parent,false);
        return new ItemMusicPlayerViewHolder(item);
    }

    public void onBindViewHolder(ItemMusicPlayerViewHolder holder, int position) {
        String musicPlayer = musicPlayerArrayList.get(position);
        loadMusicPlayer(holder.webView,musicPlayer, holder.webView.getContext());
    }

    /*Load music player to web view*/
    private void loadMusicPlayer(WebView webView, String dataUrl, Context context){
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadData(dataUrl, "text/html", "utf-8");
    }

    public int getItemCount(){
        return musicPlayerArrayList.size();
    }
}
