package plz.com.singbar.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.tablemanager.Connector;

import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.operation.DbOperation;

public class MainActivity extends Activity implements View.OnClickListener {
    private ViewHolder holder;
    private static final String APP_ID = "1105609983";
    private static final String APP_KEY = "e0XfTnrSKnoPeIcS";
    private Tencent tencent;
    private QQAuth qqAuth;
    private String openidString;
    private IUiListener iUiListener;

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login:
                //登陆
                String account = holder.inputAccount.getText().toString();
                String pw = holder.inputPassword.getText().toString();
                if (account.length() < 1 || pw.length() < 1) {
                    Toast.makeText(this, "用户名或密码为空...", Toast.LENGTH_SHORT).show();
                    break;
                }
                List<UserBean> list = DbOperation.query(account);
                if (list == null || list.size() < 1) {
                    Log.i("result", list.size() + "");
                    Toast.makeText(this, "用户名或密码错误...", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    for (UserBean bean : list) {
                        if (bean.getAccount().equals(account)) {
                            if (bean.getPw().equals(pw)) {
                                Toast.makeText(this, "登陆成功!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(this, HomeActivity.class);
                                intent.putExtra("id", bean.getId());
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(this, "密码错误...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
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
                    updateUserInfo();
                    Log.i("result", o + "----------------------");
                    try {
                        openidString = o.getString("openid");
                        Log.e("result", "-------------" + openidString);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            // qqAuth.login(MainActivity.this,"all",iUiListener);
//            tencent.login(MainActivity.this, "all", iUiListener);
            tencent.loginWithOEM(MainActivity.this,"all",iUiListener,"10000144","10000144","xxxx");
        } else {
            qqAuth.logout(MainActivity.this);
        }

    }

    public void updateUserInfo() {
            if (qqAuth != null && qqAuth.isSessionValid()) {
                IUiListener listener = new IUiListener() {
                    @Override
                    public void onError(UiError e) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onComplete(final Object response) {
                        JSONObject json = (JSONObject) response;
                        // 昵称
                        Message msg = new Message();
                        String nickname = null;
                        try {
                            nickname = ((JSONObject) response)
                                    .getString("nickname");
                            Log.i("result",nickname+"--------");
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
//                        msg.getData().putString("nickname", nickname);
//                        msg.what = 0;
//                        mHandler.sendMessage(msg);
                        // 头像
                        String path;
                        try {
                            path = json.getString("figureurl_qq_2");
                            Log.i("result",path+">>path");
//                            MyImgThread imgThread = new MyImgThread(path);
//                            Thread thread = new Thread(imgThread);
//                            thread.start();
                        } catch (JSONException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancel() {
                        // TODO Auto-generated method stub

                    }
                };
                // MainActivity.mTencent.requestAsync(Constants.GRAPH_SIMPLE_USER_INFO,
                // null,
                // Constants.HTTP_GET, requestListener, null);
//                mInfo = new UserInfo(this, mQQAuth.getQQToken());
//                mInfo.getUserInfo(listener);
            } else {
                // mUserInfo.setText("");
                // mUserInfo.setVisibility(android.view.View.GONE);
                // mUserLogo.setVisibility(android.view.View.GONE);
            }
    }

    public class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
            Log.i("result",o+"");
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
