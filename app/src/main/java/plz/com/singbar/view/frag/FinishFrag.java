package plz.com.singbar.view.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import plz.com.singbar.R;

/**
 * Created by Administrator on 2016/8/31.
 */
public class FinishFrag extends Fragment {
    private TextView tvReplace;
    private int time = 5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_find_finish, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        tvReplace = (TextView) view.findViewById(R.id.tv_replace);
        tvReplace.setText(time + "s后为您跳转至登录页面...");
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                time--;
                if (time < 0) {
                    getActivity().finish();
                } else {
                    tvReplace.setText(time + "s后为您跳转至登录页面...");
                    handler.postDelayed(this,1000);
                }
            }
        },1000);
    }
}
