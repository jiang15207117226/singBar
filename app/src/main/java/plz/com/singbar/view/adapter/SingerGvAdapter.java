package plz.com.singbar.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.view.info.SingerHNInfo;

/**
 * Created by Administrator on 2016/9/1 0001.
 */
public class SingerGvAdapter extends BaseAdapter{
    private List<SingerHNInfo> list;
    private Context context;
    private LayoutInflater inflater;

    public SingerGvAdapter(List<SingerHNInfo> list,Context context){
        this.list=list;
        this.context=context;
        inflater = LayoutInflater.from(context);
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
        ViewHolder holder;
        if(view == null){
            view=inflater.inflate(R.layout.item_gv, null);
            holder = new ViewHolder();
            holder.ima= (ImageView) view.findViewById(R.id.iv_gv_head);
            holder.name= (TextView) view.findViewById(R.id.tv_gv_singername);
            view.setTag(holder);
        }
        holder= (ViewHolder) view.getTag();
        holder.name.setText(list.get(i).getName());
        Picasso.with(context).load(list.get(i).getIma()).placeholder(R.mipmap.ic_launcher).into(holder.ima);

        return view;
    }
    class ViewHolder{
        private ImageView ima;
        private TextView name;
    }
}
