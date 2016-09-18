package plz.com.singbar.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import plz.com.singbar.R;
import plz.com.singbar.operation.SongO;
import plz.com.singbar.view.adapter.GxAdapter;
import plz.com.singbar.view.info.DgGxInfo;
import plz.com.singbar.view.info.NewSongInfo;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class HistortActivity extends Activity{
    private ListView lv;
    private DgGxInfo info;
    private List<DgGxInfo> list;
    private GxAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        lv= (ListView) findViewById(R.id.lv_his);
        list=new ArrayList<>();

        if(list!=null){
            adapter=new GxAdapter(HistortActivity.this,list);
            adapter.change(list);
            lv.setAdapter(adapter);
        }


       songInfo("十年");

    }
    public void listinfo(List<DgGxInfo> list){
        this.list=list;
    }



    private String spec="http://tingapi.ting.baidu.com/v1/restserver/ting";
    int n ;
    public void songInfo(String key) {
        String k="method=baidu.ting.search.catalogSug&query="+"演员";
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(spec+"?"+k)
                .get()
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String a = response.body().string();
         //       Log.i("result","#**********"+a);
                try {
                    JSONObject jsonObject = new JSONObject(a);
                    Gson gson = new Gson();
                    final NewSongInfo info = gson.fromJson(jsonObject.toString(),NewSongInfo.class);

                    for(int i =0;i<info.getSong().size();i++) {
                        n=i;
                        String id =info.getSong().get(i).getSongid();
                        String url = "method=baidu.ting.song.play&songid="+id;
                        OkHttpClient mOkHttpClient = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url(spec + "?" + url)
                                .get()
                                .build();
                        Call callo = mOkHttpClient.newCall(request);
                        callo.enqueue(new Callback() {


                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                String aa=response.body().string();
                                Log.i("result",aa);
                                try {
                                    JSONObject object = new JSONObject(aa);
                                    JSONObject object1 = object.getJSONObject("songinfo");
                                    JSONObject object2 = object.optJSONObject("bitrate");
                                    String ima = (String) object1.opt("pic_big");
                                    String gc = object1.optString("lrclink");
                                    String u = object2.optString("file_link");
                                    info.getSong().get(n).setGeci(gc);
                                    info.getSong().get(n).setIma(ima);
                                    info.getSong().get(n).setUrl(u);
                    //                Log.i("result","^^^^^^^^^^^^^^^"+info.getSong().get(n).getIma());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }








                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
