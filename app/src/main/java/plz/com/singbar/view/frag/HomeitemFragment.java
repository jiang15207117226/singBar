package plz.com.singbar.view.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.view.adapter.HomeitemAdapter;
import plz.com.singbar.view.info.HomeitemInfo;


/**
 * Created by Administrator on 2016/8/26.
 */
public class HomeitemFragment extends Fragment {
    private View view;
    private ListView lv;
    private List<HomeitemInfo> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_home,container,false);
        init();
        buildadapter();
        return view;
    }

    private void init() {
        lv= (ListView) view.findViewById(R.id.lv_fragment_home);
        list=new ArrayList<>();
        for (int i=0;i<10;i++) {
            HomeitemInfo info = new HomeitemInfo();
            String title = "小编精选";
            String time = "6分钟前";
            String content = "汤灿一直想成为一名军人歌手";
            info.setTitle(title);
            info.setTime(time);
            info.setContent(content);
            list.add(info);
        }

    }
    private void buildadapter() {
        HomeitemAdapter adapter=new HomeitemAdapter(list,getContext());
        lv.setAdapter(adapter);
    }
}
