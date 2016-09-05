package plz.com.singbar.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import plz.com.singbar.R;
import plz.com.singbar.view.frag.GdFrag;
import plz.com.singbar.view.frag.GxFragment;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class DiangeActivity extends FragmentActivity {
    private RadioGroup rg;
    private RadioButton gx;
    private RadioButton fl;
    private RadioButton hc;
    private RadioButton qc;
    private FragmentManager manager;
    private GxFragment gxfrag;
    private GdFrag gdFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diange);
        init();
        build();
    }



    private void init() {
        rg= (RadioGroup) findViewById(R.id.rg_diange_one);
        gx= (RadioButton) findViewById(R.id.rb_dg_gx);
        fl= (RadioButton) findViewById(R.id.rb_dg_dg);
        hc= (RadioButton) findViewById(R.id.rb_dg_hc);
        qc= (RadioButton) findViewById(R.id.rb_dg_qc);
        manager=getSupportFragmentManager();
        gxfrag=new GxFragment();
        gdFrag=new GdFrag();
        manager.beginTransaction().add(R.id.fl_diange_one,gxfrag).commit();
    }
    private void build() {
        rg.setOnCheckedChangeListener(l);

    }
    RadioGroup.OnCheckedChangeListener l = new RadioGroup.OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            FragmentTransaction transaction=manager.beginTransaction();
            switch (i){
                case R.id.rb_dg_gx:
                    gx.setTextColor(getResources().getColor(R.color.dg));
                    fl.setTextColor(getResources().getColor(R.color.bai));
                    hc.setTextColor(getResources().getColor(R.color.bai));
                    qc.setTextColor(getResources().getColor(R.color.bai));
                    transaction.replace(R.id.fl_diange_one,gxfrag).commit();

                    break;
                case R.id.rb_dg_dg:
                    gx.setTextColor(getResources().getColor(R.color.bai));
                    fl.setTextColor(getResources().getColor(R.color.dg));
                    hc.setTextColor(getResources().getColor(R.color.bai));
                    qc.setTextColor(getResources().getColor(R.color.bai));
                    transaction.replace(R.id.fl_diange_one,gdFrag).commit();
                    break;
                case R.id.rb_dg_hc:
                    gx.setTextColor(getResources().getColor(R.color.bai));
                    fl.setTextColor(getResources().getColor(R.color.bai));
                    hc.setTextColor(getResources().getColor(R.color.dg));
                    qc.setTextColor(getResources().getColor(R.color.bai));
                    break;
                case R.id.rb_dg_qc:
                    gx.setTextColor(getResources().getColor(R.color.bai));
                    fl.setTextColor(getResources().getColor(R.color.bai));
                    hc.setTextColor(getResources().getColor(R.color.bai));
                    qc.setTextColor(getResources().getColor(R.color.dg));
                    break;
            }
        }
    };
    public void onclick(View v){
        switch (v.getId()){
            case R.id.iv_dg_back:
                finish();
                break;
        }
    }
}
