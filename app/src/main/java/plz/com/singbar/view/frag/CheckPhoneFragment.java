package plz.com.singbar.view.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import plz.com.singbar.R;

/**
 * Created by Administrator on 2016/8/30.
 */
public class CheckPhoneFragment extends Fragment implements View.OnClickListener {
    private ViewHolder holder;
    private PhoneNumber p;
    private long phoneNumber;
    private int authCode = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_check_phone, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        holder = new ViewHolder();
        holder.bindView(view);
        phoneNumber = p.getPhoneNumber();
        holder.phoneNumber.setText("请输入手机号" + phoneNumber + "收到的短信验证码");
        holder.getAuthCode.setOnClickListener(this);
        holder.finishAuth.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_authCode:
                //获取验证码
                Log.i("result", "获取验证码");
                authCode = (int) ((Math.random() * 9 + 1) * 100000);
                String msg = "【麦吧】验证码:" + authCode + "您正在使用注册麦吧账号，请勿泄露验证码。";
                Log.i("result", phoneNumber + "");
                sendAuthSms(phoneNumber + "", msg);
                break;
            case R.id.tv_phone_finish:
                //完成验证
                Log.i("result", "完成验证" + authCode);
                if (Long.parseLong(holder.inputAuthCode.getText().toString()) == authCode) {
                    p.replaceToSetPw();
                }else{
                    Toast.makeText(getActivity(),"验证码错误...",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void sendAuthSms(String phone, String msg) {
        SmsManager smsManager = SmsManager.getDefault();
        /** 切分短信，每七十个汉字切一个，不足七十就只有一个：返回的是字符串的List集合*/
        List<String> texts = smsManager.divideMessage(msg);
        //发送之前检查短信内容是否为空
        for (int i = 0; i < texts.size(); i++) {
            String text = texts.get(i);
            smsManager.sendTextMessage(phone, null, text, null, null);
            Log.i("result", "短信-->" + text);
            Toast.makeText(getActivity(), "验证码已发送，请注意查收...", Toast.LENGTH_SHORT).show();
        }
    }

    private class ViewHolder {
        private TextView phoneNumber;
        private EditText inputAuthCode;
        private TextView getAuthCode;
        private TextView finishAuth;

        private void bindView(View view) {
            phoneNumber = (TextView) view.findViewById(R.id.tv_phone_number);
            inputAuthCode = (EditText) view.findViewById(R.id.et_phone_input);
            getAuthCode = (TextView) view.findViewById(R.id.tv_get_authCode);
            finishAuth = (TextView) view.findViewById(R.id.tv_phone_finish);
        }
    }

    public void getPhoneNumber(PhoneNumber p) {
        this.p = p;
    }

    public interface PhoneNumber {
        long getPhoneNumber();

        void replaceToSetPw();
    }
}
