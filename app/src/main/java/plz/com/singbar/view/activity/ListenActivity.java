package plz.com.singbar.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import plz.com.singbar.R;
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.bean.UserOwnSongsBean;


/**
 * Created by Administrator on 2016/8/29.
 */
public class ListenActivity extends Activity implements CompoundButton.OnCheckedChangeListener{
    private ImageView head;
    private TextView name;
    private TextView callName;
    private TextView fansCount;
    private TextView songName;
    private TextView sorce;
    private TextView time;
    private TextView singnum;
    private TextView comment;
    private TextView flower;
    private CheckBox giveFlower;
    private CheckBox wComment;
    private UserOwnSongsBean songsBean;
    private UserBean bean;
    private CheckBox play;
    private CheckBox atten;
    private MediaPlayer player;
    private boolean ispause=false;
    private int position=0;
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
        giveFlower= (CheckBox) findViewById(R.id.cb_user_flower);
        wComment= (CheckBox) findViewById(R.id.cb_user_comment);
        play= (CheckBox) findViewById(R.id.cb_activity_play);
        atten= (CheckBox) findViewById(R.id.cb_focus_listen);
        head= (ImageView) findViewById(R.id.iv_listen_user);
        name= (TextView) findViewById(R.id.tv_listen_username);
        callName= (TextView) findViewById(R.id.tv_listen_callName);
        fansCount= (TextView) findViewById(R.id.tv_listen_fans);
        songName= (TextView) findViewById(R.id.tv_listen_name);
        sorce= (TextView) findViewById(R.id.tv_listen_source);
        time= (TextView) findViewById(R.id.tv_activity_time);
        singnum= (TextView) findViewById(R.id.tv_activity_singnum);
        comment= (TextView) findViewById(R.id.tv_activity_comment);
        flower= (TextView) findViewById(R.id.tv_activity_flower);

        Intent intent=getIntent();
        songsBean= (UserOwnSongsBean) intent.getSerializableExtra("songsBean");
        bean= (UserBean) intent.getSerializableExtra("userBean");
        name.setText(bean.getPetName());
        callName.setText(bean.getButility());
        fansCount.setText(bean.getFansCount()+"");
        songName.setText(songsBean.getSongName());
        sorce.setText("来听听我唱的《"+songsBean.getSongName()+"》");
        time.setText(songsBean.getTime());
        singnum.setText(songsBean.getTrys()+"");
        comment.setText(songsBean.getComments()+"");
        flower.setText(songsBean.getFlowers()+"");

        player=new MediaPlayer();
        player=MediaPlayer.create(this,R.raw.lianrenxing);

        atten.setOnCheckedChangeListener(this);
    }
    public void click(View view){
        int comments=Integer.parseInt(comment.getText().toString());
        int flowers=Integer.parseInt(flower.getText().toString());
        switch (view.getId()){
            case R.id.rb_activity_up:

                break;
            case R.id.cb_activity_play:
                if (play.isChecked()){
                    player.start();
                }else {
                    if(ispause){
                        player.seekTo(position);
                        player.start();
                        ispause=false;
                    }else {
                        player.pause();
                        position = player.getCurrentPosition();
                        ispause = true;
                    }
                }

                break;
            case R.id.rb_activity_down:

                break;
            case R.id.iv_focus_listen:

                break;
            case R.id.tv_user_share:

                break;
            case R.id.cb_user_comment:
                if (wComment.isChecked()){
                    comment.setText(comments+1+"");
                }else{
                    comment.setText(comments-1+"");
                }
                break;
            case R.id.cb_user_flower:
                if(giveFlower.isChecked()){
                    flower.setText(flowers+1+"");
                }else{
                    flower.setText(flowers-1+"");
                }
                break;
            case R.id.iv_listen_back:
                finish();
                break;

        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b){
            Drawable attention = getResources().getDrawable(R.mipmap.icon_right, null);
            attention.setBounds(0, 0, attention.getMinimumWidth(), attention.getMinimumHeight());
            atten.setCompoundDrawables(attention,null, null, null);
            atten.setText("已关注");
            atten.setTextColor(getResources().getColor(R.color.mine_item_opus_name_color));
        }else{
            atten.setCompoundDrawables(null,null, null, null);
            atten.setText("关注");
            atten.setTextColor(Color.WHITE);
        }
    }
}
