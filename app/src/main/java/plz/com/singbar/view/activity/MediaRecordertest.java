package plz.com.singbar.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import plz.com.singbar.R;
import plz.com.singbar.bean.UserOwnSongsBean;
import plz.com.singbar.operation.DbOperation;
import plz.com.singbar.operation.UserIdConfig;
import plz.com.singbar.view.info.BdSingInfo;
import plz.com.singbar.view.info.DgGxInfo;
import plz.com.singbar.view.info.SingInfoo;


/**
 * Created by Administrator on 2016/9/6.
 */
public class MediaRecordertest extends Activity implements CompoundButton.OnCheckedChangeListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener {
    private String geci;
    private String geming;
    private int shijian;
    private long dur;
    private CheckBox cancle;
    private CheckBox play;
    private CheckBox stop;
    private ProgressBar progressBar;
    private TextView time;
    private TextView singer;
    private Timer mTimer = new Timer();
    private File fi;
    private File record;
    private File path;
    private Uri uri;
    private String songUrl;
    private String temp = "recaudio_";// 临时文件前缀
    private MediaRecorder mediaRecorder;
    private List<String> rec = new ArrayList<String>();// 存放录音文件
    MediaPlayer mediaPlayer1;
    private MediaPlayer player = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_singtime);
        init();
//        String path = fi + File.separator + rec.get(i);
//        File files = new File(path);
//        PlayMusic(files);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = Environment.getExternalStorageDirectory();
            fi = new File(file, "麦吧录音临时文件");
            record = new File(file, "Mb录音");
            if (!fi.exists()) {
                fi.mkdirs();
            }
            if (!record.exists()) {
                record.mkdirs();
            }
        } else {
            Toast.makeText(this, "请先插入SD卡", Toast.LENGTH_LONG).show();
            return;
        }
        int tag = getIntent().getIntExtra("tag", -1);
        if (tag == 0) {
            SingInfoo infoo = (SingInfoo) getIntent().getSerializableExtra("data");
            songUrl = infoo.getUrl();
            geming = infoo.getFilename();
            shijian = infoo.getDuration();
            geci = infoo.getContext();
        } else if (tag == 1) {
            DgGxInfo dgGxInfo = (DgGxInfo) getIntent().getSerializableExtra("data");
            songUrl = dgGxInfo.getPlayurl();
            geming = dgGxInfo.getSingname();
        } else if (tag == 2) {
            BdSingInfo info = (BdSingInfo) getIntent().getSerializableExtra("data");
            songUrl = info.getUri();
            geming = info.getSname();
            dur = info.getDx();
        }
        singer.setText(geming);
        mediaPlayer1 = new MediaPlayer();
        uri = Uri.parse(songUrl);
        Log.i("result", "1111--:" + songUrl);
        try {
//            mediaPlayer1.setDataSource(this,uri);
            mediaPlayer1.setDataSource(songUrl);
            mediaPlayer1.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer1.setOnBufferingUpdateListener(this);
            mediaPlayer1.setOnPreparedListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 显示录音列表
         */
    }

    public void MusicList() {
        File[] f = fi.listFiles(new MusicFilter());
        rec.clear();
        for (int i = 0; i < f.length; i++) {
            File file = f[i];
            rec.add(file.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rec);
    }


    public void init() {
        cancle = (CheckBox) findViewById(R.id.rb_quxiao);
        play = (CheckBox) findViewById(R.id.start_bt);
        stop = (CheckBox) findViewById(R.id.end_bt);
        progressBar = (ProgressBar) findViewById(R.id.pb_bar);
        time = (TextView) findViewById(R.id.tv_tape_time);
        singer = (TextView) findViewById(R.id.tv_sing_singname);
        mTimer.schedule(mTimerTask, 0, 1000);

        cancle.setOnCheckedChangeListener(this);
        play.setOnCheckedChangeListener(this);
        stop.setOnCheckedChangeListener(this);
    }

    public void PlayMusic(File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "audio");
        this.startActivity(intent);
    }

    private void playMedia(String videoUrl) {
        try {
            // 创建录音临时文件
            path = File.createTempFile(temp, ".MP3", fi);
            setTitle("==" + path.getAbsolutePath());
            mediaRecorder = new MediaRecorder();
            // 设置数据来源，麦克风
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            // 设置格式
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
            // 设置编码
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            // 设置输出文件路径
            mediaRecorder.setOutputFile(path.getAbsolutePath());
            mediaPlayer1.reset();
            Log.i("result","videoUrl-->"+videoUrl);
            mediaPlayer1.setDataSource(videoUrl);
            mediaPlayer1.prepareAsync();//prepare
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (mediaPlayer1 == null)
                return;
            if (mediaPlayer1.isPlaying() && progressBar.isPressed() == false) {

                handleProgress.sendEmptyMessage(0);
            }
        }
    };
    Handler handleProgress = new Handler() {
        public void handleMessage(Message msg) {

            int position = mediaPlayer1.getCurrentPosition();
            int duration = mediaPlayer1.getDuration();
            Log.i("result", position + "==============" + duration);
            if (duration > 0) {
                long pos = progressBar.getMax() * position / duration;
                progressBar.setProgress((int) pos);
                time.setText(parseDur(position) + "/" + parseDur(duration));
            }
        }
    };

    private String parseDur(int i) {
        int s = i / 1000;
        int m = s / 60;
        return m + ":" + (s - 60 * m);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton == cancle) {
            if (b) {
                Log.i("result", "cancle");
                if (mediaPlayer1.isPlaying()){
                    mediaPlayer1.stop();
                    mediaRecorder.stop();
                }
                mediaPlayer1.release();
                mediaRecorder.release();
                mediaRecorder = null;
                finish();
            }
        } else if (compoundButton == play) {
            if (b) {
                playMedia(songUrl);
                Log.i("result", "play");
            } else {
                if (mediaPlayer1.isPlaying()) {
                    Log.i("result", "pause");
                    mediaPlayer1.pause();
//                   mediaRecorder.pause();
                }
            }
        } else if (compoundButton == stop) {
            Log.i("result", "stop");
            if (b) {
                Log.i("result", "stop--check");
                mediaPlayer1.stop();
                mediaRecorder.stop();
                stop.setClickable(false);
                stop.setFocusable(false);
                mediaRecorder = null;
                File file=new File(record.getAbsolutePath() + File.separator + path.getName());
                path.renameTo(file);
                UserOwnSongsBean userOwnSongsBean=new UserOwnSongsBean();
                userOwnSongsBean.setSongName(geming);
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                userOwnSongsBean.setTime(format.format(new Date()));
                userOwnSongsBean.setVoiceUrl(file.getAbsolutePath());
                userOwnSongsBean.save();
                int id= UserIdConfig.id;
                Log.i("result",id+"--userid");
                DbOperation.updateSongsUserId(id,userOwnSongsBean.getId());
            }
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        progressBar.setSecondaryProgress(i);
        int currentProgress = progressBar.getMax() * mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration();
        Log.i(currentProgress + "% play", i + "% buffer");
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        Log.i("result", "start___>");
    }
}


