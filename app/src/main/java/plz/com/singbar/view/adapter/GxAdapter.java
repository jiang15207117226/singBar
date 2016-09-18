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

import java.util.ArrayList;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.view.activity.HistortActivity;
import plz.com.singbar.view.activity.MediaRecordertest;
import plz.com.singbar.view.info.DgGxInfo;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class GxAdapter extends BaseAdapter implements View.OnClickListener{
    private DgGxInfo dgGxInfo;
    private List<DgGxInfo> listo;
    private List<DgGxInfo> list;
    private Context context;
    private LayoutInflater inflater;
    public GxAdapter(Context context,List<DgGxInfo> list){
        this.context=context;
        this.list=list;
        listo=new ArrayList<>();
        inflater=LayoutInflater.from(context);
    }
    public void change(List<DgGxInfo> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder ;
        if(view==null){
            view = inflater.inflate(R.layout.time_songsets,null);
            holder=new ViewHolder();
            holder.dx= (TextView) view.findViewById(R.id.tv_dg_daxiao);
            holder.head= (ImageView) view.findViewById(R.id.iv_dg_head);
            holder.singername= (TextView) view.findViewById(R.id.tv_dg_name);
            holder.singname= (TextView) view.findViewById(R.id.tv_dg_gname);
            holder.kge= (Button) view.findViewById(R.id.btn_dg_kg);   //K歌按钮
            view.setTag(holder);
        }
        holder= (ViewHolder) view.getTag();
        holder.singname.setText(list.get(i).getSingname());
        holder.singername.setText(list.get(i).getSingername());
        holder.dx.setText(list.get(i).getSingdx()+"");
        Picasso.with(context).load(list.get(i).getIma()).into(holder.head);
        holder.kge.setOnClickListener(this);
        holder.kge.setTag(i);
        return view;
    }

    @Override
    public void onClick(View view) {
        int i= (int) view.getTag();
        Intent intent=new Intent(context, MediaRecordertest.class);
        intent.putExtra("data",list.get(i));
        intent.putExtra("tag",1);
        context.startActivity(intent);

        dgGxInfo = new DgGxInfo();
        dgGxInfo.setSingname(list.get(i).getSingname());
        dgGxInfo.setIma(list.get(i).getIma());
        dgGxInfo.setSingername(list.get(i).getSingername());
        dgGxInfo.setPlayurl(list.get(i).getPlayurl());
        listo.add(dgGxInfo);
        HistortActivity histortActivity = new HistortActivity();
        histortActivity.listinfo(listo);


    }


    class ViewHolder{
        private ImageView head;
        private TextView singname;
        private TextView singername;
        private TextView dx;
        private Button kge;
    }
}
