package plz.com.singbar.view.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.view.adapter.DgGdAdapter;
import plz.com.singbar.view.info.DgGdInfo;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class GdFrag extends Fragment{
    private View view;
    private List<DgGdInfo> list;
    private ListView lv;
    private DgGdInfo info;
    private DgGdAdapter adapter;
    private int width2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_dg_gd,container,false);

        WindowManager manager = getActivity().getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        width2 = outMetrics.widthPixels;
        init();
        build();

        return view;
    }


    private void init() {
        lv= (ListView) view.findViewById(R.id.lv_dg_gd);
        list= new ArrayList<>();
    }

    private void build() {
        info=new DgGdInfo();
        info.setIma(R.mipmap.xingesheng);
        info.setText("中国新歌声 合辑");
        list.add(info);

        info=new DgGdInfo();
        info.setIma(R.mipmap.aiweier);
        info.setText("欧美热歌");
        list.add(info);

        info=new DgGdInfo();
        info.setIma(R.mipmap.cyx);
        info.setText("经典老歌 重新回味");
        list.add(info);

        info=new DgGdInfo();
        info.setIma(R.mipmap.zjl);
        info.setText("史上公认的最好听的中文歌");
        list.add(info);

        info=new DgGdInfo();
        info.setIma(R.mipmap.qh);
        info.setText("听歌，是一种情怀");
        list.add(info);

        adapter=new DgGdAdapter(getActivity(),list,width2);
        lv.setAdapter(adapter);
    }
}
