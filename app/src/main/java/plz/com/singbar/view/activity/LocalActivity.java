package plz.com.singbar.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.bean.UserOwnSongsBean;
import plz.com.singbar.operation.DbOperation;
import plz.com.singbar.operation.UserIdConfig;
import plz.com.singbar.view.adapter.LocalAdapter;

/**
 * Created by Administrator on 2016/9/18.
 */
public class LocalActivity extends Activity {
    private ListView localVoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        getWindow().setStatusBarColor(getResources().getColor(R.color.zise));
        setContentView(R.layout.activity_localvoice);
        init();
    }

    private void init() {
        localVoice= (ListView) findViewById(R.id.lv_local);
        List<UserOwnSongsBean>list=DbOperation.querySongs(UserIdConfig.id);
        if (list!=null&&list.size()>0){
            LocalAdapter adapter=new LocalAdapter(this,list);
            localVoice.setAdapter(adapter);
        }
    }
}
