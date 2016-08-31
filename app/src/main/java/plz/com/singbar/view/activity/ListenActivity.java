package plz.com.singbar.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    private TextView singnum;
    private TextView comment;
    private TextView flower;
    private FocusitemInfo info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
        init();
    }

    private void init() {
        head= (ImageView) findViewById(R.id.iv_listen_user);
        name= (TextView) findViewById(R.id.tv_listen_username);
        singnum= (TextView) findViewById(R.id.tv_activity_singnum);
        comment= (TextView) findViewById(R.id.tv_activity_comment);
        flower= (TextView) findViewById(R.id.tv_activity_flower);

        Intent intent=getIntent();
        info= (FocusitemInfo) intent.getSerializableExtra("key");
        name.setText(info.getName());
        singnum.setText(info.getSingnum()+"");
        comment.setText(info.getComment()+"");
        flower.setText(info.getFlower()+"");


    }
    public void click(View view){
        switch (view.getId()){
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
