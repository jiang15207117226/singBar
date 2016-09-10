package plz.com.singbar.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import plz.com.singbar.view.adapter.AttentionAdapter;
import plz.com.singbar.view.adapter.FansAdapter;
import plz.com.singbar.view.adapter.MineOpusAdapter;

/**
 * Created by Administrator on 2016/9/8.
 */
public class HerHomeActivity extends Activity implements RadioGroup.OnCheckedChangeListener ,AdapterView.OnItemClickListener{
    private ViewHolder holder;
    private List<UserOwnSongsBean> list;
    private List<AttenBean> attenList;
    private List<FansBean> fansList;
    private UserBean userBean;
    private int userID = 0;
    private MineOpusAdapter opusAdapter;
    private AttentionAdapter attenAdapter;
    private FansAdapter fansAdapter;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_herhome, null);
        init(view);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        getWindow().setStatusBarColor(getResources().getColor(R.color.pop_song_top));
        setContentView(view);
    }

    /**
     * 初始化
     *
     * @param view
     */
    private void init(View view) {
        holder = new ViewHolder();
        holder.bindView(view);//绑定视图
        list = new ArrayList<>();
        fansList = new ArrayList<>();
        holder.mineRg.setOnCheckedChangeListener(this);

        if (userID == 0) {
            userID = getIntent().getIntExtra("id", -1);
        }
        Log.i("result", userID + "");
        userBean = DbOperation.queryById(userID);
        String headUrl = userBean.getHead();
        holder.topBarTitle.setText(userBean.getPetName()+"的主页");
        if (headUrl != null) {
            Log.i("headUrl", headUrl);
            Picasso.with(this).load("file://" + headUrl).placeholder(R.mipmap.health_guide_woman_selected).resize(100, 100).transform(new CircleTrans()).centerCrop().into(holder.userHead);
        }
        holder.userName.setText(userBean.getPetName());
        holder.listenCount.setText(userBean.getListenCount() + "");
        String sign = userBean.getSign();
        String callName = userBean.getButility();
        if (sign != null && sign.length() >= 1) {
            holder.sign.setText(sign);
        }
        if (callName != null && callName.length() >= 1) {
            holder.callName.setText(callName);
        }
        getCheckItem();
        holder.opusLv.setOnItemClickListener(this);
    }

    private void getCheckItem() {
        int btnId = holder.mineRg.getCheckedRadioButtonId();
        switch (btnId) {
            case R.id.rb_mine_opus:
                //作品
                setOpusAdapter();
                break;
            case R.id.rb_mine_attention:
                //关注
                setAttenAdapter();
                break;
            case R.id.rb_mine_fans:
                //粉丝
                setFansAdapter();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_mine_opus:
                //作品
                setOpusAdapter();
                break;
            case R.id.rb_mine_attention:
                //关注
                setAttenAdapter();
                break;
            case R.id.rb_mine_fans:
                //粉丝
                setFansAdapter();
                break;
        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if (holder.opusLv.getAdapter() == opusAdapter) {
            //作品 item点击事件
            UserOwnSongsBean songsBean = list.get(i);
            Intent intent = new Intent(this, ListenActivity.class);
            intent.putExtra("songsBean", songsBean);
            intent.putExtra("userBean", userBean);
            intent.putExtra("adress", 0x02);
            startActivity(intent);
        }
    }
    private void setOpusAdapter() {
        addListData();
        holder.noProduct.setText("该用户当前还没有任何作品");
        Drawable opus = getResources().getDrawable(R.mipmap.icon_opus, null);
        opus.setBounds(0, 0, opus.getMinimumWidth(), opus.getMinimumHeight());
        holder.noProduct.setCompoundDrawables(null, opus, null, null);
        holder.opusLv.setDividerHeight(10);
        holder.noProduct.setClickable(false);
        holder.noProduct.setFocusable(false);
        if (list == null || list.size() < 1) {
            holder.opusLv.setAdapter(null);
            holder.noProduct.setVisibility(View.VISIBLE);
        } else {
            holder.noProduct.setVisibility(View.GONE);
            if (opusAdapter == null) {
                opusAdapter = new MineOpusAdapter(this, list, userBean);
            }
            holder.opusLv.setAdapter(null);
            holder.opusLv.setAdapter(opusAdapter);
        }

    }

    private void setAttenAdapter() {
        attenList = DbOperation.queryByUserId(userID);
        holder.noProduct.setText("该用户当前还没有关注任何人");
        Drawable attention = getResources().getDrawable(R.mipmap.icon_attention, null);
        attention.setBounds(0, 0, attention.getMinimumWidth(), attention.getMinimumHeight());
        holder.noProduct.setCompoundDrawables(null, attention, null, null);
        holder.opusLv.setDividerHeight(0);
        holder.noProduct.setClickable(true);
        holder.noProduct.setFocusable(true);
        if (attenList == null || attenList.size() < 1) {
            holder.opusLv.setAdapter(null);
            holder.noProduct.setVisibility(View.VISIBLE);
        } else {
            holder.noProduct.setVisibility(View.GONE);
            List<UserBean> userAttenLsit = addAttenData();
            Log.i("size", userAttenLsit.size() + "--size");
            if (attenAdapter == null) {
                attenAdapter = new AttentionAdapter(this, userAttenLsit, new int[0]);
                attenAdapter.setInsertAtten(false);
                holder.opusLv.setAdapter(attenAdapter);
            }
            holder.opusLv.setAdapter(null);
            holder.opusLv.setAdapter(attenAdapter);
        }
    }

    private void setFansAdapter() {
        addFansData();
        holder.opusLv.setAdapter(null);
        holder.noProduct.setText("该用户当前还没有粉丝");
        Drawable fans = getResources().getDrawable(R.mipmap.icon_fans, null);
        fans.setBounds(0, 0, fans.getMinimumWidth(), fans.getMinimumHeight());
        holder.noProduct.setCompoundDrawables(null, fans, null, null);
        holder.noProduct.setClickable(false);
        holder.noProduct.setFocusable(false);
        if (fansList == null || fansList.size() < 1) {
            holder.opusLv.setAdapter(null);
            holder.noProduct.setVisibility(View.VISIBLE);
        } else {
            holder.noProduct.setVisibility(View.GONE);
            if (fansAdapter == null) {
                fansAdapter = new FansAdapter(this, fansList);
                fansAdapter.setIsOthersHome(true);
            }
            holder.opusLv.setAdapter(fansAdapter);
        }
    }

    /**
     * 视图管理类
     */
    private class ViewHolder {
        private TextView topBarTitle;
        private ListView opusLv;
        private TextView noProduct;
        private ImageView userHead;
        private TextView userName;
        private RadioGroup mineRg;
        private TextView sign;
        private TextView callName;
        private TextView listenCount;

        private void bindView(View view) {
            topBarTitle = (TextView) view.findViewById(R.id.tv_top_title);
            opusLv = (ListView) view.findViewById(R.id.lv_mine_opus);
            noProduct = (TextView) view.findViewById(R.id.tv_mine_noProduct);
            userHead = (ImageView) view.findViewById(R.id.iv_mine_user_head);
            userName = (TextView) view.findViewById(R.id.tv_mine_userName);
            mineRg = (RadioGroup) view.findViewById(R.id.rg_mine);
            sign = (TextView) view.findViewById(R.id.tv_mine_design);
            callName = (TextView) view.findViewById(R.id.tv_mine_callName);
            listenCount = (TextView) view.findViewById(R.id.tv_mine_listenCount);
        }
    }

    private void addListData() {
        for (int i = 0; i < 10; i++) {
            UserOwnSongsBean userOwn = new UserOwnSongsBean();
            userOwn.setSongName("死了都要爱" + i);
            userOwn.setComments(100);
            userOwn.setFlowers(321);
            userOwn.setTrys(140);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String time = format.format(new Date());
            userOwn.setTime(time);
            list.add(userOwn);
            userOwn.save();
            DbOperation.updateSongsUserId(userID, DbOperation.querySongsId(userOwn.getSongName()));
        }
    }

    private List<UserBean> addAttenData() {
        List<UserBean> userAttenList = new ArrayList<>();
        for (int i = 0; i < attenList.size(); i++) {
            AttenBean attenBean = attenList.get(i);
            Log.i("result", attenBean.getAttenUserId() + "");
            int id = attenBean.getAttenUserId();
            List<UserBean> list = DbOperation.queryPartById(id);
            UserBean bean = null;
            if (list != null && list.size() >= 1) {
                bean = list.get(0);
                userAttenList.add(bean);
            }
//            Log.i("result","昵称:"+bean.getPetName()+"称号:"+bean.getButility()+"头像:"+bean.getHead());
        }
        Log.i("atten", userAttenList.size() + "----------------");
        return userAttenList;
    }

    private void addFansData() {
        int[] _ids = DbOperation.queryFansByUserId(userID);
        if (_ids == null) {
            return;
        }
        fansList = new ArrayList<>();
        for (int i = 0; i < _ids.length; i++) {
            UserBean userBean = DbOperation.queryPartById(_ids[i]).get(0);
            FansBean bean = new FansBean();
            bean.setHead(userBean.getHead());
            bean.setPetName(userBean.getPetName());
            bean.setCallName(userBean.getButility());
            fansList.add(bean);
        }
    }

}
