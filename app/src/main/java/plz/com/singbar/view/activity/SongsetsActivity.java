package plz.com.singbar.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.view.adapter.SingerGvAdapter;
import plz.com.singbar.view.info.SingerHNInfo;

/**
 * Created by Administrator on 2016/8/31.
 */
public class SongsetsActivity extends Activity {
    private ListView lv;
    private ImageView iv;
    private GridView gv;
    private List<SingerHNInfo> gvList;
    private SingerHNInfo singerinfo;
    private SingerGvAdapter singeradapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songsets);

        init();
        build();
    }



    public void init() {
        lv = (ListView) findViewById(R.id.songsets_lv);
        iv = (ImageView) findViewById(R.id.songsets_iv);
        gv= (GridView) findViewById(R.id.gv_gv_one);
        gvList=new ArrayList<>();

    }
    private void build() {

        for(int i=0;i<10;i++){
            singerinfo=new SingerHNInfo();
            singerinfo.setIma("http://i-3.497.com/2016/8/11/KDYwMHgpW3dtOjEucG5nLHI6MTMsYjoxM10=/d53c039a-714d-42be-9549-325759e46302.png");
            singerinfo.setName("潘璐砖");
            gvList.add(singerinfo);
        }
        singeradapter=new SingerGvAdapter(gvList,SongsetsActivity.this);
        gv.setAdapter(singeradapter);
    }
}
