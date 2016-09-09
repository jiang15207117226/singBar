package plz.com.singbar.view.adapter;

import android.content.Context;
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
import plz.com.singbar.view.info.BdSingInfo;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class BdAdapter extends BaseAdapter{
    private List<BdSingInfo> list;
    private Context context;
    private LayoutInflater inflater;

    public BdAdapter(Context context,List<BdSingInfo> list){
        this.list=list;
        this.context=context;
        inflater=LayoutInflater.from(context);
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
        holder.singname.setText(list.get(i).getSname());
        holder.singername.setText(list.get(i).getName());

        double b=(list.get(i).getDx())/(1024*1024);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float vv=Float.parseFloat(decimalFormat.format(b));
        holder.dx.setText(vv+"M");

        Picasso.with(context).load(R.mipmap.health_guide_men_selected).into(holder.head);

        return view;
    }

    class ViewHolder{
        private TextView singname;
        private TextView singername;
        private TextView dx;
        private Button kge;
        private ImageView head;
    }


}
