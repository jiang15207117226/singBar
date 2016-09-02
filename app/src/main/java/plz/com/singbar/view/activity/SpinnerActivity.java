package plz.com.singbar.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.view.adapter.NationwidebarAdapter;
import plz.com.singbar.view.info.NationwidebarInfo;

/**
 * Created by Administrator on 2016/9/2.
 */
public class SpinnerActivity extends Activity {
    private TextView tv;
    private Spinner sp;
    private Context context;
    private List<NationwidebarInfo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepagelistview);
        sp = (Spinner) findViewById(R.id.sping);
        sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] str = getResources().getStringArray(R.array.sping);
            }
        });
        NationwidebarAdapter adapter = new NationwidebarAdapter(list, context);
        sp.setAdapter(adapter);
    }
}
