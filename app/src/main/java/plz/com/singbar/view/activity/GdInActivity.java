package plz.com.singbar.view.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.operation.SongsOperation;
import plz.com.singbar.view.adapter.SouSuoAdapter;
import plz.com.singbar.view.info.SingInfo;
import plz.com.singbar.view.info.SingInfoo;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class GdInActivity extends Activity{
    private ListView lv;
    private List<String> imalist;
    private SouSuoAdapter adapter;
    private SongsOperation songsOperation;
    private SingInfo singInfo;
    private ImageView one;
    private TextView two;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gd_in);
        init();

    }


    public void buildAdapter() {
        if (adapter == null) {
            adapter = new SouSuoAdapter(GdInActivity.this, singInfo, imalist);
            lv.setAdapter(adapter);
        } else {
            adapter.datachange(singInfo, imalist);
        }
    }


    private void init() {
        one= (ImageView) findViewById(R.id.iv_loading_two);
        two= (TextView) findViewById(R.id.tv_loading_two);



        lv= (ListView) findViewById(R.id.lv_gd_in);

        String str=getIntent().getStringExtra("keyword");

        one.setVisibility(View.VISIBLE);
        two.setVisibility(View.VISIBLE);
        AnimationDrawable ad = (AnimationDrawable) one.getDrawable();
        ad.start();


        songsOperation = new SongsOperation(handler);
        songsOperation.getSongsBean(str);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    singInfo = (SingInfo) msg.obj;
                    List<SingInfoo> list = singInfo.getSingInfoo();

                    for (int i = 0; i < list.size(); i++) {
                        Log.i("result", list.get(i).getSingername() + "---" + i);
                        songsOperation.getSingerBean(list.get(i).getSingername(), list.size());

                    }

                    break;

                case 3:
                    List<String> imgs = (List<String>) msg.obj;
                    imalist = imgs;


                    one.setVisibility(View.GONE);
                    two.setVisibility(View.GONE);
                    AnimationDrawable ad = (AnimationDrawable) one.getDrawable();
                    ad.stop();

                    buildAdapter();
                    break;
            }

        }
    };
}
