package plz.com.singbar.view.frag;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.bean.AttenBean;
import plz.com.singbar.bean.FansBean;
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.bean.UserOwnSongsBean;
import plz.com.singbar.operation.CircleTrans;
import plz.com.singbar.operation.DbOperation;
import plz.com.singbar.view.activity.ListenActivity;
import plz.com.singbar.view.adapter.AttentionAdapter;
import plz.com.singbar.view.adapter.FansAdapter;
import plz.com.singbar.view.adapter.MineOpusAdapter;

/**
 * Created by Administrator on 2016/8/29.
 */
public class MineFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {
    private ViewHolder holder;
    private List<UserOwnSongsBean> list;
    private List<AttenBean> attenList;
    private List<FansBean> fansList;
    private UserInfo info;
    private UserBean userBean;
    private int userID;
    private MineOpusAdapter opusAdapter;
    private AttentionAdapter attenAdapter;
    private FansAdapter fansAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_mine, null);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.pop_song_top));
        init(view);//初始化
        return view;
    }

    /**
     * 初始化
     *
     * @param view
     */
    private void init(View view) {
        holder = new ViewHolder();
        holder.bindView(view);//绑定视图
        holder.topBarTitle.setText(getString(R.string.mine_title));
        holder.mineRg.setOnCheckedChangeListener(this);
        userID = info.getUserID();
        Log.i("result", userID + "");
        userBean = DbOperation.queryById(userID);
        String headUrl = userBean.getHead();
        if (headUrl != null) {
            Picasso.with(getActivity()).load(headUrl).placeholder(R.mipmap.health_guide_woman_selected).resize(100, 100).transform(new CircleTrans()).centerCrop().into(holder.userHead);
        }
        holder.userName.setText(userBean.getPetName());
        list = new ArrayList<>();
        addListData();
        if (list == null || list.size() < 1) {
            holder.noProduct.setVisibility(View.VISIBLE);
        } else {
            holder.noProduct.setVisibility(View.GONE);
            opusAdapter = new MineOpusAdapter(getActivity(), list, userBean);
            holder.opusLv.setAdapter(opusAdapter);
        }
        holder.opusLv.setOnItemClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_mine_opus:
                //作品
                holder.noProduct.setText("您当前还没有任何作品");
                Drawable opus = getResources().getDrawable(R.mipmap.icon_opus, null);
                opus.setBounds(0, 0, opus.getMinimumWidth(), opus.getMinimumHeight());
                holder.noProduct.setCompoundDrawables(null, opus, null, null);
                holder.opusLv.setDividerHeight(10);
                holder.opusLv.setAdapter(null);
                holder.opusLv.setAdapter(opusAdapter);
                break;
            case R.id.rb_mine_attention:
                //关注
                holder.noProduct.setText("您当前还没有关注任何人");
                Drawable attention = getResources().getDrawable(R.mipmap.icon_attention, null);
                attention.setBounds(0, 0, attention.getMinimumWidth(), attention.getMinimumHeight());
                holder.noProduct.setCompoundDrawables(null, attention, null, null);
                holder.opusLv.setDividerHeight(0);
                if (attenAdapter == null) {
                    addAttenData();
                    attenAdapter = new AttentionAdapter(getActivity(), attenList);
                }
                holder.opusLv.setAdapter(null);
                holder.opusLv.setAdapter(attenAdapter);
                break;
            case R.id.rb_mine_fans:
                //粉丝
                holder.noProduct.setText("您当前还没有粉丝");
                Drawable fans = getResources().getDrawable(R.mipmap.icon_fans, null);
                fans.setBounds(0, 0, fans.getMinimumWidth(), fans.getMinimumHeight());
                holder.noProduct.setCompoundDrawables(null, fans, null, null);
                if (fansAdapter == null) {
                    addFansData();
                    fansAdapter = new FansAdapter(getActivity(), fansList);
                }
                holder.opusLv.setAdapter(null);
                holder.opusLv.setAdapter(fansAdapter);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if (holder.opusLv.getAdapter() == opusAdapter) {
            //作品 item点击事件
            UserOwnSongsBean songsBean = list.get(i);
            Intent intent = new Intent(getActivity(), ListenActivity.class);
            intent.putExtra("songsBean", songsBean);
            intent.putExtra("userBean", userBean);
            intent.putExtra("adress", 0x02);
            startActivity(intent);
        }
    }


    /**
     * 视图管理类
     */
    private class ViewHolder {
        private LinearLayout topBar;
        private ImageView topBtnLeft;
        private ImageView topBtnRight;
        private TextView topBarTitle;
        private ListView opusLv;
        private TextView noProduct;
        private ImageView userHead;
        private TextView userName;
        private RadioGroup mineRg;

        private void bindView(View view) {
            topBar = (LinearLayout) view.findViewById(R.id.layout_top_navgationBar);
            topBtnLeft = (ImageView) view.findViewById(R.id.iv_top_btn_left);
            topBtnRight = (ImageView) view.findViewById(R.id.iv_top_btn_right);
            topBarTitle = (TextView) view.findViewById(R.id.tv_top_title);
            opusLv = (ListView) view.findViewById(R.id.lv_mine_opus);
            noProduct = (TextView) view.findViewById(R.id.tv_mine_noProduct);
            userHead = (ImageView) view.findViewById(R.id.iv_mine_user_head);
            userName = (TextView) view.findViewById(R.id.tv_mine_userName);
            mineRg = (RadioGroup) view.findViewById(R.id.rg_mine);
        }
    }

    private void addListData() {
        for (int i = 0; i < 10; i++) {
            UserOwnSongsBean userOwn = new UserOwnSongsBean();
            userOwn.setSongName("死了都要爱");
            userOwn.setComments(100);
            userOwn.setFlowers(321);
            userOwn.setTrys(140);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String time = format.format(new Date());
            userOwn.setTime(time);
            list.add(userOwn);
        }
    }

    private void addAttenData() {
        attenList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AttenBean bean = new AttenBean();
            bean.setHead("http://mg.soupingguo.com/bizhi/big/10/133/531/10133531.jpg");
            bean.setPetName("plz");
            bean.setCallName("歌唱达人");
            if (i % 3 == 0) {
                bean.setStadus(true);
            } else {
                bean.setStadus(false);
            }
            attenList.add(bean);
        }
    }

    private void addFansData() {
        fansList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FansBean bean = new FansBean();
            bean.setHead("http://mg.soupingguo.com/bizhi/big/10/133/531/10133531.jpg");
            bean.setPetName("plz");
            bean.setCallName("歌唱达人");
            fansList.add(bean);
        }
    }

    public void getUserInfo(UserInfo info) {
        this.info = info;
    }

    public interface UserInfo {
        int getUserID();
    }
}
