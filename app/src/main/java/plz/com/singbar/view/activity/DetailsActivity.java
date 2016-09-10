package plz.com.singbar.view.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.bean.UserDetailBean;
import plz.com.singbar.operation.CircleTrans;
import plz.com.singbar.operation.DbOperation;

/**
 * Created by Administrator on 2016/8/26 0026.
 */
public class DetailsActivity extends Activity {
    private ImageView iv;
    private TextView bri;
    private View popview;
    private TextView m;
    private TextView constellation;
    private PickerView one;
    private PickerView two;
    private PickerView onlyone;
    private View ll;
    private PopupWindow popupWindow;
    private PopupWindow popupWindowo;
    private View xz;
    private Drawable dr;
    private TextView nickName;
    private TextView old;
    private TextView zhuangtai;
    private TextView tall;
    private TextView profession;
    private TextView xueli;
    private TextView qianm;
    private TextView account;
    private TextView phone;
    private EditText et;
    private View etview;
    private EditText eto;
    private View viewone;
    private TextView jiaxiang;
    private View viewtwo;
    private TextView newhome;
    private EditText etnow;
    private UserBean bean;
    private UserDetailBean userDetailBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
        build();
    }


    private void init() {
        bean = (UserBean) getIntent().getSerializableExtra("user");
        userDetailBean = DbOperation.queryDetail(bean.getId());
        etview = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.item_pop_ido, null);
        viewone = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.item_pop_home, null);
        viewtwo = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.item_pop_nowhome, null);
        eto = (EditText) viewone.findViewById(R.id.et_pop_home);
        et = (EditText) etview.findViewById(R.id.et_pop_one);
        etnow = (EditText) viewtwo.findViewById(R.id.et_pop_nowhome);

        newhome = (TextView) findViewById(R.id.tv_details_nowhome);
        jiaxiang = (TextView) findViewById(R.id.tv_details_home);
        qianm = (TextView) findViewById(R.id.tv_details_qianming);
        xueli = (TextView) findViewById(R.id.tv_details_xueli);
        account = (TextView) findViewById(R.id.tv_account);
        phone = (TextView) findViewById(R.id.tv_phone);
        profession = (TextView) findViewById(R.id.tv_details_profession);
        tall = (TextView) findViewById(R.id.tv_details_tall);
        zhuangtai = (TextView) findViewById(R.id.tv_details_zhuangtai);
        nickName = (TextView) findViewById(R.id.tv_details_nickName);
        old = (TextView) findViewById(R.id.tv_details_old);
        iv = (ImageView) findViewById(R.id.iv_datails_head);
        bri = (TextView) findViewById(R.id.tv_details_brithday);
        constellation = (TextView) findViewById(R.id.tv_details_constellation);
        m = (TextView) findViewById(R.id.tv_details_m);
        ll = findViewById(R.id.ll_one);
        popview = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.item_pop_birthday, null);//生日


        popupWindow = new PopupWindow(popview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dr = new ColorDrawable(getResources().getColor(R.color.white));
        //月
        one = (PickerView) popview.findViewById(R.id.pic_one);
        //日
        two = (PickerView) popview.findViewById(R.id.pic_two);

        account.setText(bean.getAccount());
        phone.setText(bean.getPhone() + "");
        initView();
    }

    private void initView() {
        Log.i("result", "initView");
        if (bean.getPetName() != null && bean.getPetName().length() >= 1) {
            nickName.setText(bean.getPetName());        //设置昵称
        }
        if (userDetailBean.getAge() != 0) {             //设置年龄
            old.setText(userDetailBean.getAge() + "");
        }
        if (userDetailBean.getConstellation() != null && userDetailBean.getConstellation().length() >= 1) {
            constellation.setText(userDetailBean.getConstellation()); //设置星座
        }
        if (userDetailBean.getBirthday() != null && !userDetailBean.getBirthday().equals("null-null") && userDetailBean.getBirthday().length() >= 1) {
            bri.setText(userDetailBean.getBirthday());      //设置生日
            Log.i("result", userDetailBean.getBirthday() + "-->getBirthday");
        }
        if (userDetailBean.getStadius() != null && userDetailBean.getStadius().length() >= 1) {
            zhuangtai.setText(userDetailBean.getStadius()); //设置情感状态
        }
        if (userDetailBean.getHeight() != 0) {
            tall.setText(userDetailBean.getHeight() + "");  //设置身高
        }
        if (userDetailBean.getProfression() != null && userDetailBean.getProfression().length() >= 1) {
            profession.setText(userDetailBean.getProfression());//设置职业
        }
        if (userDetailBean.getDegree() != null && userDetailBean.getDegree().length() >= 1) {
            xueli.setText(userDetailBean.getDegree());  //设置学历
        }
        if (userDetailBean.getHomeCountry() != null && userDetailBean.getHomeCountry().length() >= 1) {
            jiaxiang.setText(userDetailBean.getHomeCountry());//设置家乡
        }
        if (userDetailBean.getNowLiving() != null && userDetailBean.getNowLiving().length() >= 1) {
            newhome.setText(userDetailBean.getNowLiving()); //设置现居地
        }
        Picasso.with(this).load("file://" + bean.getHead()).placeholder(R.mipmap.health_guide_woman_selected).resize(50, 50).transform(new CircleTrans()).centerCrop().into(iv);
        if (bean.getSign() != null && bean.getSign().length() >= 1) {
            qianm.setText(bean.getSign());
        }
    }

    private void build() {
        //给月 日设置数据及监听
        List<String> dataone = new ArrayList<String>();
        List<String> datatwo = new ArrayList<String>();
        for (int i = 1; i < 13; i++) {
            dataone.add(i + "");
        }
        for (int k = 1; k < 32; k++) {
            datatwo.add(k + "");
        }
        one.setData(dataone);
        two.setData(datatwo);
        one.setOnSelectListener(l);
        two.setOnSelectListener(li);
    }

    PickerView.onSelectListener l = new PickerView.onSelectListener() {

        @Override
        public void onSelect(String text) {
            m.setText(text + "月");
            Log.i("result", text + "-->生日，月");
        }
    };
    PickerView.onSelectListener li = new PickerView.onSelectListener() {

        @Override
        public void onSelect(String text) {
            bri.setText(text + "日");
            Log.i("result", text + "-->生日，日");
        }
    };
    //年龄
    PickerView.onSelectListener lil = new PickerView.onSelectListener() {

        @Override
        public void onSelect(String text) {
            old.setText(text + "岁");
            Log.i("result", text + "-->年龄");
            userDetailBean.setAge(Integer.parseInt(text));
        }
    };
    //星座
    PickerView.onSelectListener baiyang = new PickerView.onSelectListener() {

        @Override
        public void onSelect(String text) {
            constellation.setText(text);
            Log.i("result", text + "-->星座");
            userDetailBean.setConstellation(text);
        }
    };
    //情感状态
    PickerView.onSelectListener lili = new PickerView.onSelectListener() {

        @Override
        public void onSelect(String text) {
            zhuangtai.setText(text);
            Log.i("result", text + "-->情感状态");
            userDetailBean.setStadius(text);
        }
    };
    //身高
    PickerView.onSelectListener lilil = new PickerView.onSelectListener() {

        @Override
        public void onSelect(String text) {
            tall.setText(text + "cm");
            Log.i("result", text + "-->身高");
            userDetailBean.setHeight(Integer.parseInt(text));
        }
    };
    //职业
    PickerView.onSelectListener zhiye = new PickerView.onSelectListener() {

        @Override
        public void onSelect(String text) {
            profession.setText(text);
            Log.i("result", text + "-->职业");
            userDetailBean.setProfression(text);
        }
    };
    //学历
    PickerView.onSelectListener xue = new PickerView.onSelectListener() {
        @Override
        public void onSelect(String text) {
            xueli.setText(text);
            Log.i("result", text + "-->学历");
            userDetailBean.setDegree(text);
        }
    };

    public void click(View v) {
        switch (v.getId()) {
            case R.id.iv_details_head://设置头像
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra("crop", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, 2);
                break;
            case R.id.tv_details_brithday://设置生日
                popupWindow.setBackgroundDrawable(dr);
                popupWindow.setTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(ll, Gravity.CENTER, 0, 0);
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.7f;
                getWindow().setAttributes(lp);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1f;
                        getWindow().setAttributes(lp);
                    }
                });

                break;
            case R.id.tv_details_constellation://设置星座
                xz = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.item_onepicker, null);
                //设置星座及监听
                onlyone = (PickerView) xz.findViewById(R.id.pic_one_one);
                List<String> xingzuo = new ArrayList<String>();
                xingzuo.add("白羊座");
                xingzuo.add("金牛座");
                xingzuo.add("双子座");
                xingzuo.add("巨蟹座");
                xingzuo.add("狮子座");
                xingzuo.add("处女座");
                xingzuo.add("天秤座");
                xingzuo.add("天蝎座");
                xingzuo.add("射手座");
                xingzuo.add("摩羯座");
                xingzuo.add("水瓶座");
                xingzuo.add("双鱼座");
                onlyone.setData(xingzuo);
                onlyone.setOnSelectListener(baiyang);

                popupWindowo = new PopupWindow(xz, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindowo.setFocusable(true);
                popupWindowo.setTouchable(true);
                popupWindowo.setBackgroundDrawable(dr);
                popupWindowo.showAtLocation(ll, Gravity.CENTER, 0, 0);
                popupWindowo.setFocusable(true);
                popupWindowo.setTouchable(true);
                popupWindowo.setBackgroundDrawable(dr);
                popupWindowo.showAtLocation(ll, Gravity.CENTER, 0, 0);
                // 设置背景颜色变暗
                WindowManager.LayoutParams lpo = getWindow().getAttributes();
                lpo.alpha = 0.7f;
                getWindow().setAttributes(lpo);
                popupWindowo.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lpo = getWindow().getAttributes();
                        lpo.alpha = 1f;
                        getWindow().setAttributes(lpo);
                    }
                });
                break;
            case R.id.tv_details_nickName:
                et.setHint("设置昵称");
                popupWindowo = new PopupWindow(etview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindowo.setFocusable(true);
                popupWindowo.setTouchable(true);
                popupWindowo.setBackgroundDrawable(dr);
                popupWindowo.showAtLocation(ll, Gravity.CENTER, 0, 0);
                // 设置背景颜色变暗
                WindowManager.LayoutParams lmlms = getWindow().getAttributes();
                lmlms.alpha = 0.7f;
                getWindow().setAttributes(lmlms);
                popupWindowo.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lmlm = getWindow().getAttributes();
                        lmlm.alpha = 1f;
                        getWindow().setAttributes(lmlm);
                    }
                });

                break;
            case R.id.tv_details_old:
                xz = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.item_onepicker, null);
                onlyone = (PickerView) xz.findViewById(R.id.pic_one_one);
                List<String> ppp = new ArrayList<String>();
                for (int u = 1; u < 100; u++) {
                    ppp.add(u + "");
                }
                onlyone.setData(ppp);
                onlyone.setOnSelectListener(lil);

                popupWindowo = new PopupWindow(xz, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindowo.setFocusable(true);
                popupWindowo.setTouchable(true);
                popupWindowo.setBackgroundDrawable(dr);
                popupWindowo.showAtLocation(ll, Gravity.CENTER, 0, 0);
                // 设置背景颜色变暗
                WindowManager.LayoutParams lpoo = getWindow().getAttributes();
                lpoo.alpha = 0.7f;
                getWindow().setAttributes(lpoo);
                popupWindowo.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lpoo = getWindow().getAttributes();
                        lpoo.alpha = 1f;
                        getWindow().setAttributes(lpoo);
                    }
                });
                break;
            case R.id.tv_details_zhuangtai:
                xz = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.item_onepicker, null);
                onlyone = (PickerView) xz.findViewById(R.id.pic_one_one);
                List<String> pppp = new ArrayList<String>();
                pppp.add("已婚");
                pppp.add("未婚");
                pppp.add("保密");
                onlyone.setData(pppp);
                onlyone.setOnSelectListener(lili);
                popupWindowo = new PopupWindow(xz, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindowo.setFocusable(true);
                popupWindowo.setTouchable(true);
                popupWindowo.setBackgroundDrawable(dr);
                popupWindowo.showAtLocation(ll, Gravity.CENTER, 0, 0);
                // 设置背景颜色变暗
                WindowManager.LayoutParams lpooo = getWindow().getAttributes();
                lpooo.alpha = 0.7f;
                getWindow().setAttributes(lpooo);
                popupWindowo.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lpooo = getWindow().getAttributes();
                        lpooo.alpha = 1f;
                        getWindow().setAttributes(lpooo);
                    }
                });
                break;
            case R.id.tv_details_tall:
                xz = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.item_onepicker, null);
                onlyone = (PickerView) xz.findViewById(R.id.pic_one_one);
                List<String> ppo = new ArrayList<String>();
                for (int m = 155; m < 200; m++) {
                    ppo.add(m + "");
                }
                onlyone.setData(ppo);
                onlyone.setOnSelectListener(lilil);
                popupWindowo = new PopupWindow(xz, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindowo.setFocusable(true);
                popupWindowo.setTouchable(true);
                popupWindowo.setBackgroundDrawable(dr);
                popupWindowo.showAtLocation(ll, Gravity.CENTER, 0, 0);
                // 设置背景颜色变暗
                WindowManager.LayoutParams lm = getWindow().getAttributes();
                lm.alpha = 0.7f;
                getWindow().setAttributes(lm);
                popupWindowo.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lm = getWindow().getAttributes();
                        lm.alpha = 1f;
                        getWindow().setAttributes(lm);
                    }
                });
                break;
            case R.id.tv_details_profession:
                xz = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.item_onepicker, null);
                onlyone = (PickerView) xz.findViewById(R.id.pic_one_one);
                List<String> ppoo = new ArrayList<String>();
                ppoo.add("学生");
                ppoo.add("行政");
                ppoo.add("教师");
                ppoo.add("司机");
                ppoo.add("保密");
                onlyone.setData(ppoo);
                onlyone.setOnSelectListener(zhiye);

                popupWindowo = new PopupWindow(xz, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindowo.setFocusable(true);
                popupWindowo.setTouchable(true);
                popupWindowo.setBackgroundDrawable(dr);
                popupWindowo.showAtLocation(ll, Gravity.CENTER, 0, 0);
                // 设置背景颜色变暗
                WindowManager.LayoutParams lmm = getWindow().getAttributes();
                lmm.alpha = 0.7f;
                getWindow().setAttributes(lmm);
                popupWindowo.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lmm = getWindow().getAttributes();
                        lmm.alpha = 1f;
                        getWindow().setAttributes(lmm);
                    }
                });
                break;
            case R.id.tv_details_xueli:
                xz = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.item_onepicker, null);
                onlyone = (PickerView) xz.findViewById(R.id.pic_one_one);
                List<String> list = new ArrayList<String>();
                list.add("小学");
                list.add("初中");
                list.add("高中");
                list.add("中专");
                list.add("大专");
                list.add("大学");
                onlyone.setData(list);
                onlyone.setOnSelectListener(xue);

                popupWindowo = new PopupWindow(xz, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindowo.setFocusable(true);
                popupWindowo.setTouchable(true);
                popupWindowo.setBackgroundDrawable(dr);
                popupWindowo.showAtLocation(ll, Gravity.CENTER, 0, 0);
                // 设置背景颜色变暗
                WindowManager.LayoutParams lml = getWindow().getAttributes();
                lml.alpha = 0.7f;
                getWindow().setAttributes(lml);
                popupWindowo.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lml = getWindow().getAttributes();
                        lml.alpha = 1f;
                        getWindow().setAttributes(lml);
                    }
                });
                break;
            case R.id.tv_details_qianming:
                et.setHint("设置个性签名");
                popupWindowo = new PopupWindow(etview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindowo.setFocusable(true);
                popupWindowo.setTouchable(true);
                popupWindowo.setBackgroundDrawable(dr);
                popupWindowo.showAtLocation(ll, Gravity.CENTER, 0, 0);
                // 设置背景颜色变暗
                WindowManager.LayoutParams lmlm = getWindow().getAttributes();
                lmlm.alpha = 0.7f;
                getWindow().setAttributes(lmlm);
                popupWindowo.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lmlm = getWindow().getAttributes();
                        lmlm.alpha = 1f;
                        getWindow().setAttributes(lmlm);
                    }
                });
                break;
            case R.id.tv_details_home:
                popupWindowo = new PopupWindow(viewone, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindowo.setFocusable(true);
                popupWindowo.setTouchable(true);
                popupWindowo.setBackgroundDrawable(dr);
                popupWindowo.showAtLocation(ll, Gravity.CENTER, 0, 0);
                // 设置背景颜色变暗
                WindowManager.LayoutParams lmt = getWindow().getAttributes();
                lmt.alpha = 0.7f;
                getWindow().setAttributes(lmt);
                popupWindowo.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lmt = getWindow().getAttributes();
                        lmt.alpha = 1f;
                        getWindow().setAttributes(lmt);
                    }
                });

                break;
            case R.id.tv_details_nowhome:
                popupWindowo = new PopupWindow(viewtwo, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindowo.setFocusable(true);
                popupWindowo.setTouchable(true);
                popupWindowo.setBackgroundDrawable(dr);
                popupWindowo.showAtLocation(ll, Gravity.CENTER, 0, 0);
                // 设置背景颜色变暗
                WindowManager.LayoutParams lmto = getWindow().getAttributes();
                lmto.alpha = 0.7f;
                getWindow().setAttributes(lmto);
                popupWindowo.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lmto = getWindow().getAttributes();
                        lmto.alpha = 1f;
                        getWindow().setAttributes(lmto);
                    }
                });
                break;
        }
    }

    public void cclick(View v) {
        switch (v.getId()) {
            case R.id.btn_pop_no:
                popupWindowo.dismiss();
                break;
            case R.id.btn_pop_yes:
                if (et.getHint().equals("设置个性签名")) {
                    if (et.getText() == null || et.getText().toString().length() < 1) {
                        qianm.setText("点击设置");
                    } else {
                        String sign = et.getText().toString();
                        qianm.setText(sign);//签名
                        bean.setSign(sign);
                        popupWindowo.dismiss();
                        Log.i("result", sign + "-->签名");
                    }
                } else if (et.getHint().equals("设置昵称")) {
                    if (et.getText() != null && et.getText().toString().length() >= 1) {
                        String nickNames = et.getText().toString();
                        nickName.setText(nickNames);//签名
                        bean.setPetName(nickNames);
                        popupWindowo.dismiss();
                        Log.i("result", nickNames + "-->昵称");
                    }
                }
                break;
            case R.id.btn_pop_nohome:
                popupWindowo.dismiss();
                break;
            case R.id.btn_pop_yeshome:
                if (eto.getText() == null || eto.getText().toString().length() < 1) {
                    jiaxiang.setText("点击设置");
                } else {
                    String homeCountry = eto.getText().toString();
                    jiaxiang.setText(homeCountry);//家乡
                    popupWindowo.dismiss();
                    userDetailBean.setHomeCountry(homeCountry);
                    Log.i("result", homeCountry + "-->家乡");
                }
                break;
            case R.id.btn_pop_nonowhome:
                popupWindowo.dismiss();
                break;
            case R.id.btn_pop_yesnewhome:
                if (etnow.getText() == null || etnow.getText().toString().length() < 1) {
                    newhome.setText("点击设置");
                } else {
                    String nowLiving = etnow.getText().toString();
                    newhome.setText(nowLiving);//现居地
                    popupWindowo.dismiss();
                    userDetailBean.setNowLiving(nowLiving);
                    Log.i("result", nowLiving + "-->現居地");
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            System.out.println("requestCode" + requestCode);
            if (requestCode == 2) {
                Uri uri = data.getData();//得到返回值并设置头像
                Log.i("uri", uri + "-uri");
                String path = getFilePath(uri, getContentResolver());
                Picasso.with(DetailsActivity.this).load(uri).resize(50, 50).centerCrop().transform(new CircleTransform()).into(iv);
                bean.setHead(path);
                Log.i("uri", path + "-uriPath");
            }
        }
    }

    private String getFilePath(Uri uri, ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        Cursor cursor = contentResolver.query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("result", "onDestroy");
        String month = m.getText().toString();
        String day = bri.getText().toString();
        if (month != null && month.length() > 0 && day != null && day.length() > 0) {
            userDetailBean.setBirthday(month + day);
        }
        bean.setUserDetailBean(userDetailBean);
        bean.update(bean.getId());
        userDetailBean.update(userDetailBean.getId());
    }
}
