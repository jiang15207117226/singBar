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
import plz.com.singbar.view.activity.ListenActivity;
import plz.com.singbar.view.adapter.NationwidebarAdapter;
import plz.com.singbar.view.info.FocusitemInfo;
import plz.com.singbar.view.info.NationwidebarInfo;


/**
 * Created by Administrator on 2016/8/31.
 */
public class Nationwidebar extends Fragment {
    private View view;
    private ListView lv;
    private List<NationwidebarInfo> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     //   View view = inflater.inflate(R.layout.activity_nationwidebar, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_nationwidebar, container, false);
        init();
        buildadapter();
        return view;
    }

    private void init() {
        lv = (ListView) view.findViewById(R.id.nationwidebar_lv);
        lv.setOnItemClickListener(listener);
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NationwidebarInfo info = new NationwidebarInfo();
            String userName = "许巍";
            String singname = "曾经的你";
            int audition = 136;

            info.setUserName(userName);
            info.setSingname(singname);
            info.setAudition(audition);

            list.add(info);
        }
    }

    private void buildadapter() {
        NationwidebarAdapter adapter = new NationwidebarAdapter(list, getContext());
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
