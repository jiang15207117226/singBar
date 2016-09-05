package plz.com.singbar.view.activity;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import plz.com.singbar.R;


/**
 * Created by Administrator on 2016/7/13.
 */
public class MyService extends Service {
    private MediaPlayer player;
    private boolean ispause=false;
    private int position=0;

    public class MyIBinder extends Binder {
        public void play(){
            player.start();
        }
        public void pause(){
            if(ispause){
                player.seekTo(position);
                player.start();
                ispause=false;
            }else {
                player.pause();
                position=player.getCurrentPosition();
                ispause=true;
            }
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
