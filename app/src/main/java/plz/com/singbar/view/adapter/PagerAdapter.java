package plz.com.singbar.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> tabList;
    public PagerAdapter(FragmentManager fm,List<Fragment> fragmentList,List<String> tabList) {
        super(fm);
        this.fragmentList=fragmentList;
        this.tabList=tabList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return tabList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position);
    }
}
