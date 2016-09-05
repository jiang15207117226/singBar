package plz.com.singbar.view.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import plz.com.singbar.R;
import plz.com.singbar.view.info.DgGxInfo;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class GxFragment extends Fragment{
  /*  String httpUrl = "http://apis.baidu.com/geekery/music/query";
    String httpArg = "s=十年&size=10&page=1";
    String jsonResult = request(httpUrl, httpArg);*/
    private View view;
    private ListView list;
    private List<DgGxInfo> gxlist;
    private String spec="http://apis.baidu.com/geekery/music/query?s=十年";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.frag_dg_gx,container,false);
        init();
        visit();
        return  view;

    }



    private void init() {
        list= (ListView) view.findViewById(R.id.lv_dg_gx);
        gxlist=new ArrayList<>();
    }
    private void visit() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(spec).addHeader("apikey","9f021592a34b6c446b4557778852774f")
                .get()
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback(){

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.i("result",str);
            }
        });



    }
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",  "9f021592a34b6c446b4557778852774f");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
