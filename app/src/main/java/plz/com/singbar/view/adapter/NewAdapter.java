package plz.com.singbar.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.operation.CircleTrans;
import plz.com.singbar.view.activity.MediaRecordertest;
import plz.com.singbar.view.info.SingInfo;
import plz.com.singbar.view.info.SingInfoo;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class NewAdapter extends BaseAdapter implements View.OnClickListener {
    private SingInfo info;
    private Context context;
    private LayoutInflater inflater;
    private List<SingInfoo> list;
    private String name;

    public NewAdapter(Context context, SingInfo info, String name) {
        this.context = context;
        this.info = info;
        this.name = name;
        list = info.getSingInfoo();
        inflater = LayoutInflater.from(context);
    }

    public void datachange(SingInfo info) {
        this.info = info;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return info == null ? 0 : info.getSingInfoo().size();
    }

    @Override
    public Object getItem(int i) {
        return info.getSingInfoo().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.time_songsets, null);
            viewHolder = new ViewHolder();
            viewHolder.singername = (TextView) view.findViewById(R.id.tv_dg_name);
            viewHolder.singname = (TextView) view.findViewById(R.id.tv_dg_gname);
            viewHolder.daxiao = (TextView) view.findViewById(R.id.tv_dg_daxiao);
            viewHolder.iv = (ImageView) view.findViewById(R.id.iv_dg_head);
            viewHolder.kge = (Button) view.findViewById(R.id.btn_dg_kg);   //K歌按钮
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();
        viewHolder.singname.setText(info.getSingInfoo().get(i).getFilename());
        viewHolder.singername.setText(list.get(i).getSingername());

        double b=(double) (info.getSingInfoo().get(i).getFilesize())/(1024*1024);
        Log.i("result",b+"^^^^^^^^^^^^^^^^^^");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float vv=Float.parseFloat(decimalFormat.format(b));
        viewHolder.daxiao.setText(vv+"M-");

        String uri = info.getSingInfoo().get(i).getIma();
        if (uri != null && uri.length() >= 1) {

            Picasso.with(context).load(uri).placeholder(R.mipmap.health_guide_men_selected).transform(new CircleTrans()).resize(80, 80).centerCrop().into(viewHolder.iv);
            Picasso.with(context).load(uri).placeholder(R.mipmap.health_guide_men_selected).transform(new CircleTrans()).resize(60, 60).centerCrop().into(viewHolder.iv);
        }
        viewHolder.kge.setOnClickListener(this);
        viewHolder.kge.setTag(i);

        return view;
    }

    @Override
    public void onClick(View view) {
        int i = (int) view.getTag();
        Intent intent = new Intent(context, MediaRecordertest.class);
        SingInfoo infoo = info.getSingInfoo().get(i);

        intent.putExtra("data", infoo);
        intent.putExtra("tag", 0);
        context.startActivity(intent);


    }

    class ViewHolder {
        private ImageView iv;
        private TextView singname;
        private TextView daxiao;
        private TextView singername;
        private Button kge;
    }
}
