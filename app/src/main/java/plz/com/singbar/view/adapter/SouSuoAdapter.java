package plz.com.singbar.view.adapter;

import android.content.Context;


import android.content.Intent;
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

import plz.com.singbar.view.activity.MediaRecordertest;
import plz.com.singbar.view.info.SingInfo;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class SouSuoAdapter extends BaseAdapter implements View.OnClickListener {
    private SingInfo info;
    private int k;
    private String h;
    private Context context;
    private List<String> imalist;
    private LayoutInflater inflater;

    public SouSuoAdapter(Context context, SingInfo info, List<String> imalist) {
        this.context = context;
        this.info = info;
        this.imalist = imalist;
        inflater = LayoutInflater.from(context);
    }

    public void datachange(SingInfo info, List<String> imalist) {
        this.info = info;
        this.imalist = imalist;
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
        k = i;
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
        viewHolder.singername.setText(info.getSingInfoo().get(i).getSingername());
        double b = (info.getSingInfoo().get(i).getFilesize()) / (1024 * 1024);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float vv = Float.parseFloat(decimalFormat.format(b));
        viewHolder.daxiao.setText(vv + "M-");

        Picasso.with(context).load(imalist.get(i)).placeholder(R.mipmap.health_guide_men_selected).into(viewHolder.iv);
        viewHolder.kge.setOnClickListener(this);
        viewHolder.kge.setTag(i);
        return view;
    }

    @Override
    public void onClick(View view) {
        int i = (int) view.getTag();
        Intent intent = new Intent(context, MediaRecordertest.class);
        intent.putExtra("data", info.getSingInfoo().get(i));
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
