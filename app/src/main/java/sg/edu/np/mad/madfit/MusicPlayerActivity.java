package sg.edu.np.mad.madfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MusicPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        // Music dataUrl from https://www.mixcloud.com/
        WebView webView = findViewById(R.id.webView1);
        String dataUrl = "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?dark=1&feed=%2Fakeymusicfactory%2Fall-sza-selection%2F\" frameborder=\"0\" ></iframe>";
        loadMusicPlayer(webView, dataUrl);

        WebView webView2 = findViewById(R.id.webView2);
        String dataUrl2 = "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?feed=%2FDJ_Mattrixx%2Fdrake-scorpion-album-mix%2F\" frameborder=\"0\" ></iframe>";
        loadMusicPlayer(webView2, dataUrl2);

        WebView webView3 = findViewById(R.id.webView3);
        String dataUrl3 = "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?light=1&feed=%2FDJNUT_N_NICE%2Ftravis-scott-x-don-toliver-astro-in-hell%2F\" frameborder=\"0\" ></iframe>";
        loadMusicPlayer(webView3, dataUrl3);
    }

    /*
    Load music player to respective web view
    */
    private void loadMusicPlayer(WebView webView, String dataUrl){
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadData(dataUrl, "text/html", "utf-8");
    }
}