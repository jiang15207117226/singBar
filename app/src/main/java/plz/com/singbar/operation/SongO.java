package plz.com.singbar.operation;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

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
import plz.com.singbar.view.info.NewSongInfo;
import plz.com.singbar.view.info.SingInfo;
import plz.com.singbar.view.info.SingInfoo;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class SongO {
    private Handler handler;
    private SingInfo singInfo;
    private List<SingInfoo> list;
    private String spec="http://tingapi.ting.baidu.com/v1/restserver/ting";
    int n ;
    public SongO(Handler handler) {
        this.handler = handler;
    }
    public void songInfo(String key) {
        singInfo=new SingInfo();
        list=new ArrayList<>();
        String k="method=baidu.ting.search.catalogSug&query="+key;
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
                     NewSongInfo info = gson.fromJson(jsonObject.toString(),NewSongInfo.class);

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
                                SingInfoo singInfoo = new SingInfoo();
                                String aa=response.body().string();
                                Log.i("result",aa);
                                try {
                                    JSONObject object = new JSONObject(aa);
                                    JSONObject object1 = object.getJSONObject("songinfo");
                                    JSONObject object2 = object.optJSONObject("bitrate");
                                    String ima = (String) object1.opt("pic_big");
                                    String name = object1.optString("title");

                                    String gc = object1.optString("lrclink");
                                    String u = object2.optString("file_link");
                                    int dx = object2.optInt("file_size");

                                    String singer=object1.optString("author");
                                    singInfoo.setContext(gc);
                                    singInfoo.setSingername(singer);
                                    singInfoo.setUrl(u);
                                    Log.i("result",""+u);
                                    singInfoo.setIma(ima);
                                    singInfoo.setFilesize(dx);
                                    singInfoo.setFilename(name);

                                    list.add(singInfoo);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                        singInfo.setSingInfoo(list);
                        Message msg = new Message();
                        msg.obj = singInfo;
                        msg.what = 1;
                        handler.sendMessage(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
