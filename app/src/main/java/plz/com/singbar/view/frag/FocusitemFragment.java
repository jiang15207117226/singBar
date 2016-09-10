package plz.com.singbar.view.frag;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.bean.UserOwnSongsBean;
import plz.com.singbar.view.activity.ListenActivity;
import plz.com.singbar.view.adapter.FocusitemAdapter;


/**
 * Created by Administrator on 2016/8/26.
 */
public class FocusitemFragment extends Fragment implements HomeFragment.SetContext{
    private View view;
    private FocusitemAdapter adapter;
    private ImageView loading;
    private TextView loadingText;
    private com.handmark.pulltorefresh.library.PullToRefreshListView lv;
    private List<UserOwnSongsBean> list;
    private List<UserBean> userList;
    private TextView tv;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return view;
    }

    private void init() {
        lv = (PullToRefreshListView) view.findViewById(R.id.lv_fragment_home);
        loading = (ImageView) view.findViewById(R.id.iv_loading);
        loadingText = (TextView) view.findViewById(R.id.tv_loading);
        tv= (TextView) view.findViewById(R.id.tv_focus);
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        lv.getRefreshableView().setOnItemClickListener(listener);
        lv.setOnRefreshListener(fl);
        handler.postDelayed(thread,1000);

    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0x01){
                getdata();
                buildadapter();
                AnimationDrawable drawable= (AnimationDrawable) loading.getDrawable();
                drawable.stop();
                loading.setVisibility(View.GONE);
                loadingText.setVisibility(View.GONE);
                adapter.notifyData(list);
            }
        }
    };
    Thread thread=new Thread(){
        @Override
        public void run() {
            AnimationDrawable drawable= (AnimationDrawable) loading.getDrawable();
            drawable.start();
            loading.setVisibility(View.VISIBLE);
            loadingText.setVisibility(View.VISIBLE);
            handler.sendEmptyMessage(0x01);
        }
    };
    private void getdata() {
        list = new ArrayList<>();
        userList=new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
            Date date = new Date();
            String string = dateFormat.format(date);
            UserOwnSongsBean info = new UserOwnSongsBean();
            UserBean bean=new UserBean();
            String name = "secret";
            String singname = "我们的歌";
            int singnum = 521213;
            int comment = 22163;
            int flower = 31311;
            info.setSongName(singname+i);
            info.setTime(string);
            info.setTrys(singnum);
            info.setComments(comment);
            info.setFlowers(flower);
            list.add(info);
            bean.setPetName(name+i);
            bean.setFansCount(1200);
            bean.setButility("歌唱新人");
            userList.add(bean);
        }
    }
    @Override
    public void setContext(Context context) {
        this.context=context;
    }

    private void buildadapter() {
        if (list == null || list.size() < 1) {
            tv.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(View.GONE);
            adapter = new FocusitemAdapter(list,userList, getActivity().getBaseContext());
            lv.setAdapter(adapter);
        }
    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getActivity(), ListenActivity.class);
            UserOwnSongsBean info = (UserOwnSongsBean) adapterView.getItemAtPosition(i);
            intent.putExtra("songsBean", info);
            intent.putExtra("userBean",userList.get(i-1));
            startActivity(intent);

        }
    };
    PullToRefreshBase.OnRefreshListener2 fl = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {

        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {

        }
    };
}
