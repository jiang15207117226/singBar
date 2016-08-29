package plz.com.singbar.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import plz.com.singbar.R;

/**
 * Created by Administrator on 2016/8/29.
 */
public class HomeActivity extends Activity implements RadioGroup.OnCheckedChangeListener{
    private  ViewHolder holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view=LayoutInflater.from(this).inflate(R.layout.activity_home,null);
        setContentView(view);
        init(view);
    }

    /**
     * 初始化
     * @param view
     */
    private void init(View view) {
        holder = new ViewHolder();
        holder.bindView(view);
        //设置底部RddioGroup监听
        holder.bottomRg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rb_home:
                //首页
                break;
            case R.id.rb_list:
                //榜单
                break;
            case R.id.rb_trends:
                //动态
                break;
            case R.id.rb_mine:
                //我的
//                Intent intent=new Intent(thi)
                break;
        }
    }

    //视图管理类
    private class ViewHolder{
        private RelativeLayout layout_bottom;
        private RadioGroup bottomRg;
        private void bindView(View view){
            layout_bottom= (RelativeLayout) view.findViewById(R.id.layout_home_bottom);
            bottomRg= (RadioGroup) layout_bottom.findViewById(R.id.rg_home_bottom);
        }
    }
}
