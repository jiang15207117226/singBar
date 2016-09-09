package plz.com.singbar.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zcw.togglebutton.ToggleButton;

import plz.com.singbar.R;

/**
 * Created by Administrator on 2016/8/29 0029.
 */
public class SetActivity extends Activity{
    private TextView tv;
    private ToggleButton toggleBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        init();
        bulid();
    }



    private void init() {
        tv= (TextView) findViewById(R.id.tv_set_dataclean);
        toggleBtn= (ToggleButton) findViewById(R.id.tlbtn_tishiyin);
    }
    private void bulid() {
        String a;
        try {
          a=  DataClean.getTotalCacheSize(SetActivity.this);
            tv.setText(a+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        toggleBtn.setOnToggleChanged(onToggleChanged);
    }

    public void click(View v){
        switch (v.getId()){
            case R.id.tv_set_dataclean://清空缓存
                    DataClean.clearAllCache(SetActivity.this);
                try {
                    tv.setText(DataClean.getTotalCacheSize(SetActivity.this));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(SetActivity.this,"清除成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_set_about:
                Intent intent = new Intent(SetActivity.this,AboutActivity.class);
                startActivity(intent);
                break;
        }
    }
    //消息提示音开关监听
    ToggleButton.OnToggleChanged onToggleChanged = new ToggleButton.OnToggleChanged(){

        @Override
        public void onToggle(boolean on) {

            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            if(on){
                r.play();
            }else {
                r.stop();
            }


        }
    };
}
