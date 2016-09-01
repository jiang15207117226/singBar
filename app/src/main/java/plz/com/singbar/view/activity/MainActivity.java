package plz.com.singbar.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.tablemanager.Connector;

import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.operation.DbOperation;

public class MainActivity extends Activity implements View.OnClickListener {
    private   ViewHolder holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view=LayoutInflater.from(this).inflate(R.layout.activity_main,null);
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
    private void setClickListener(){
        holder.btnLogin.setOnClickListener(this);
        holder.registerAccount.setOnClickListener(this);
        holder.findbackPw.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login_login:
                //登陆
//                String account=holder.inputAccount.getText().toString();
//                String pw=holder.inputPassword.getText().toString();
//                if (account.length()<1||pw.length()<1){
//                    Toast.makeText(this,"用户名或密码为空...",Toast.LENGTH_SHORT).show();
//                    break;
//                }
//                List<UserBean> list= DbOperation.query(account);
//                if (list==null||list.size()<1){
//                    Log.i("result",list.size()+"");
//                    Toast.makeText(this,"用户名或密码错误...",Toast.LENGTH_SHORT).show();
//                    break;
//                }else{
//                    for (UserBean bean:list){
//                       if (bean.getAccount().equals(account)){
//                           if (bean.getPw().equals(pw)){
//                               Toast.makeText(this,"登陆成功!",Toast.LENGTH_SHORT).show();
                               Intent intent=new Intent(this,HomeActivity.class);
                               startActivity(intent);
//                           }else{
//                               Toast.makeText(this,"密码错误...",Toast.LENGTH_SHORT).show();
//                           }
//                       }
//                    }
//                }
                break;
            case R.id.tv_login_registerAccount:
                //注册账号
                Intent register=new Intent(this,RegisterActivity.class);
                startActivity(register);
            break;
            case R.id.tv_login_findBackPW:
                //找回密码
                Intent findBack=new Intent(this,FindBackPwActivity.class);
                startActivity(findBack);
            break;

        }
    }

    /**
     * 控件(视图)管理类
     */
    private class ViewHolder{
        private EditText inputAccount;
        private EditText inputPassword;
        private Button btnLogin;
        private TextView registerAccount;
        private TextView findbackPw;
        private void bindView(View view){
            inputAccount= (EditText) view.findViewById(R.id.et_login_inputAccount);
            inputPassword= (EditText) view.findViewById(R.id.et_login_inputPW);
            btnLogin= (Button) view.findViewById(R.id.btn_login_login);
            registerAccount= (TextView) view.findViewById(R.id.tv_login_registerAccount);
            findbackPw= (TextView) view.findViewById(R.id.tv_login_findBackPW);
        }
    }
}
