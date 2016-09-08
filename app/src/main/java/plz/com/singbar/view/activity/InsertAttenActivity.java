package plz.com.singbar.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import plz.com.singbar.R;
import plz.com.singbar.bean.AttenBean;
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.operation.DbOperation;
import plz.com.singbar.view.adapter.AttentionAdapter;

/**
 * Created by Administrator on 2016/9/6.
 */
public class InsertAttenActivity extends Activity implements View.OnClickListener {
    public static String action="com.plz.action";
    private ViewHolder holder;
    private List<UserBean> list;
    private List<UserBean> listCopy;
    private AttentionAdapter adapter;
    private int userId;
    private int[] _ids;
    private  Handler handler;
    private boolean isInsertAtten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_insert_atten, null);
        init(view);
        setContentView(view);
    }

    private void init(View view) {
        holder = new ViewHolder();
        holder.bindView(view);
        userId = getIntent().getIntExtra("id", -1);
        _ids = getIntent().getIntArrayExtra("_ids");
        list = DbOperation.getRandomData(userId);
        Log.i("list", list.size() + "");
        adapter = new AttentionAdapter(this, list, _ids);
        adapter.setInsertAtten(true);
        holder.tjLv.setAdapter(adapter);
        holder.query.setOnClickListener(this);
        holder.input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.i("result", holder.input.getText().length() + "--->length");
                if (holder.input.getText() == null || holder.input.getText().length() < 1) {
                    adapter.notifyData(listCopy);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        String input = holder.input.getText().toString();
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(input);
        List<UserBean> list;
        if (m.find()) {
            Log.i("result", "输入的有汉字");
            list = DbOperation.queryByPetName(input);
        } else if (input.substring(0, 2).equals("mb")) {
            list = DbOperation.query(input);
            Log.i("result", "输入的是字母/数字等"+input);
        } else {
            Log.i("result", "输入的是昵称");
            list = DbOperation.queryByPetName(input);
        }
        if (list == null || list.size() < 1) {
            Toast.makeText(this, "搜索失败...请检查输入是否有误", Toast.LENGTH_SHORT).show();
        } else {
            listCopy=this.list;
            this.list=list;
            adapter.notifyData(list);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.start();
    }

    Thread thread =new Thread(){
        @Override
        public void run() {
            super.run();
            int[] positions = adapter.getPositions();
            if (positions==null||positions.length<1){
                return;
            }
            for (int j = 0; j < positions.length; j++) {
                AttenBean attenBean = new AttenBean();
                int id = positions[j];
                if (id!=0){
                    Log.i("aid--", "AttenUserId-->" + id);
                    attenBean.setAttenUserId(id);
                    if (attenBean.save()) {
                        Log.i("aid", attenBean.getId() + "");
                        DbOperation.updateAttenUserId(userId, attenBean.getId());
                    } else {
                        Toast.makeText(InsertAttenActivity.this, "存储异常...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            DbOperation.updateUserAttenCount(positions.length, userId);
            Intent intent=new Intent(action);
            intent.putExtra("isRegister",true);
            sendBroadcast(intent);
        }
    };
    private class ViewHolder {
        private EditText input;
        private TextView query;
        private ListView tjLv;
        private TextView tj;

        private void bindView(View view) {
            input = (EditText) view.findViewById(R.id.et_intsert_sousuo);
            query = (TextView) view.findViewById(R.id.tv_diange_sousuo);
            tjLv = (ListView) view.findViewById(R.id.lv_intsert_tj);
            tj = (TextView) view.findViewById(R.id.tv_insert_tj);
        }
    }
}
