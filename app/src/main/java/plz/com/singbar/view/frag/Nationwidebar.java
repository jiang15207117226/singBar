package plz.com.singbar.view.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import plz.com.singbar.R;


/**
 * Created by Administrator on 2016/8/31.
 */
public class Nationwidebar extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_nationwidebar, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
