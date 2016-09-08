package plz.com.singbar.view.activity;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;


import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.view.info.DgGxInfo;


/**
 * Created by Administrator on 2016/7/13.
 */
public class MyService extends Service {
    public MediaPlayer player=new MediaPlayer();
    public int position=0;
    private int musicposition;
    public List<DgGxInfo> list;

    public class MyIBinder extends Binder {
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
            player.seekTo(position);
            player.start();
        }
        public void pause(){
                player.pause();
                position=player.getCurrentPosition();
        }
        public void playup(){
            if (musicposition-1<0){
                musicposition=list.size()-1;
            }else {
                musicposition--;
            }
            player.start();
        }
        public void playdown(){
            if (musicposition+1>list.size()){
                musicposition=0;
            }else {
                musicposition++;
            }
            player.start();
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
