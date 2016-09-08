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
import plz.com.singbar.operation.DataOperation;
import plz.com.singbar.operation.DbOperation;

public class MainActivity extends Activity implements View.OnClickListener{
    private  String[]urls=new String[]{
            "http://changba.com/commonreport/testsrc/view2.php?id=899&msgid=899&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=868&msgid=868&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=839&msgid=839&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=827&msgid=827&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=799&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=739&msgid=739&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=729&msgid=729&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=710&msgid=710&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=691&msgid=691&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=659&msgid=659&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
    };
    private   ViewHolder holder;
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
//        new DataOperation().execute(urls);
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
//                               intent.putExtra("userBean",bean);
                               startActivity(intent);
//                               finish();
//                           }else{
//                               Toast.makeText(this,"密码错误...",Toast.LENGTH_SHORT).show();
//                           }
//                       }
//                    }
//               }
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
