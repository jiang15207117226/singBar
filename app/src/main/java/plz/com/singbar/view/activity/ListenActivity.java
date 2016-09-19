package plz.com.singbar.view.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import plz.com.singbar.R;
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.bean.UserOwnSongsBean;


/**
 * Created by Administrator on 2016/8/29.
 */
public class ListenActivity extends Activity implements CompoundButton.OnCheckedChangeListener {
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
    private UserOwnSongsBean songsBean;
    private UserBean bean;
    private Button play;
    private CheckBox atten;
    private PopupWindow pw;
    private LayoutInflater inflater;
    private EditText et;
    private SeekBar seekBar;
    private TextView playtime;
    private TextView musictime;
    private SimpleDateFormat dateFormat;
    private TextView textView;
    private String path;
    private boolean isFirst = true;

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

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyService.MyIBinder) service;
            binder.getplayer().setOnCompletionListener(musiclistener);
            binder.getPath(path);
            seekBar.setMax(binder.getDuration());
            musictime.setText(dateFormat.format(binder.getDuration()));
//            songName.setText(binder.getmusicname());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void init() {
        Intent intent = getIntent();
        songsBean = (UserOwnSongsBean) intent.getSerializableExtra("songsBean");
        bean = (UserBean) intent.getSerializableExtra("userBean");
        dateFormat = new SimpleDateFormat("mm:ss");
        Intent intents = new Intent(this, MyService.class);
        path = songsBean.getVoiceUrl();
        Log.i("result", path);
        bindService(intents, connection, BIND_AUTO_CREATE);
        giveFlower = (CheckBox) findViewById(R.id.cb_user_flower);
        play = (Button) findViewById(R.id.cb_activity_play);
        atten = (CheckBox) findViewById(R.id.cb_focus_listen);
        head = (ImageView) findViewById(R.id.iv_listen_user);
        name = (TextView) findViewById(R.id.tv_listen_username);
        callName = (TextView) findViewById(R.id.tv_listen_callName);
        fansCount = (TextView) findViewById(R.id.tv_listen_fans);
        songName = (TextView) findViewById(R.id.tv_listen_name);
        sorce = (TextView) findViewById(R.id.tv_listen_source);
        time = (TextView) findViewById(R.id.tv_activity_time);
        singnum = (TextView) findViewById(R.id.tv_activity_singnum);
        comment = (TextView) findViewById(R.id.tv_activity_comment);
        flower = (TextView) findViewById(R.id.tv_activity_flower);
        seekBar = (SeekBar) findViewById(R.id.sb_activity);
        playtime = (TextView) findViewById(R.id.tv_music_playtime);
        musictime = (TextView) findViewById(R.id.tv_music_time);
        textView = (TextView) findViewById(R.id.tv_comment_content);

        name.setText(bean.getPetName());
        callName.setText(bean.getButility());
        fansCount.setText(bean.getFansCount() + "");
        songName.setText(songsBean.getSongName());
        sorce.setText("...来听听我唱的《" + songsBean.getSongName() + "》");
        time.setText(songsBean.getTime());
        singnum.setText(songsBean.getTrys() + "");
        comment.setText(songsBean.getComments() + "");
        flower.setText(songsBean.getFlowers() + "");

        atten.setOnCheckedChangeListener(this);
        seekBar.setOnSeekBarChangeListener(sbchangelistener);

        inflater = LayoutInflater.from(this);
        View popupwindow = inflater.inflate(R.layout.item_comment, null);
        et = (EditText) popupwindow.findViewById(R.id.et_comment);
        pw = new PopupWindow(popupwindow, 1000, 800);
        pw.setTouchable(true);
        pw.setOutsideTouchable(true);
        pw.setBackgroundDrawable(new ColorDrawable(0000));
        pw.setFocusable(true);
    }

    MediaPlayer.OnCompletionListener musiclistener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            binder.playdown();
        }
    };
    Handler handler = new Handler();
    Runnable updateThread = new Runnable() {
        public void run() {
            if (binder == null) {
                return;
            }
            //获得歌曲现在播放位置并设置成播放进度条的值
            seekBar.setProgress(binder.getCurrentPosition());
            playtime.setText(dateFormat.format(binder.getCurrentPosition()));
            //每次延迟100毫秒再启动线程
            handler.postDelayed(updateThread, 100);
        }
    };

    public void click(View view) {
        if (binder == null) {
            return;
        }
        int comments = Integer.parseInt(comment.getText().toString());
        int flowers = Integer.parseInt(flower.getText().toString());
        switch (view.getId()) {
            case R.id.rb_activity_up:
                binder.playup();
                play.setBackground(getDrawable(R.mipmap.minibar_btn_pause_normal));
                break;
            case R.id.cb_activity_play:
                if (binder.getplayer().isPlaying()) {
                    binder.pause();
                    play.setBackground(getDrawable(R.mipmap.minibar_btn_play_normal));
                } else {
                    if (isFirst) {
                        Log.i("result", "first");
                        if (binder != null) {
                            binder.prepare();
                        }
                        isFirst = false;
                    } else {
                        binder.play();
                    }
                    play.setBackground(getDrawable(R.mipmap.minibar_btn_pause_normal));
                }
                handler.post(updateThread);
                break;
            case R.id.rb_activity_down:
                binder.playdown();
                play.setBackground(getDrawable(R.mipmap.minibar_btn_pause_normal));
                break;
            case R.id.tv_user_share:
                String s = "来听听我唱的《" + songsBean.getSongName() + "》";
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, s);
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "分享到"));
                break;
            case R.id.tv_user_comment:
                pw.showAtLocation(view, Gravity.CENTER, 0, 300);
                pw.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                pw.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
                break;
            case R.id.cb_user_flower:
                if (giveFlower.isChecked()) {
                    flower.setText(flowers + 1 + "");
                } else {
                    flower.setText(flowers - 1 + "");
                }
                break;
            case R.id.iv_listen_back:
                finish();
                break;
            case R.id.bt_comment_no:
                pw.dismiss();
                break;
            case R.id.bt_comment_finish:
                comment.setText(comments + 1 + "");
                songsBean.setComment(et.getText().toString());
                pw.dismiss();
                textView.setText(bean.getPetName() + "刚刚发表了评论:" + songsBean.getComment());
                Toast.makeText(this, "评论成功", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            Drawable attention = getResources().getDrawable(R.mipmap.icon_right, null);
            attention.setBounds(0, 0, attention.getMinimumWidth(), attention.getMinimumHeight());
            atten.setCompoundDrawables(attention, null, null, null);
            atten.setText("已关注");
            atten.setTextColor(getResources().getColor(R.color.mine_item_opus_name_color));
        } else {
            atten.setCompoundDrawables(null, null, null, null);
            atten.setText("关注");
            atten.setTextColor(Color.WHITE);
        }
    }

    SeekBar.OnSeekBarChangeListener sbchangelistener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if (b) {
                if (binder == null) {
                    return;
                }
                binder.seekTo(i);
                handler.post(updateThread);
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (binder == null) {
                return;
            }
            binder.seekTo(seekBar.getProgress());
            handler.post(updateThread);
        }
    };

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }

}
