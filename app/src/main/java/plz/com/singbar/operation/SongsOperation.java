package plz.com.singbar.operation;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
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
import plz.com.singbar.view.info.SingInfo;
import plz.com.singbar.view.info.SingInfoo;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class SongsOperation {
    private String head = "http://apis.baidu.com/geekery/music/query";
    private String singerHead = "http://apis.baidu.com/geekery/music/singer";
    private Handler handler;
    private int i = 0;
    private List<String> imgs = new ArrayList<>();

    public SongsOperation(Handler handler) {
        this.handler = handler;
    }

    public void getSongsBean(String songName) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(head + "?" + "s=" + songName).addHeader("apikey", "bd3c27144607ec0617f96d91a0ec6b24")
                .get()
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                Log.i("result", json);
                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject(json);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final JSONObject object = jsonObject.optJSONObject("data");
                Gson gson = new Gson();
                Gson gsons = new Gson();
                SingInfo singInfo = gsons.fromJson(object.toString(), SingInfo.class);

                List<SingInfoo> listo = singInfo.getSingInfoo();

                JSONArray array = object.optJSONArray("data");
                for (int i = 0; i < array.length(); i++) {

                    try {
                        final SingInfoo singInfoo = gson.fromJson(array.get(i).toString(), SingInfoo.class);

                        String spec = "http://apis.baidu.com/geekery/music/playinfo";

                        OkHttpClient mOkHttpClient = new OkHttpClient();
                        final Request request = new Request.Builder()
                                .url(spec + "?hash=" + singInfoo.getHash()).addHeader("apikey", "bd3c27144607ec0617f96d91a0ec6b24")
                                .get()
                                .build();
                        Call callo = mOkHttpClient.newCall(request);
                        callo.enqueue(new Callback() {

                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String str = response.body().string();
                                JSONObject jsonObject = null;

                                try {
                                    jsonObject = new JSONObject(str);
                                    JSONObject data = jsonObject.optJSONObject("data");
                                    String url = data.optString("url");
                                    singInfoo.setUrl(url);
                                    Log.i("result", url + "===================================");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                        String speco = "http://apis.baidu.com/geekery/music/krc";

                        String arg = "?name=" + singInfoo.getFilename() + "&hash=" + singInfoo.getHash() + "&time=" + singInfoo.getDuration();
                        OkHttpClient mOkHttpCliento = new OkHttpClient();
                        final Request requesto = new Request.Builder()
                                .url(speco + arg).addHeader("apikey", "bd3c27144607ec0617f96d91a0ec6b24")
                                .get()
                                .build();
                        Call calloo = mOkHttpCliento.newCall(requesto);
                        calloo.enqueue(new Callback() {


                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String stro = response.body().string();
                                Log.i("result", "-------------------" + stro + "-------------------------");
                                JSONObject jsonObjecto = null;
                                try {
                                    jsonObjecto = new JSONObject(stro);
                                    JSONObject object1 = jsonObjecto.optJSONObject("data");
                                    if (object1 != null) {
                                        String ss = object1.optString("content");
                                        singInfoo.setContext(ss);
                                    }else{
                                        Log.i("result","这个是空");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                      listo.add(singInfoo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                singInfo.setSingInfoo(listo);
                Message msg = new Message();
                msg.obj = singInfo;
                msg.what = 1;
                handler.sendMessage(msg);

            }
        });
    }

    public void getSingerBean(String singerName, final int size) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(singerHead + "?" + "name=" + singerName).addHeader("apikey", "bd3c27144607ec0617f96d91a0ec6b24")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String st = response.body().string();
                JSONObject json = null;
                try {
                    json = new JSONObject(st);
                    JSONObject data = json.optJSONObject("data");
                    // Log.i("result",data.toString());
                    String a = null;
                    if (data != null) {

                        a = data.getString("image");

                    }

                    imgs.add(a);
                    if (a == null) {
                        Log.i("result", "null" + i);
                    } else {
                        Log.i("result", a + "--" + i);
                    }
                    ++i;
//                   Log.i("result","i="+i+"size="+size);
                    if (i == size) {
                        Message msg = new Message();
                        msg.what = 3;
                        msg.obj = getImgs();
                        handler.sendMessage(msg);
                    }
                } catch (JSONException e) {

                }

            }
        });
    }

    public List<String> getImgs() {
        return imgs;
    }


}
