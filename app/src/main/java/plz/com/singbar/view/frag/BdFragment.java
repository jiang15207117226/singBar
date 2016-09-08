package plz.com.singbar.view.frag;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.view.adapter.BdAdapter;
import plz.com.singbar.view.info.BdSingInfo;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class BdFragment extends Fragment{
    private View view;
    private ListView lv;
    private BdSingInfo info;
    private List<BdSingInfo> list;
    private BdAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_bedi,container,false);
        init();
        build();
        return view;
    }


    private void init() {
        lv= (ListView) view.findViewById(R.id.lv_dg_bd);
        list=new ArrayList<>();
    }

    private void build() {

        Cursor cursor = getActivity().getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            info=new BdSingInfo();
            info.setName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
            info.setSname(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
            info.setDx(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));
            info.setUri(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
            list.add(info);
        }
        adapter=new BdAdapter(getActivity(),list);
        lv.setAdapter(adapter);
    }
}
