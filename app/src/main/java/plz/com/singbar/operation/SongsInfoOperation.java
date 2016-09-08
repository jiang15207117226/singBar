package plz.com.singbar.operation;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import plz.com.singbar.view.info.SingInfo;
import plz.com.singbar.view.info.SingInfoo;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class SongsInfoOperation extends AsyncTask<String,Void,String> {

    private String json;

    private  String head="http://apis.baidu.com/geekery/music/query";

    @Override
    protected String doInBackground(String... songNames) {

        Log.i("result",songNames[0]);
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(head+"?"+"s="+songNames[0]).addHeader("apikey","9f021592a34b6c446b4557778852774f")
                .get()
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                json = response.body().string();
                Log.i("result",json);
            }
        });
        Log.i("result",json+"----");
        return json;
    }


    @Override
    protected void onPostExecute(String s) {
        Log.i("result",s);
        JSONObject json =null;

    }

}
