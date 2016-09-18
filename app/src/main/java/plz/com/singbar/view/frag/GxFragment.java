package plz.com.singbar.view.frag;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import plz.com.singbar.view.adapter.GxAdapter;
import plz.com.singbar.view.adapter.SouSuoAdapter;
import plz.com.singbar.view.info.DgGxInfo;
import plz.com.singbar.view.info.SingInfo;
import plz.com.singbar.view.info.SingInfoo;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class GxFragment extends Fragment{
    private View view;
    private ListView list;
    private List<DgGxInfo> gxlist;
    private GxAdapter adapter;
    private DgGxInfo info;
    private TextView tv;
    private ConvenientBanner con;
    private List<Integer> li;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.frag_dg_gx,container,false);
        init();
        build();
        return  view;

    }

    private void init() {
        list= (ListView) view.findViewById(R.id.lv_dg_gx);
        tv= (TextView) view.findViewById(R.id.tv_gx_one);
        con= (ConvenientBanner) view.findViewById(R.id.convenient_one);
        li=new ArrayList<>();
        gxlist=new ArrayList<>();
    }

    private void build() {
        info=new DgGxInfo();
        info.setPlayurl("http:\\/\\/yinyueshiting.baidu.com\\/data2\\/music\\/124598466\\/124598466.mp3?xcode=ce5922d63dec3d873cbb1604e4436b22");
        info.setIma("http://img1.music.response.itmf.cn/uploadpic/pass/softhead/400/20160614/20160614183818760585.jpg");
        info.setSingername("陈奕迅");
        info.setSingname("浮夸");
        info.setSingdx("4.0M");
        gxlist.add(info);

        info=new DgGxInfo();
        info.setPlayurl("http:\\/\\/yinyueshiting.baidu.com\\/data2\\/music\\/9549419d16a0bfac9818423eaa149b21\\/264005473\\/264005473.mp3?xcode=4fa8e60b38e41d082a1779a2a81a9c08");
        info.setIma("http://img1.music.response.itmf.cn/uploadpic/pass/softhead/400/20160614/20160614161830591380.jpg");
        info.setSingername("周杰伦");
        info.setSingname("稻香");
        info.setSingdx("3.0M");
        gxlist.add(info);

        info=new DgGxInfo();
        info.setPlayurl("http:\\/\\/yinyueshiting.baidu.com\\/data2\\/music\\/2b6655840b5ec803e04c26ca11bdd009\\/270587896\\/270587896.mp3?xcode=4fa8e60b38e41d0848ae5c20176c0886");
        info.setIma("http://img1.music.response.itmf.cn/uploadpic/pass/softhead/400/20160810/20160810175724522.jpg");
        info.setSingername("薛之谦");
        info.setSingname("丑八怪");
        info.setSingdx("4.0M");
        gxlist.add(info);

        info=new DgGxInfo();
        info.setPlayurl("http:\\/\\/yinyueshiting.baidu.com\\/data2\\/music\\/59eec38ecf8863b4277804e8814d4a51\\/260357989\\/260357989.mp3?xcode=016eed673d7668794394f800f7f2550e");
        info.setIma("http://img1.music.response.itmf.cn/uploadpic/pass/softhead/400/20160407/20160407184433394229.jpg");
        info.setSingername("凤凰传奇");
        info.setSingname("最炫民族风");
        info.setSingdx("4.0M");
        gxlist.add(info);

        info=new DgGxInfo();
        info.setPlayurl("http:\\/\\/yinyueshiting.baidu.com\\/data2\\/music\\/123860036\\/123860036.mp3?xcode=016eed673d7668797ed60eb536e08c87");
        info.setIma("http://img1.music.response.itmf.cn/uploadpic/pass/softhead/400/20160418/20160418100531196.jpg");
        info.setSingername("Beyond");
        info.setSingname("海阔天空");
        info.setSingdx("4.0M");
        gxlist.add(info);

        info=new DgGxInfo();
        info.setPlayurl("http:\\/\\/yinyueshiting.baidu.com\\/data2\\/music\\/8f5c521fe452f90ba9bbef2573114250\\/270273605\\/270273605.mp3?xcode=016eed673d766879bd5cc648288db746");
        info.setIma("http://img1.music.response.itmf.cn/uploadpic/pass/softhead/400/20160425/20160425102353574359.jpg");
        info.setSingername("张杰");
        info.setSingname("逆战");
        info.setSingdx("4.0M");
        gxlist.add(info);

        adapter=new GxAdapter(getActivity(),gxlist);
        list.setAdapter(adapter);


        li.add(R.mipmap.xsy111);
        li.add(R.mipmap.zjl);
        li.add(R.mipmap.aiweier);
        li.add(R.mipmap.cyx);
        li.add(R.mipmap.cyy);


        con.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        },li).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);

    }
    public class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
        }
    }
}
