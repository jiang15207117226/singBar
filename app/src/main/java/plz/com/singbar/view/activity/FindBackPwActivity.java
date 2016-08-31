package plz.com.singbar.view.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.Instrumentation;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.view.frag.FindBackPwFrag;
import plz.com.singbar.view.frag.FinishFrag;
import plz.com.singbar.view.frag.NewPwFrag;

/**
 * Created by Administrator on 2016/8/30.
 */
public class FindBackPwActivity extends Activity implements View.OnClickListener, FindBackPwFrag.SendSms,NewPwFrag.GetPhone{
    private FragmentManager manager;
    private RadioGroup proRg;
    private RadioButton zero;
    private RadioButton one;
    private RadioButton two;
    private TextView back;
    private FindBackPwFrag findBackPwFrag;
    private NewPwFrag newPwFrag;
    private FinishFrag finishFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findback_pw);
        init();
    }

    private void init() {
        manager = getFragmentManager();
        proRg = (RadioGroup) findViewById(R.id.rg_find);
        zero = (RadioButton) findViewById(R.id.rb_find_zero);
        one = (RadioButton) findViewById(R.id.rb_find_one);
        two = (RadioButton) findViewById(R.id.rb_find_two);

        back = (TextView) findViewById(R.id.tv_find_back);
        findBackPwFrag = new FindBackPwFrag();
        findBackPwFrag.getSendSms(this);
        newPwFrag = new NewPwFrag();
        newPwFrag.getGetPhone(this);
        finishFrag = new FinishFrag();

        manager.beginTransaction().add(R.id.fl_find_content, findBackPwFrag).commit();
        back.setOnClickListener(this);
    }
    @Override
    public void moveToSetNewPw() {
        manager.beginTransaction().replace(R.id.fl_find_content, newPwFrag).addToBackStack(null).commit();
    }
    @Override
    public void moveToFinishFrag() {
        manager.beginTransaction().replace(R.id.fl_find_content, finishFrag).commit();
        setRgchecked(2);
    }
    private void setRgTextColor(int i) {
        switch (i) {
            case 0:
                zero.setTextColor(getResources().getColor(R.color.black));
                one.setTextColor(getResources().getColor(R.color.find_text_color));
                two.setTextColor(getResources().getColor(R.color.find_text_color));
                break;
            case 1:
                zero.setTextColor(getResources().getColor(R.color.find_text_color));
                one.setTextColor(getResources().getColor(R.color.black));
                two.setTextColor(getResources().getColor(R.color.find_text_color));
                break;
            case 2:
                zero.setTextColor(getResources().getColor(R.color.find_text_color));
                one.setTextColor(getResources().getColor(R.color.find_text_color));
                two.setTextColor(getResources().getColor(R.color.black));
                break;
        }
    }
    @Override
    public long getPhoneNumber(){
        return findBackPwFrag.getPhoneNumber();
    }
    @Override
    public void setRgchecked(int i) {
        switch (i) {
            case 0:
                setRgTextColor(i);
                proRg.check(R.id.rb_find_zero);
                break;
            case 1:
                setRgTextColor(i);
                proRg.check(R.id.rb_find_one);
                break;
            case 2:
                setRgTextColor(i);
                proRg.check(R.id.rb_find_two);
                break;
        }
    }

    @Override
    public void sendAuthSms(String phone, String msg) {
        SmsManager smsManager = SmsManager.getDefault();
        /** 切分短信，每七十个汉字切一个，不足七十就只有一个：返回的是字符串的List集合*/
        List<String> texts = smsManager.divideMessage(msg);
        //发送之前检查短信内容是否为空
        for (int i = 0; i < texts.size(); i++) {
            String text = texts.get(i);
            smsManager.sendTextMessage(phone, null, text, null, null);
            Log.i("result", "短信-->" + text);
            Toast.makeText(this, "验证码已发送，请注意查收...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
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
