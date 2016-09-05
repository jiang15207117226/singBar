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
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.bean.UserOwnSongsBean;
import plz.com.singbar.operation.CircleTrans;

/**
 * Created by Administrator on 2016/8/31.
 */
public class MineOpusAdapter extends BaseAdapter {
    private Context context;
    private List<UserOwnSongsBean> list;
    private UserBean bean;
    private LayoutInflater inflater;

    public MineOpusAdapter(Context context, List<UserOwnSongsBean> list, UserBean bean) {
        this.context = context;
        this.list = list;
        this.bean=bean;
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
            view = inflater.inflate(R.layout.item_mine_show_opus, null);
            holder.head = (ImageView) view.findViewById(R.id.iv_mine_item_opus_head);
            holder.userName = (TextView) view.findViewById(R.id.tv_mine_item_opus_username);
            holder.songName = (TextView) view.findViewById(R.id.tv_mine_item_opus_songName);
            holder.time = (TextView) view.findViewById(R.id.tv_mine_item_opus_time);
            holder.listenCount = (TextView) view.findViewById(R.id.tv_mine_item_opus_listenCount);
            holder.flowers = (TextView) view.findViewById(R.id.tv_mine_item_opus_flowers);
            holder.comments = (TextView) view.findViewById(R.id.tv_mine_item_opus_commends);
            holder.contents = (TextView) view.findViewById(R.id.tv_mine_item_opus_source);
            view.setTag(holder);
        }
        holder= (ViewHolder) view.getTag();
        Picasso.with(context).load(bean.getHead()).placeholder(R.mipmap.health_guide_woman_selected).resize(80,80).transform(new CircleTrans()).centerCrop().into(holder.head);
        holder.userName.setText(bean.getPetName());
        String songName=list.get(i).getSongName();
        holder.songName.setText(songName);
        holder.time.setText(list.get(i).getTime());
        holder.listenCount.setText(list.get(i).getTrys()+"");
        holder.flowers.setText(list.get(i).getFlowers()+"");
        holder.comments.setText(list.get(i).getComments()+"");
        holder.contents.setText("...来听听我唱的《"+songName+"》");
        return view;
    }


    private class ViewHolder {
        private ImageView head;
        private TextView userName;
        private TextView songName;
        private TextView time;
        private TextView listenCount;
        private TextView flowers;
        private TextView comments;
        private TextView contents;
    }
}
