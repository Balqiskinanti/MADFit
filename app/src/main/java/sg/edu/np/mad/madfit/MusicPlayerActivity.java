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

        // load music from https://www.mixcloud.com/
        WebView webView = findViewById(R.id.webView);
        String dataUrl = "<iframe width=\"100%\" height=\"400\" src=\"https://www.mixcloud.com/widget/iframe/?dark=1&feed=%2Fakeymusicfactory%2Fall-sza-selection%2F\" frameborder=\"0\" ></iframe>";

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadData(dataUrl, "text/html", "utf-8");
    }
}