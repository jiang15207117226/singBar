package plz.com.singbar.view.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import plz.com.singbar.R;
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.operation.GenerMbAccount;

/**
 * Created by Administrator on 2016/8/31.
 */
public class SetPwFrag extends Fragment implements View.OnClickListener {
    private TextView mbAccount;
    private EditText inputPw;
    private EditText petName;
    private TextView enSure;
    private String account;
    private PhoneNumber p;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_register_set_pw, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        mbAccount = (TextView) view.findViewById(R.id.tv_register_mbAccount);
        petName = (EditText) view.findViewById(R.id.et_register_petName);
        inputPw = (EditText) view.findViewById(R.id.et_register_pw);
        enSure = (TextView) view.findViewById(R.id.tv_register_enTrue);

        account = GenerMbAccount.generMbAccount();
        mbAccount.setText(account);
        enSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String pw = inputPw.getText().toString();
        String pN = petName.getText().toString();
        if (pw == null || pw.length() < 1) {
            if (pN == null || pN.length() < 1) {
                Toast.makeText(getActivity(), "请输入昵称...", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "请输入密码...", Toast.LENGTH_SHORT).show();
            }
        } else {
            UserBean bean = new UserBean();
            bean.setAccount(account);
            bean.setPetName(pN);
            bean.setPw(pw);
            bean.setPhone(p.getPhoneNumber());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String time = format.format(new Date());
            bean.setTime(time);
            bean.save();
            Log.i("result","save");
            p.replaceToFinish();
        }
    }

    public void setPhoneNumber(PhoneNumber p) {
        this.p = p;
    }

    public interface PhoneNumber {
        long getPhoneNumber();

        void replaceToFinish();
    }
}
