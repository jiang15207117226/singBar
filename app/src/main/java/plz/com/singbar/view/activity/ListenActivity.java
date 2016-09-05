package plz.com.singbar.view.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import plz.com.singbar.R;
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.bean.UserOwnSongsBean;


/**
 * Created by Administrator on 2016/8/29.
 */
public class ListenActivity extends Activity implements CompoundButton.OnCheckedChangeListener{
    private MyService.MyIBinder binder;
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
    private PopupWindow pw;
    private LayoutInflater inflater;
    private EditText et;
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
        Intent intents=new Intent(this, MyService.class);
        bindService(intents,connection,BIND_AUTO_CREATE);
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
        sorce.setText("...来听听我唱的《"+songsBean.getSongName()+"》");
        time.setText(songsBean.getTime());
        singnum.setText(songsBean.getTrys()+"");
        comment.setText(songsBean.getComments()+"");
        flower.setText(songsBean.getFlowers()+"");

        atten.setOnCheckedChangeListener(this);

        inflater = LayoutInflater.from(this);
        View popupwindow = inflater.inflate(R.layout.item_comment, null);
        et= (EditText) popupwindow.findViewById(R.id.et_comment);
        pw = new PopupWindow(popupwindow, 1000, 800);
        pw.setTouchable(true);
        pw.setOutsideTouchable(true);
        pw.setBackgroundDrawable(new ColorDrawable(0000));
        pw.setFocusable(true);
    }
    ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder= (MyService.MyIBinder) service;

        }
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    public void click(View view){
        if (binder==null){
            return;
        }
        int comments=Integer.parseInt(comment.getText().toString());
        int flowers=Integer.parseInt(flower.getText().toString());
        switch (view.getId()){
            case R.id.rb_activity_up:

                break;
            case R.id.cb_activity_play:
                if (play.isChecked()){
                    binder.play();
                }else {
                    binder.pause();
                }

                break;
            case R.id.rb_activity_down:

                break;
            case R.id.tv_user_share:
                String s="来听听我唱的《"+songsBean.getSongName()+"》";
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,s);
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "分享到"));
                break;
            case R.id.cb_user_comment:
                if (wComment.isChecked()){
                    et.setText(null);
                    comment.setText(comments+1+"");
                    pw.showAtLocation(view, Gravity.CENTER, 0, 300);
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
            case R.id.bt_comment_no:
                pw.dismiss();
                break;
            case R.id.bt_comment_finish:
                songsBean.setComment(et.getText().toString());
                pw.dismiss();
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
