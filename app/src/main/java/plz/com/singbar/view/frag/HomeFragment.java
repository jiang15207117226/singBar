package plz.com.singbar.view.frag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.view.adapter.PagerAdapter;

/**
 * Created by Administrator on 2016/8/30.
 */
public class HomeFragment extends Fragment {
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
        view = inflater.inflate(R.layout.activity_fragment_home, container, false);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.home_bottom_rg_bg));
        init();
        buildadapter();
        return view;
    }

    private void init() {
        tablayout = (TabLayout) view.findViewById(R.id.tab_line);
        vp = (ViewPager) view.findViewById(R.id.home_vp);
        fragmentList = new ArrayList<>();
        homeFragment = new HomeitemFragment();
        homeFragment.setContext(getContext());
        focusFragment = new FocusitemFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(focusFragment);
        tabList = new ArrayList<>();
        tabList.add("首页");
        tabList.add("关注");
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.addTab(tablayout.newTab().setText(tabList.get(0)));
        tablayout.addTab(tablayout.newTab().setText(tabList.get(1)));


    }

    private void buildadapter() {
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(), fragmentList, tabList);
        vp.setAdapter(adapter);
        tablayout.setupWithViewPager(vp);
    }
    public interface SetContext{
        void setContext(Context context);
    }
}
