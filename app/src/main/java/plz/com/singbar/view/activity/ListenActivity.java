package plz.com.singbar.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import plz.com.singbar.R;
import plz.com.singbar.view.info.FocusitemInfo;


/**
 * Created by Administrator on 2016/8/29.
 */
public class ListenActivity extends Activity {
    private ImageView head;
    private TextView name;
    private TextView time;
    private TextView singnum;
    private TextView comment;
    private TextView flower;
    private FocusitemInfo info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        this.getWindow().setStatusBarColor(getResources().getColor(R.color.home_bottom_rg_bg));
        init();
    }

    private void init() {
        head= (ImageView) findViewById(R.id.iv_listen_user);
        name= (TextView) findViewById(R.id.tv_listen_username);
        time= (TextView) findViewById(R.id.tv_activity_time);
        singnum= (TextView) findViewById(R.id.tv_activity_singnum);
        comment= (TextView) findViewById(R.id.tv_activity_comment);
        flower= (TextView) findViewById(R.id.tv_activity_flower);

        Intent intent=getIntent();
        info= (FocusitemInfo) intent.getSerializableExtra("key");
        name.setText(info.getName());
        time.setText(info.getTime());
        singnum.setText(info.getSingnum()+"");
        comment.setText(info.getComment()+"");
        flower.setText(info.getFlower()+"");


    }
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_activity_up:

                break;
            case R.id.iv_activity_play:

                break;
            case R.id.iv_activity_down:

                break;
            case R.id.iv_focus_listen:

                break;
            case R.id.iv_activity_listen:
                singnum.setText(info.getSingnum()+1+"");
                break;
            case R.id.iv_activity_comment:
                comment.setText(info.getComment()+1+"");
                break;
            case R.id.iv_activity_flower:
                flower.setText(info.getFlower()+1+"");
                break;
            case R.id.iv_listen_back:
                finish();
                break;

        }

    }
}
