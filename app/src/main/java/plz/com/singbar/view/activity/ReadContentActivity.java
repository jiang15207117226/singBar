package plz.com.singbar.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import plz.com.singbar.R;

/**
 * Created by Administrator on 2016/9/2.
 */
public class ReadContentActivity extends Activity implements View.OnClickListener {
    private ImageView back;
    private WebView web;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        init();
    }

    private void init() {
        back = (ImageView) findViewById(R.id.iv_web_back);
        progress = (ProgressBar) findViewById(R.id.pb);
        web = (WebView) findViewById(R.id.web);
        String url = getIntent().getStringExtra("url");
        web.loadUrl(url);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDefaultTextEncodingName("utf-8");
        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progress.setProgress(newProgress);
                if (progress.getProgress() == 100) {
                    progress.setVisibility(View.GONE);
                }
            }
        });
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
