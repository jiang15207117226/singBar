package plz.com.singbar.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import plz.com.singbar.R;

/**
 * Created by Administrator on 2016/8/31.
 */
public class SongsetsActivity extends Activity {
    private ListView lv;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songsets);

        init();
    }

    public void init() {
        lv = (ListView) findViewById(R.id.songsets_lv);
        iv = (ImageView) findViewById(R.id.songsets_iv);

    }
}
