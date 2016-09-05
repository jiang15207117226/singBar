package plz.com.singbar.view.activity;


import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import plz.com.singbar.R;
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.view.frag.HomeFragment;
import plz.com.singbar.view.frag.MineFragment;

/**
 * Created by Administrator on 2016/8/29.
 */
public class HomeActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, MineFragment.UserInfo {
    private ViewHolder holder;
    private FragmentManager manager;
    private PopupWindow pop;
    private MineFragment mineFragment;
    private HomeFragment homeFragment;
    private UserBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_home, null);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        setContentView(view);
        init(view);
    }

    /**
     * 初始化
     *
     * @param view
     */
    private void init(View view) {
        holder = new ViewHolder();
        holder.bindView(view);
        bean = (UserBean) getIntent().getSerializableExtra("userBean");
        initPop();//初始化pop
        manager = getSupportFragmentManager();
        mineFragment = new MineFragment();
        mineFragment.getUserInfo(this);
        homeFragment = new HomeFragment();
        manager.beginTransaction().add(R.id.fl_home_content, homeFragment).commit();
        //设置底部中间imageview点击监听
        holder.bottomCenter.setOnClickListener(this);
        //设置底部RddioGroup监听
        holder.bottomRg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (i) {
            case R.id.rb_home:
                //首页
                setBottomTextColor(1);//设置文字选中颜色
                transaction.replace(R.id.fl_home_content, homeFragment);
                break;
            case R.id.rb_list:
                //榜单
                setBottomTextColor(2);//设置文字选中颜色
                break;
            case R.id.rb_trends:
                //动态
                setBottomTextColor(3);//设置文字选中颜色
                break;
            case R.id.rb_mine:
                //我的
                setBottomTextColor(4);//设置文字选中颜色
                manager.beginTransaction().remove(homeFragment).commit();
                transaction.replace(R.id.fl_home_content, mineFragment);
                break;
        }
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        pop.showAsDropDown(holder.popUnder);
        holder.isPopShow.setVisibility(View.VISIBLE);
        holder.bottomCenter.setImageResource(R.mipmap.icon_record_pop_show);
        //设置状态栏颜色
        getWindow().setStatusBarColor(getResources().getColor(R.color.pop_song_top));
    }

    /**
     * 更换选中项文字颜色
     *
     * @param position
     */
    private void setBottomTextColor(int position) {
        switch (position) {
            case 1:
                holder.rBtnHome.setTextColor(getResources().getColor(R.color.home_bottom_text_checked_color));
                holder.rBtnList.setTextColor(getResources().getColor(R.color.white));
                holder.rBtnTrends.setTextColor(getResources().getColor(R.color.white));
                holder.rBtnMine.setTextColor(getResources().getColor(R.color.white));
                break;
            case 2:
                holder.rBtnHome.setTextColor(getResources().getColor(R.color.white));
                holder.rBtnList.setTextColor(getResources().getColor(R.color.home_bottom_text_checked_color));
                holder.rBtnTrends.setTextColor(getResources().getColor(R.color.white));
                holder.rBtnMine.setTextColor(getResources().getColor(R.color.white));
                break;
            case 3:
                holder.rBtnHome.setTextColor(getResources().getColor(R.color.white));
                holder.rBtnList.setTextColor(getResources().getColor(R.color.white));
                holder.rBtnTrends.setTextColor(getResources().getColor(R.color.home_bottom_text_checked_color));
                holder.rBtnMine.setTextColor(getResources().getColor(R.color.white));
                break;
            case 4:
                holder.rBtnHome.setTextColor(getResources().getColor(R.color.white));
                holder.rBtnList.setTextColor(getResources().getColor(R.color.white));
                holder.rBtnTrends.setTextColor(getResources().getColor(R.color.white));
                holder.rBtnMine.setTextColor(getResources().getColor(R.color.home_bottom_text_checked_color));
                break;
        }
    }

    private void initPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_song, null);
        WindowManager windowManager = getWindowManager();
        int screenHeight = windowManager.getDefaultDisplay().getHeight() - holder.bottomCenter.getLayoutParams().height - getStatusBarHeight();
        Log.i("result", "screenHeight->" + screenHeight + "====" + holder.bottomCenter.getLayoutParams().height);
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, screenHeight);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setTouchable(true);
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setAnimationStyle(R.style.pop_bottom_style);
        PopViewHolder holder = new PopViewHolder();
        holder.bindPopView(view);
        //设置监听
        holder.songDismiss.setOnClickListener(onPopClick);
        holder.kodBox.setOnClickListener(onPopClick);
        holder.koded.setOnClickListener(onPopClick);
        holder.record.setOnClickListener(onPopClick);
        pop.setOnDismissListener(dismissListener);
    }

    View.OnClickListener onPopClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_pop_song_k:
                    //取消pop
                    pop.dismiss();
                    holder.bottomCenter.setImageResource(R.mipmap.icon_record);
                    //设置状态栏颜色
                    getWindow().setStatusBarColor(getResources().getColor(R.color.home_bottom_rg_bg));
                    break;
                case R.id.tv_pop_item_kodBox:
                    //点歌台
                    break;
                case R.id.tv_pop_item_koded:
                    //已点歌曲
                    break;
                case R.id.tv_pop_item_record:
                    //本地录音

                    break;
            }
        }
    };
    PopupWindow.OnDismissListener dismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            holder.bottomCenter.setImageResource(R.mipmap.icon_record);
            holder.isPopShow.setVisibility(View.GONE);
        }
    };

    @Override
    public UserBean getUserBean() {
        return (UserBean) getIntent().getSerializableExtra("userBean");
    }

    //视图管理类
    private class ViewHolder {
        private RelativeLayout layout_bottom;
        private LinearLayout isPopShow;
        private ImageView bottomCenter;
        private RadioGroup bottomRg;
        private RadioButton rBtnHome;
        private RadioButton rBtnList;
        private RadioButton rBtnTrends;
        private RadioButton rBtnMine;
        private TextView popUnder;

        private void bindView(View view) {
            layout_bottom = (RelativeLayout) view.findViewById(R.id.layout_home_bottom);
            isPopShow = (LinearLayout) view.findViewById(R.id.ll_is_pop_show);
            bottomCenter = (ImageView) view.findViewById(R.id.iv_bottom_center);
            bottomRg = (RadioGroup) layout_bottom.findViewById(R.id.rg_home_bottom);
            rBtnHome = (RadioButton) layout_bottom.findViewById(R.id.rb_home);
            rBtnList = (RadioButton) layout_bottom.findViewById(R.id.rb_list);
            rBtnTrends = (RadioButton) layout_bottom.findViewById(R.id.rb_trends);
            rBtnMine = (RadioButton) layout_bottom.findViewById(R.id.rb_mine);
            popUnder = (TextView) view.findViewById(R.id.tv_pop_under);
        }
    }

    /**
     * pop的视图管理类
     */
    private class PopViewHolder {
        private ImageView songDismiss;
        private TextView kodBox;
        private TextView koded;
        private TextView record;

        private void bindPopView(View view) {
            songDismiss = (ImageView) view.findViewById(R.id.iv_pop_song_k);
            kodBox = (TextView) view.findViewById(R.id.tv_pop_item_kodBox);
            koded = (TextView) view.findViewById(R.id.tv_pop_item_koded);
            record = (TextView) view.findViewById(R.id.tv_pop_item_record);
        }
    }

    public int getStatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
