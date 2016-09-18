package plz.com.singbar.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.tablemanager.Connector;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.bean.UserDetailBean;
import plz.com.singbar.operation.DbOperation;
import plz.com.singbar.operation.GenerMbAccount;
import plz.com.singbar.operation.UserIdConfig;


public class MainActivity extends Activity implements View.OnClickListener {
    private ViewHolder holder;
    private static final String APP_ID = "1105609983";
    private static final String APP_KEY = "e0XfTnrSKnoPeIcS";
    private Tencent tencent;
    private QQAuth qqAuth;
    private String openidString;
    private IUiListener iUiListener;
    private String account;
    private String pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        getWindow().setStatusBarColor(getResources().getColor(R.color.navigation_bar_color));
        setContentView(view);
        /**
         * 初始化
         */
        init(view);
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    private void init(View view) {
        /**
         * 绑定视图中用到的控件
         */
        holder = new ViewHolder();
        holder.bindView(view);
        Connector.getDatabase();
        /**
         * 设置点击监听
         */
        setClickListener();
    }
    private void setClickListener() {
        holder.btnLogin.setOnClickListener(this);
        holder.registerAccount.setOnClickListener(this);
        holder.findbackPw.setOnClickListener(this);
    }
    private void loginSucceed(int userId) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("id", userId);
        startActivity(intent);
        finish();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login:
                //登陆
                account = holder.inputAccount.getText().toString();
                pw = holder.inputPassword.getText().toString();
                if (account.length() < 1 || pw.length() < 1) {
                    Toast.makeText(this, "用户名或密码为空...", Toast.LENGTH_SHORT).show();
                    break;
                }
                handler.postDelayed(loginThread, 700);
                break;
            case R.id.tv_login_registerAccount:
                //注册账号
                Intent register = new Intent(this, RegisterActivity.class);
                startActivity(register);
                break;
            case R.id.tv_login_findBackPW:
                //找回密码
                Intent findBack = new Intent(this, FindBackPwActivity.class);
                startActivity(findBack);
                break;
        }
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int id = (int) msg.obj;
            if (msg.what == 0) {
                loginSucceed(id);
            }
        }
    };
    Thread loginThread = new Thread() {
        @Override
        public void run() {
            super.run();
            List<UserBean> list = DbOperation.query(account);
            if (list == null || list.size() < 1) {
                Log.i("result", list.size() + "");
                Toast.makeText(MainActivity.this, "用户名或密码错误...", Toast.LENGTH_SHORT).show();
                return;
            } else {
                for (UserBean bean : list) {
                    if (bean.getAccount().equals(account)) {
                        if (pw!=null&&bean.getPw().equals(pw)) {
                            Toast.makeText(MainActivity.this, "登陆成功!", Toast.LENGTH_SHORT).show();
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = bean.getId();
                            handler.sendMessage(msg);
                            UserIdConfig.id=bean.getId();
                        } else {
                            Toast.makeText(MainActivity.this, "密码错误...", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    };
    public void qqLoginClick(View view) {
        loginQQ();
    }
    public void loginQQ() {
        tencent = Tencent.createInstance(APP_ID, getApplicationContext());
        qqAuth = QQAuth.createInstance(APP_ID, getApplicationContext());
        if (!qqAuth.isSessionValid()) {
            iUiListener = new BaseUiListener() {
                @Override
                protected void doComplete(JSONObject o) {
                    Log.i("result", o + "----------------------");
                    try {
                        openidString = o.getString("openid");
                        String accessToken = o.getString("access_token");
                        String exp = o.getString("expires_in");
                        Log.e("result", "-------------" + openidString);
                        int userId = DbOperation.findByOpenId(openidString);
                        if (userId == -1) {
                            tencent.setOpenId(openidString);
                            tencent.setAccessToken(accessToken, exp);
                            UserInfo info = new UserInfo(MainActivity.this, tencent.getQQToken());
                            info.getUserInfo(new IUiListener() {
                                @Override
                                public void onComplete(Object o) {
                                    Log.i("result", "-----GetUserInfo-->onComplete" + o);
                                    try {
                                        JSONObject json = (JSONObject) o;
                                        String nickName = json.getString("nickname");
                                        String mbAccount = GenerMbAccount.generMbAccount();
                                        final UserBean bean = new UserBean();
                                        bean.setPetName(nickName);
                                        bean.setAccount(mbAccount);
                                        bean.setQqLoginOpenedId(openidString);
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                        String time = format.format(new Date());
                                        bean.setTime(time);
                                        UserDetailBean detailBean=new UserDetailBean();
                                        bean.setUserDetailBean(detailBean);
                                        boolean isSuccess=bean.save();
                                        detailBean.save();
                                        if (isSuccess) {
                                            loginSucceed(bean.getId());
                                            Log.i("result","idiid---"+bean.getId());
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                @Override
                                public void onError(UiError uiError) {
                                }
                                @Override
                                public void onCancel() {
                                }
                            });
                        } else {
                            loginSucceed(userId);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            tencent.login(MainActivity.this, "all", iUiListener);
//            tencent.loginWithOEM(MainActivity.this,"all",iUiListener,"10000144","10000144","xxxx");
        } else {
            qqAuth.logout(MainActivity.this);
        }
    }
    public class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object o) {
            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
            Log.i("result", o + "");
            doComplete((JSONObject) o);
        }
        /**
         * 处理返回的消息
         */
        protected void doComplete(JSONObject o) {
        }
        @Override
        public void onError(UiError uiError) {
        }
        @Override
        public void onCancel() {
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            if (resultCode == -1) {
                tencent.handleLoginData(data, iUiListener);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    long time=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis()-time<2000){
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                System.exit(0);//退出程序
            }else{
                Toast.makeText(this,"再点一次退出程序",Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 控件(视图)管理类
     */
    private class ViewHolder {
        private EditText inputAccount;
        private EditText inputPassword;
        private Button btnLogin;
        private TextView registerAccount;
        private TextView findbackPw;
        private void bindView(View view) {
            inputAccount = (EditText) view.findViewById(R.id.et_login_inputAccount);
            inputPassword = (EditText) view.findViewById(R.id.et_login_inputPW);
            btnLogin = (Button) view.findViewById(R.id.btn_login_login);
            registerAccount = (TextView) view.findViewById(R.id.tv_login_registerAccount);
            findbackPw = (TextView) view.findViewById(R.id.tv_login_findBackPW);
        }
    }
}