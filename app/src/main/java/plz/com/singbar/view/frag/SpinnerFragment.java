package plz.com.singbar.view.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import plz.com.singbar.R;

/**
 * Created by Administrator on 2016/9/2.
 */
public class SpinnerFragment extends Fragment {
    private Spinner sp;
    List<String> list;
    ArrayAdapter adapter;
    private String[] str;
    private View view;
    private TextView tv;
    private ArrayAdapter adapter2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bangdan_homepage, container, false);
        sp = (Spinner) view.findViewById(R.id.sping);
        tv = (TextView) view.findViewById(R.id.spinner_tv);
        list = new ArrayList<String>();
        list.add("Beijing");
        list.add("Xiaozhuang");

        adapter = new ArrayAdapter(getActivity(), R.layout.itme_homepage, R.id.homepage_list, list);
        sp = (Spinner) view.findViewById(R.id.sping);           //得到Spinner的对象
        sp.setAdapter(adapter);                              //添加适配器
        sp.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) new SpinnerListener()); //绑定监听器
        sp.setPrompt("地点");                                  //设置选项列表的title
        return view;
    }


    class SpinnerListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            String selected = arg0.getItemAtPosition(arg2).toString();
            Toast.makeText(getActivity(), "what you selected is :" + selected, Toast.LENGTH_LONG).show();
//            Log.d("test", "what you selected is :" + selected);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
            Toast.makeText(getActivity(), "you have selected nothing", Toast.LENGTH_LONG).show();
//            Log.d("test", "you have selected nothing");
        }

    }

}