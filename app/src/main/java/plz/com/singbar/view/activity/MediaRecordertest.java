package plz.com.singbar.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import plz.com.singbar.R;

/**
 * Created by Administrator on 2016/9/6.
 */
public class MediaRecordertest extends Activity {

    private ImageView im1;
    private ImageView im2;
    private ListView lv;
    private File fi;
    private File path;
    private String temp = "recaudio_";// 临时文件前缀
    private MediaRecorder mediaRecorder;
    private List<String> rec = new ArrayList<String>();// 存放录音文件



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singtime);
        init();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            fi = Environment.getExternalStorageDirectory();
            MusicList();
        } else {
            Toast.makeText(this, "请先插入SD卡", Toast.LENGTH_LONG).show();
            return;
        }

        //开始录音
        im1.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // 创建录音临时文件
                    path = File.createTempFile(temp, ".amr", fi);
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
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        im2.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaRecorder.release();
                mediaRecorder = null;
                MusicList();
            }
        });
        lv.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String path = fi + File.separator + rec.get(i);
                File files = new File(path);
                PlayMusic(files);
            }
        });
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
        lv.setAdapter(adapter);
    }

    public void init() {
        im1 = (ImageView) findViewById(R.id.start_bt);
        im2 = (ImageView) findViewById(R.id.end_bt);
        lv = (ListView) findViewById(R.id.singtiem_listview);
    }

    public void PlayMusic(File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "audio");
        this.startActivity(intent);
    }
}


