package plz.com.singbar.view.frag;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.bean.NoteBean;
import plz.com.singbar.operation.DataOperation;
import plz.com.singbar.view.activity.ReadContentActivity;
import plz.com.singbar.view.adapter.HomeitemAdapter;


/**
 * Created by Administrator on 2016/8/26.
 */
public class HomeitemFragment extends Fragment implements HomeFragment.SetContext,DataOperation.OnPostFinish{
    private String[] urls = new String[]{
            "http://changba.com/commonreport/testsrc/view2.php?id=899&msgid=899&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=868&msgid=868&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=839&msgid=839&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=827&msgid=827&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=799&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=739&msgid=739&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=729&msgid=729&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=710&msgid=710&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=691&msgid=691&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
            "http://changba.com/commonreport/testsrc/view2.php?id=659&msgid=659&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A",
    };
    private View view;
    private com.handmark.pulltorefresh.library.PullToRefreshListView lv;
    private List<NoteBean> noteList;
    private List<NoteBean> noteListPart;
    private ImageView loadingImg;
    private TextView loadingText;
    private HomeitemAdapter adapter;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return view;
    }

    private void init() {
        noteListPart = new ArrayList<>();
        lv = (PullToRefreshListView) view.findViewById(R.id.lv_fragment_home);
        loadingImg = (ImageView) view.findViewById(R.id.iv_loading);
        loadingText = (TextView) view.findViewById(R.id.tv_loading);
        DataOperation dataOperation=new DataOperation();
        dataOperation.setOnPostFinish(this);
        dataOperation.execute(urls);
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        lv.getRefreshableView().setOnItemClickListener(listener);
        lv.setOnRefreshListener(fl);
    }

    private void buildadapter(List<NoteBean> list) {
//        Context context = HomeitemFragment.this.getContext();
        Log.i("result",context+"--context");
        adapter = new HomeitemAdapter(list, context);
        lv.setAdapter(adapter);
    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.i("result", "onItemClick-->" + (i - 1));
            Intent intent = new Intent(getActivity(), ReadContentActivity.class);
            intent.putExtra("url", noteListPart.get(i - 1).getUrl());
            startActivity(intent);
        }
    };

    PullToRefreshBase.OnRefreshListener2 fl = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            handler.postDelayed(thread, 1000);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            Log.i("result", "onPullUpToRefresh");
            addData();
            handler.postDelayed(thread, 1000);
        }
    };
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Log.i("result", "noteListPart.size-->" + noteListPart.size());
                adapter.notifyData(noteListPart);
                lv.onRefreshComplete();
            }
        }
    };
    Thread thread = new Thread() {
        @Override
        public void run() {
            handler.sendEmptyMessage(1);
        }
    };

    @Override
    public void setContext(Context context) {
        this.context=context;
    }


    @Override
    public void onPostFinish(List<NoteBean> noteBeen) {
        Log.i("result","onPostFinish");
        noteList = noteBeen;
        AnimationDrawable drawable = (AnimationDrawable) loadingImg.getDrawable();
        drawable.stop();
        addData();
        loadingText.setVisibility(View.GONE);
        loadingImg.setVisibility(View.GONE);
        buildadapter(noteListPart);
    }

    @Override
    public void onPre() {
        Log.i("result","onPre");
        AnimationDrawable drawable = (AnimationDrawable) loadingImg.getDrawable();
        drawable.start();
        loadingText.setVisibility(View.VISIBLE);
    }

    private void addData() {
        Log.i("result", noteListPart.size() + "");
        int s = noteListPart.size();
        for (int i = noteListPart.size(); i < (3 + s); i++) {
            if (i < noteList.size()) {
                noteListPart.add(noteList.get(i));
            }
        }
        Log.i("result", noteListPart.size() + "--after");
    }
}
