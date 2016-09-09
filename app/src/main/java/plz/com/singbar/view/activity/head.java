package plz.com.singbar.view.activity;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class Head {
    private String str;
    String url;
    public Head(String str){
        this.str=str;
    }
    public String visit(){
        String head="http://apis.baidu.com/geekery/music/query";
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(head+"?"+"s="+str).addHeader("apikey","9f021592a34b6c446b4557778852774f")
                .get()
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String st=response.body().string();

                JSONObject json =null;
                try {
                    json=new JSONObject(st);
                    JSONObject data=json.optJSONObject("data");
                    // Log.i("result",data.toString());
                    String a=null;
                    if (data!=null){

                        a = data.getString("image");
                        url=a;
                    }
                    if (a!=null){

                    }

                } catch (JSONException e) {

                }

            }
        });

    return url;
    }




    private final OkHttpClient client = new OkHttpClient();

    public String run() throws IOException {
        String head="http://apis.baidu.com/geekery/music/singer";
        Request request = new Request.Builder()
                .url(head+"?"+"name="+str).addHeader("apikey","9f021592a34b6c446b4557778852774f")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }
         //   System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }


        String st=response.body().string();

        JSONObject json =null;
        try {
            json=new JSONObject(st);
            JSONObject data=json.optJSONObject("data");
            // Log.i("result",data.toString());
            String a=null;
            if (data!=null){

                a = data.getString("image");
                url=a;
            }
            if (a!=null){

            }

        } catch (JSONException e) {

        }



      return url;
    }

}
