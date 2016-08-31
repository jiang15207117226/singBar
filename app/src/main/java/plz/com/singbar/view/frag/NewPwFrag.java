package plz.com.singbar.view.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.view.activity.FindBackPwActivity;

/**
 * Created by Administrator on 2016/8/31.
 */
public class NewPwFrag extends Fragment implements View.OnClickListener {
    private EditText inputNewPw;
    private TextView enSureNew;
    private GetPhone phone;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_find_acrossed, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        /**
         * 找到控件
         */
        inputNewPw = (EditText) view.findViewById(R.id.et_setNew_pw);
        enSureNew = (TextView) view.findViewById(R.id.tv_setNew_enTrue);
        FindBackPwActivity find= (FindBackPwActivity) getActivity();
        find.setRgchecked(1);
        enSureNew.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String pw = inputNewPw.getText().toString();
        long phoneNumber=phone.getPhoneNumber();
        List<UserBean>list=DataSupport.where("phone = ?",phoneNumber+"").find(UserBean.class);
        if (list==null||list.size()<1){
            Toast.makeText(getActivity(),"数据出错...",Toast.LENGTH_SHORT).show();
        }else{
            list.get(0).setPw(pw);
            list.get(0).update(list.get(0).getId());
            phone.moveToFinishFrag();
        }
    }
    public void getGetPhone(GetPhone phone){
        this.phone=phone;
    }
    public interface GetPhone{
        long getPhoneNumber();
        void moveToFinishFrag();
    }
}
