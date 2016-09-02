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
import plz.com.singbar.bean.AttenBean;
import plz.com.singbar.operation.CircleTrans;

/**
 * Created by Administrator on 2016/9/1.
 */
public class AttentionAdapter extends BaseAdapter {
    private Context context;
    private List<AttenBean> list;
    private LayoutInflater inflater;

    public AttentionAdapter(Context context, List<AttenBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_mine_show_attention, viewGroup, false);
            holder.head = (ImageView) view.findViewById(R.id.iv_mine_item_attention_head);
            holder.name = (TextView) view.findViewById(R.id.tv_mine_item_attention_name);
            holder.callName = (TextView) view.findViewById(R.id.tv_mine_attention_callName);
            holder.stadus = (TextView) view.findViewById(R.id.tv_attention_focus);

            view.setTag(holder);
        }
        holder = (ViewHolder) view.getTag();
        Picasso.with(context).load(list.get(i).getHead()).placeholder(R.mipmap.health_guide_woman_selected).resize(80, 80).transform(new CircleTrans()).centerCrop().into(holder.head);
        holder.name.setText(list.get(i).getPetName());
        holder.callName.setText(list.get(i).getCallName());
        if (list.get(i).isStadus()) {
            holder.stadus.setText("互相关注");
        } else {
            holder.stadus.setText("关注中");
        }
        return view;
    }

    private class ViewHolder {
        private ImageView head;     //头像
        private TextView name;      //昵称
        private TextView callName;  //称号
        private TextView stadus;    //状态
    }
}
