package plz.com.singbar.view.activity;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import plz.com.singbar.R;


/**
 * Created by Administrator on 2016/7/13.
 */
public class MyService extends Service {
    public MediaPlayer player=new MediaPlayer();
    public int position=0;
    private int musicposition=0;
    public List<File> list;
    public List<Map<String, String>> data;
    private String name;

    public class MyIBinder extends Binder {
        public void getmusic() {
            list=new ArrayList<File>();
            data = new ArrayList<Map<String,String>>();
            for (File file : list) {
                Map<String, String> map = new HashMap<String,String>();
                map.put("name", file.getName());
                map.put("path", file.getAbsolutePath());
                data.add(map);
            }
        }
        public MediaPlayer getplayer(){
            return player;
        }
        public String getmusicname(){
            if (data!=null){
            name=data.get(musicposition).get("name");
        }
            return name;
        }
        public int getDuration(){
            int duration = 0;
            if (player!= null) {
                duration = player.getDuration();
            }
            return duration;
        }
        public int getCurrentPosition(){
            if (player!= null) {
                position = player.getCurrentPosition();
            }
            return position;
        }
        public void seekTo(int position) {
            if (player != null) {
                player.seekTo(position);
            }
        }
        public void play(){
//            try {
                player.seekTo(position);
//                player.setDataSource(data.get(musicposition).get("path"));
                player.start();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        public void pause(){
                player.pause();
                position=player.getCurrentPosition();
        }
        public void playup(){
            if (musicposition-1<0){
                musicposition=data.size()-1;
            }else {
                musicposition--;
            }
            play();
        }
        public void playdown(){
            if (musicposition+1>data.size()){
                musicposition=0;
            }else {
                musicposition++;
            }
            play();
        }

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        createMediaPlayer(R.raw.lianrenxing);
        return new MyIBinder();
    }
    public MediaPlayer createMediaPlayer(int resId){
        player= MediaPlayer.create(this,resId);
        return player;
    }
}
