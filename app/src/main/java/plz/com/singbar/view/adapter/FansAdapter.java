package plz.com.singbar.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.bean.FansBean;
import plz.com.singbar.operation.CircleTrans;
import plz.com.singbar.view.activity.HerHomeActivity;

/**
 * Created by Administrator on 2016/9/1.
 */
public class FansAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<FansBean> list;
    private LayoutInflater inflater;
    private boolean isOthersHome=false;
    public FansAdapter(Context context, List<FansBean> list) {
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
            view = inflater.inflate(R.layout.item_mine_fans, viewGroup, false);
            holder.head = (ImageView) view.findViewById(R.id.iv_mine_item_afans_head);
            holder.name = (TextView) view.findViewById(R.id.tv_mine_item_fans_name);
            holder.callName = (TextView) view.findViewById(R.id.tv_mine_fans_callName);
            holder.herHome = (TextView) view.findViewById(R.id.tv_fans_focus);
            view.setTag(holder);
        }
        holder = (ViewHolder) view.getTag();
        Picasso.with(context).load("file://"+list.get(i).getHead()).placeholder(R.mipmap.health_guide_woman_selected).resize(80, 80).transform(new CircleTrans()).centerCrop().into(holder.head);
        holder.name.setText(list.get(i).getPetName());
        holder.callName.setText(list.get(i).getCallName());
        if (isOthersHome){
            holder.herHome.setVisibility(View.INVISIBLE);
        }else {
            holder.herHome.setVisibility(View.VISIBLE);
            holder.herHome.setOnClickListener(this);
            holder.herHome.setTag(i);
        }
        return view;
    }
    public void notifyData(List<FansBean> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public void onClick(View view) {
        int i = (int) view.getTag();
        Log.i("result", i + "-->i");
        Intent intent=new Intent(context, HerHomeActivity.class);
        intent.putExtra("id",list.get(i).getUserId());
        context.startActivity(intent);
    }
    public void setIsOthersHome(boolean isOthersHome){
        this.isOthersHome=isOthersHome;
    }
    private class ViewHolder {
        private ImageView head;
        private TextView name;
        private TextView callName;
        private TextView herHome;
    }
}
