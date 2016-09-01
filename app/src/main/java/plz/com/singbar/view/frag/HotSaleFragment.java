package plz.com.singbar.view.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.view.adapter.PagerAdapter;

/**
 * Created by Administrator on 2016/8/31.
 */
public class HotSaleFragment extends Fragment {
    private List<Fragment> fragmentList;
    private Homepage homepage;
    private ViewPager vp;
    private Nationwidebar nationwidebar;
    private List<String> tabList;
    private View view;
    private TabLayout tablayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_fragment_home, container, false);

        init();
        build();
        return view;

    }

    public void init() {

        vp = (ViewPager) view.findViewById(R.id.home_vp);
        tablayout = (TabLayout) view.findViewById(R.id.tab_line);

        fragmentList = new ArrayList<>();
        homepage = new Homepage();
        nationwidebar = new Nationwidebar();
        fragmentList.add(homepage);
        fragmentList.add(nationwidebar);


        tabList = new ArrayList<>();
        tabList.add("总榜");
        tabList.add("全国榜");
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.addTab(tablayout.newTab().setText(tabList.get(0)));
        tablayout.addTab(tablayout.newTab().setText(tabList.get(1)));

    }

    public void build() {

        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(), fragmentList, tabList);
        vp.setAdapter(adapter);
        tablayout.setupWithViewPager(vp);
    }

}
