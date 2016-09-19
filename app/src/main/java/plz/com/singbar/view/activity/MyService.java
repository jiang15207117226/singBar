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
public class MyService extends Service implements MediaPlayer.OnPreparedListener{
    public MediaPlayer player = new MediaPlayer();
    public int position = 0;
    private int musicposition = 0;
    private String path;

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }


    public class MyIBinder extends Binder implements MediaPlayer.OnPreparedListener{
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
        public void prepare(){
            try {
                MyService.this.player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (IllegalStateException i){
                player=new MediaPlayer();
                createMediaPlayer(path);
                player.setOnPreparedListener(this);
            }
        }
        public void getPath(String path){
            createMediaPlayer(path);
            Log.i("result",path+"-----getPath");
        }

        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.start();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        createMediaPlayer(path);
        return new MyIBinder();
    }

    public MediaPlayer createMediaPlayer(String path) {
        try {
            Log.i("result", path + "------");
            player.setDataSource("file://"+path);
            Log.i("result",player.getDuration()+"--getDuration");
            player.setOnPreparedListener(this);

        } catch (IOException e) {
            e.printStackTrace();
        }catch (IllegalStateException i){
            player=new MediaPlayer();
            createMediaPlayer(path);
            player.setOnPreparedListener(this);
        }
        return player;
    }

}
