package plz.com.singbar.view.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import plz.com.singbar.R;

/**
 * Created by Administrator on 2016/8/30.
 */
public class FindBackPwFrag extends Fragment implements View.OnClickListener {
    private ViewHolder holder;
    private SendSms sms;
    private int authCode = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_find_auth, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        holder = new ViewHolder();
        holder.bindView(view);

        sms.setRgchecked(0);
        holder.enSure.setOnClickListener(this);
        holder.getAuthCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_authCode:
                String phone = holder.phoneNumber.getText().toString();
                if (phone.length() < 11) {
                    holder.isInputPhone.setVisibility(View.VISIBLE);
                } else {
                    holder.isInputPhone.setVisibility(View.INVISIBLE);
                    authCode = (int) ((Math.random()*9+1) * 100000);
                    String msg = "【麦吧】验证码:" + authCode + "您正在使用找回密码功能，请勿泄露验证码。";
                    sms.sendAuthSms(phone, msg);
                }

                break;
            case R.id.tv_find_enTrue:
                if (holder.phoneNumber.getText().toString().length() < 11) {
                    holder.isInputPhone.setVisibility(View.VISIBLE);
                } else {
                    if (Integer.parseInt(holder.authCode.getText().toString()) != authCode) {
                        holder.findAuth.setVisibility(View.VISIBLE);
                    } else {
                        holder.findAuth.setVisibility(View.INVISIBLE);
                        sms.moveToSetNewPw();
                    }
                    holder.isInputPhone.setVisibility(View.INVISIBLE);
                }
                break;
        }

    }
    public long getPhoneNumber(){
        return Long.parseLong(holder.phoneNumber.getText().toString());
    }
    private class ViewHolder {
        private EditText phoneNumber;
        private EditText authCode;
        private TextView getAuthCode;
        private TextView findAuth;
        private TextView isInputPhone;
        private TextView enSure;

        private void bindView(View view) {
            phoneNumber = (EditText) view.findViewById(R.id.et_find_phoneNumber);
            authCode = (EditText) view.findViewById(R.id.et_find_authCode);
            getAuthCode = (TextView) view.findViewById(R.id.tv_get_authCode);
            isInputPhone = (TextView) view.findViewById(R.id.tv_find_phone_input);
            findAuth = (TextView) view.findViewById(R.id.tv_find_auth);
            enSure = (TextView) view.findViewById(R.id.tv_find_enTrue);
        }
    }

    public void getSendSms(SendSms sms) {
        this.sms = sms;
    }

    public interface SendSms {
        void setRgchecked(int i);
        void moveToSetNewPw();
        void sendAuthSms(String phone, String msg);
    }
}
