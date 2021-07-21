package sg.edu.np.mad.madfit.Adapter;

import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import sg.edu.np.mad.madfit.R;

public class ItemMusicPlayerViewHolder extends RecyclerView.ViewHolder{
    WebView webView;
    public ItemMusicPlayerViewHolder(View itemView){
        super(itemView);
        webView = itemView.findViewById(R.id.webView);
    }
}
