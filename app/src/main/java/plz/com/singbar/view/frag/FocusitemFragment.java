package plz.com.singbar.view.frag;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.bean.AttenBean;
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.bean.UserOwnSongsBean;
import plz.com.singbar.operation.DbOperation;
import plz.com.singbar.operation.UserIdConfig;
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
                if (adapter!=null){
                    adapter.notifyData(list);
                }
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
        Log.i("result","*******************************+"+UserIdConfig.id+"");
        List<AttenBean>attenList=DbOperation.queryByUserId(UserIdConfig.id);
        if (attenList==null||attenList.size()<1){
            return;
        }
        for (AttenBean bean:attenList){
            int id=bean.getAttenUserId();
            List<UserOwnSongsBean>userSongs=DbOperation.querySongs(id);
            for (int i=0;i<userSongs.size();i++){
                list.add(userSongs.get(i));
            }
            UserBean bean1=DbOperation.queryById(id);
            userList.add(bean1);
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
            adapter = new FocusitemAdapter(list,userList, context);
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
