package plz.com.singbar.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import plz.com.singbar.R;

/**
 * Created by Administrator on 2016/8/29.
 */
public class MineActivity extends Activity {
    private  ViewHolder holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view=LayoutInflater.from(this).inflate(R.layout.activity_mine,null);
        setContentView(view);
        init(view);//初始化
    }

    /**
     * 初始化
     * @param view
     */
    private void init(View view) {
        holder = new ViewHolder();
        holder.bindView(view);//绑定视图

        holder.topBarTitle.setText(getString(R.string.mine_title));
    }

    /**
     * 视图管理类
     */
    private class ViewHolder{
        private LinearLayout topBar;
        private ImageView topBtnLeft;
        private ImageView topBtnRight;
        private TextView topBarTitle;
        private void bindView(View view){
            topBar= (LinearLayout) view.findViewById(R.id.layout_top_navgationBar);
            topBtnLeft= (ImageView) view.findViewById(R.id.iv_top_btn_left);
            topBtnRight= (ImageView) view.findViewById(R.id.iv_top_btn_right);
            topBarTitle= (TextView) view.findViewById(R.id.tv_top_title);
        }
    }
}
