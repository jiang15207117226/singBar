package plz.com.singbar.view.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import plz.com.singbar.R;

/**
 * Created by Administrator on 2016/8/30.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private ViewHolder holder;
    private CallBackPhone callBackPhone;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_register, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        holder = new ViewHolder();
        holder.bindView(view);


        holder.checkPhone.setClickable(false);
        addTextChangeWatcher();
        holder.serviceAgree.setOnCheckedChangeListener(this);
    }

    private void addTextChangeWatcher() {
        holder.inputPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int lengh = holder.inputPhone.getText().toString().length();
                if (lengh == 11 && holder.serviceAgree.isChecked()) {
                    holder.checkPhone.setClickable(true);
                    holder.checkPhone.setOnClickListener(RegisterFragment.this);
                    holder.checkPhone.setBackgroundColor(getResources().getColor(R.color.find_auth));
                } else {
                    holder.checkPhone.setClickable(false);
                    holder.checkPhone.setBackgroundColor(getResources().getColor(R.color.login_div_or_text_color));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register_back:

                break;
            case R.id.tv_register_proving:
                Log.i("result", "检查手机号-->" + holder.checkPhone.isClickable());
                callBackPhone.startAuth(Long.parseLong(holder.inputPhone.getText().toString()));
                break;
        }
    }

    public long getPhone() {
        return Long.parseLong(holder.inputPhone.getText().toString());
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b && holder.inputPhone.getText().toString().length() == 11) {
            if (!holder.checkPhone.isClickable()) {
                holder.checkPhone.setOnClickListener(RegisterFragment.this);
            }
            holder.checkPhone.setClickable(true);
            holder.checkPhone.setBackgroundColor(getResources().getColor(R.color.find_auth));
        } else {
            holder.checkPhone.setClickable(false);
            holder.checkPhone.setFocusable(false);
            holder.checkPhone.setBackgroundColor(getResources().getColor(R.color.login_div_or_text_color));
        }
    }

    private class ViewHolder {
        private EditText inputPhone;
        private TextView checkPhone;
        private CheckBox serviceAgree;

        private void bindView(View view) {
            inputPhone = (EditText) view.findViewById(R.id.et_register_phone);
            checkPhone = (TextView) view.findViewById(R.id.tv_register_proving);
            serviceAgree = (CheckBox) view.findViewById(R.id.cb_register_agree);
        }
    }
    public void getCallBackPhone(CallBackPhone callBackPhone){
        this.callBackPhone=callBackPhone;
    }
    public interface CallBackPhone {
        void startAuth(long phoneNumber);
    }
}
