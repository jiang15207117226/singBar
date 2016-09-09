package plz.com.singbar.view.activity;

import android.app.Activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
 * Created by Administrator on 2016/9/5 0005.
 */
public class DgSouSuoActivity extends Activity {
    private EditText sousuo;
    private TextView tv;
    private SingInfo singInfo;
    private ListView lv;
    private List<String> imalist;
    private SouSuoAdapter adapter;
    private SongsOperation songsOperation;
    private ImageView ivloading;
    private TextView tvloading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diange_sousuo);
        init();

    }

    private void init() {
        ivloading = (ImageView) findViewById(R.id.iv_loading_one);
        tvloading = (TextView) findViewById(R.id.tv_loading_one);
        sousuo = (EditText) findViewById(R.id.et_diange_sousuo);
        tv = (TextView) findViewById(R.id.tv_diange_sousuo);
        lv = (ListView) findViewById(R.id.lv_diange_sousuo);
        tv.setOnClickListener(l);
    }

    View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (sousuo.getText() == null || sousuo.getText().length() < 1) {
                return;
            } else {
//                visit(sousuo.getText()+"");
                ivloading.setVisibility(View.VISIBLE);
                tvloading.setVisibility(View.VISIBLE);
                AnimationDrawable ad = (AnimationDrawable) ivloading.getDrawable();
                ad.start();
                songsOperation = new SongsOperation(handler);

                songsOperation.getSongsBean(sousuo.getText().toString());
//                buildAdapter();

            }

        }
    };

    public void buildAdapter() {
        if (adapter == null) {
            adapter = new SouSuoAdapter(DgSouSuoActivity.this, singInfo, imalist);
            lv.setAdapter(adapter);
        } else {
            adapter.datachange(singInfo, imalist);
        }
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
//                    for (int i=0;i<imgs.size();i++){
//                        Log.i("result",imalist.get(i)+"----"+i);
//                    }
                    ivloading.setVisibility(View.GONE);
                    tvloading.setVisibility(View.GONE);
                    AnimationDrawable ad = (AnimationDrawable) ivloading.getDrawable();
                    ad.stop();
                    buildAdapter();
                    break;
            }
        }
    };
}