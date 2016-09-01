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
 * Created by Administrator on 2016/8/30.
 */
public class HomeFragment extends Fragment{
    private TabLayout tablayout;
    private ViewPager vp;
    private List<Fragment> fragmentList;
    private List<String> tabList;
    private HomeitemFragment homeFragment;
    private FocusitemFragment focusFragment;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_fragment_home,container,false);
        init();
        buildadapter();
        return view;
    }

    private void init() {
        tablayout= (TabLayout)view.findViewById(R.id.tab_line);
        vp= (ViewPager) view.findViewById(R.id.home_vp);
        fragmentList=new ArrayList<>();
        homeFragment=new HomeitemFragment();
        focusFragment=new FocusitemFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(focusFragment);

        tabList=new ArrayList<>();
        tabList.add("首页");
        tabList.add("关注");
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.addTab(tablayout.newTab().setText(tabList.get(0)));
        tablayout.addTab(tablayout.newTab().setText(tabList.get(1)));


    }
    private void buildadapter() {
        PagerAdapter adapter=new PagerAdapter(getChildFragmentManager(),fragmentList,tabList);
        vp.setAdapter(adapter);
        tablayout.setupWithViewPager(vp);
    }
}
