package plz.com.singbar.view.activity;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;


/**
 * Created by Administrator on 2016/7/13.
 */
public class MyService extends Service{
    public MediaPlayer player;
    public int position = 0;
    private int musicposition = 0;



    public class MyIBinder extends Binder{
        public MediaPlayer getplayer() {
            return player;
        }

        public int getDuration() {
            int duration = 0;
            if (player != null) {
                duration = player.getDuration();
            }
            return duration;
        }

        public int getCurrentPosition() {
            if (player != null) {
                position = player.getCurrentPosition();
            }
            return position;
        }

        public void seekTo(int position) {
            if (player != null) {
                MyService.this.player.seekTo(position);
            }
        }

        public void play() {
            MyService.this.player.start();
        }

        public void pause() {
            player.pause();
            position = player.getCurrentPosition();
            player.seekTo(position);
        }

        public void playup() {
            play();
        }

        public void playdown() {
            play();
        }
        public void getPath(String path){
            createMediaPlayer(path);
            Log.i("result",path+"-----getPath");
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("result","onBind");
        return new MyIBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("result","onCreate");
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i("result","onRebind");
    }


    public MediaPlayer createMediaPlayer(String path) {
        try {
            player=new MediaPlayer();
            player.setDataSource("file://"+path);
            player.prepare();
//            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return player;
    }

}
