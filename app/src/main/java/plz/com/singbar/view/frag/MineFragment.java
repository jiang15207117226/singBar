package plz.com.singbar.view.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import plz.com.singbar.R;

/**
 * Created by Administrator on 2016/8/29.
 */
public class MineFragment extends Fragment {
    private ViewHolder holder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_mine, null);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.pop_song_top));
        init(view);//初始化
        return view;
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
    private class ViewHolder {
        private LinearLayout topBar;
        private ImageView topBtnLeft;
        private ImageView topBtnRight;
        private TextView topBarTitle;

        private void bindView(View view) {
            topBar = (LinearLayout) view.findViewById(R.id.layout_top_navgationBar);
            topBtnLeft = (ImageView) view.findViewById(R.id.iv_top_btn_left);
            topBtnRight = (ImageView) view.findViewById(R.id.iv_top_btn_right);
            topBarTitle = (TextView) view.findViewById(R.id.tv_top_title);
        }
    }

}
