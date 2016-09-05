package plz.com.singbar.view.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.bean.UserOwnSongsBean;
import plz.com.singbar.view.activity.ListenActivity;
import plz.com.singbar.view.adapter.FocusitemAdapter;
import plz.com.singbar.view.info.FocusitemInfo;

/**
 * Created by Administrator on 2016/8/31.
 */
public class Homepage extends Fragment {
    private View view;
    private ListView lv;
    private List<UserOwnSongsBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        buildadapter();
        return view;
    }

    private void init() {
        lv = (ListView) view.findViewById(R.id.lv_fragment_home);
        lv.setOnItemClickListener(listener);
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserOwnSongsBean info = new UserOwnSongsBean();
            String name = "secret";
            String singname = "11111`1";
//            String time = "4分钟前";
            int singnum = 521;
            int comment = 22163;
            int flower = 31311;
//            info.setTime(time);
            info.setSongName(singname);
            info.setTrys(singnum);
            info.setComments(comment);
            info.setFlowers(flower);
            list.add(info);
        }
    }

    private void buildadapter() {
        FocusitemAdapter adapter = new FocusitemAdapter(list, getContext());
        lv.setAdapter(adapter);
    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getActivity(), ListenActivity.class);
            FocusitemInfo info = (FocusitemInfo) adapterView.getItemAtPosition(i);
            intent.putExtra("key", info);
            startActivity(intent);

        }
    };

}
