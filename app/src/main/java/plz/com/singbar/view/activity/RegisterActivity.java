package plz.com.singbar.view.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.Instrumentation;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import plz.com.singbar.R;
import plz.com.singbar.operation.DbOperation;
import plz.com.singbar.view.frag.CheckPhoneFragment;
import plz.com.singbar.view.frag.FinishFrag;
import plz.com.singbar.view.frag.RegisterFragment;
import plz.com.singbar.view.frag.SetPwFrag;

/**
 * Created by Administrator on 2016/8/30.
 */
public class RegisterActivity extends Activity implements View.OnClickListener, CheckPhoneFragment.PhoneNumber, RegisterFragment.CallBackPhone,SetPwFrag.PhoneNumber {
    public long phoneNumber;
    private TextView back;
    private TextView title;
    private CheckPhoneFragment checkPhoneFrag;
    private RegisterFragment registerFrag;
    private SetPwFrag setPwFrag;
    private FinishFrag finishFrag;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_register, null);
        setContentView(view);
        init();
    }

    private void init() {
        back = (TextView) findViewById(R.id.tv_register_back);
        title = (TextView) findViewById(R.id.tv_register_title);
        checkPhoneFrag = new CheckPhoneFragment();
        checkPhoneFrag.getPhoneNumber(this);
        registerFrag = new RegisterFragment();
        registerFrag.getCallBackPhone(this);
        setPwFrag=new SetPwFrag();
        setPwFrag.setPhoneNumber(this);
        finishFrag=new FinishFrag();
        manager = getFragmentManager();
        manager.beginTransaction().add(R.id.fl_register_content, registerFrag).commit();
        title.setText("注册账号");
        back.setOnClickListener(this);
    }

    @Override
    public void startAuth(long phoneNumber) {
        this.phoneNumber=phoneNumber;
        Log.i("result",phoneNumber+"-->phoneNumber");
        if (DbOperation.queryPhone(phoneNumber) != 0) {
            Toast.makeText(this, "该手机号已被注册...", Toast.LENGTH_SHORT).show();
        } else {
            manager.beginTransaction().replace(R.id.fl_register_content, checkPhoneFrag).addToBackStack(null).commit();
            title.setText("验证手机");
        }
    }

    @Override
    public long getPhoneNumber() {
        phoneNumber = registerFrag.getPhone();
        return phoneNumber;
    }

    @Override
    public void replaceToSetPw() {
        manager.beginTransaction().replace(R.id.fl_register_content,setPwFrag).addToBackStack(null).commit();
    }

    @Override
    public void replaceToFinish() {
        manager.beginTransaction().replace(R.id.fl_register_content,finishFrag).commit();
    }

    @Override
    public void onClick(View view) {
        //返回
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                } catch (Exception e) {
                    Log.i("result", e.toString());
                }
            }
        }.start();
    }
}
