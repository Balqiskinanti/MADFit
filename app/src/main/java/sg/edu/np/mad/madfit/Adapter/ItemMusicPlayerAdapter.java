package sg.edu.np.mad.madfit.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.np.mad.madfit.BmiActivity;
import sg.edu.np.mad.madfit.BmiCalculatorActivity;
import sg.edu.np.mad.madfit.Model.MusicItem;
import sg.edu.np.mad.madfit.Model.MusicPlayer;
import sg.edu.np.mad.madfit.MusicActivity;
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
    private void loadMusicPlayer(WebView webView, String dataUrl,  Context context){
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null || url.startsWith("http://") || url.startsWith("https://")) {
                    return false;
                }

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    view.getContext().startActivity(intent);
                    return true;
                } catch (Exception e) {
                    return true;
                }
            }
        });

        if(isNetworkAvailable(context)){
            webView.loadUrl(dataUrl);
        }
        else{
            noInternetAlert(context);
        }
    }

    public int getItemCount(){
        return musicPlayerArrayList.size();
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void noInternetAlert(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("You are offline. Please check your internet connection to use this feature.");
        builder.setTitle("No Internet Connection");
        builder.setCancelable(false);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Intent intent = new Intent(context, MusicActivity.class);
                context.startActivity(intent);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
