package plz.com.singbar.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.bean.UserOwnSongsBean;
import plz.com.singbar.view.activity.MediaRecordertest;
import plz.com.singbar.view.info.DgGxInfo;

/**
 * Created by Administrator on 2016/9/18.
 */
public class LocalAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<UserOwnSongsBean>list;
    private LayoutInflater inflater;
    public LocalAdapter(Context context, List<UserOwnSongsBean> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
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
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.item_local,null);
            holder.name= (TextView) view.findViewById(R.id.tv_songname);
            holder.singer= (TextView) view.findViewById(R.id.tv_songSinger);
            holder.singBtn= (Button) view.findViewById(R.id.btn_song);
            view.setTag(holder);
        }
        holder= (ViewHolder) view.getTag();
        holder.name.setText(list.get(i).getSongName());
        holder.singer.setText(list.get(i).getTime());
        holder.singBtn.setOnClickListener(this);
        holder.singBtn.setTag(i);
        return view;
    }

    @Override
    public void onClick(View view) {
        int i= (int) view.getTag();
        DgGxInfo info=new DgGxInfo();
        info.setPlayurl(list.get(i).getVoiceUrl());
        info.setSingname(list.get(i).getSongName());
        Intent intent=new Intent(context, MediaRecordertest.class);
        intent.putExtra("data",info);
        intent.putExtra("tag",1);
        context.startActivity(intent);
    }

    private class ViewHolder{
        private TextView name;
        private TextView singer;
        private Button singBtn;
    }
}
