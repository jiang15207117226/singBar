package plz.com.singbar.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import plz.com.singbar.R;

/**
 * Created by Administrator on 2016/8/30.
 */
public class RegisterActivity extends Activity {
    private ViewHolder holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view=LayoutInflater.from(this).inflate(R.layout.activity_register,null);
        setContentView(view);
        init(view);
    }

    private void init(View view) {
        holder = new ViewHolder();
        holder.bindView(view);

    }
    private class ViewHolder{
        private EditText inputPhone;
        private Button checkPhone;
        private void bindView(View view){
            inputPhone= (EditText) view.findViewById(R.id.et_register_phone);
            checkPhone= (Button) view.findViewById(R.id.btn_register_proving);
        }
    }

}
