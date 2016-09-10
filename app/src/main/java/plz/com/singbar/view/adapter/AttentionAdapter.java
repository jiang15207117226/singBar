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
import plz.com.singbar.operation.CircleTrans;

/**
 * Created by Administrator on 2016/9/1.
 */
public class AttentionAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<UserBean> list;
    private int[] _ids;
    private LayoutInflater inflater;
    private ViewHolder holder;
    private boolean isInsertAtten = false;
    private boolean[] isAtten;
    private int[] positions;

    public AttentionAdapter(Context context, List<UserBean> list, int[] _ids) {
        this.context = context;
        this.list = list;
        this._ids = _ids;
        positions = new int[list.size()];
        isAtten=new boolean[list.size()];
//        for (int i=0;i<isAtten.length;i++){
//            isAtten[i]=false;
//        }
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
        Picasso.with(context).load("file://"+list.get(i).getHead()).placeholder(R.mipmap.health_guide_woman_selected).resize(80, 80).transform(new CircleTrans()).centerCrop().into(holder.head);
        holder.name.setText(list.get(i).getPetName());
        holder.callName.setText(list.get(i).getButility());
        if (isInsertAtten) {
            if (_ids!=null){
                for (int k = 0; k < _ids.length; k++) {
                    if (list.get(i).getId() != _ids[k]) {
                        holder.stadus.setText("加关注");
                        holder.stadus.setOnClickListener(this);
                        holder.stadus.setTag(i);
                        continue;
                    }
                    holder.stadus.setText("已关注");
                    holder.stadus.setClickable(false);
                    holder.stadus.setFocusable(false);
                }
            }else{
                holder.stadus.setText("加关注");
                holder.stadus.setOnClickListener(this);
                holder.stadus.setTag(i);
            }
        } else {
//            if (list.get(i).isStadus()) {
//                holder.stadus.setText("互相关注");
//            } else {
//                holder.stadus.setText("关注中");
//            }
        }
        return view;
    }

    public void setInsertAtten(boolean isInsertAtten) {
        this.isInsertAtten = isInsertAtten;
    }

    public int[] getPositions() {
        return positions;
    }

    @Override
    public void onClick(View view) {
        int i = (int) view.getTag();
        TextView view1 = (TextView) view;
        if (isAtten[i]) {
            view1.setText("加关注");
            isAtten[i] = false;
        } else {
            view1.setText("已关注");
            positions[i] = list.get(i).getId();
            isAtten[i] = true;
        }

    }

    public void notifyData(List<UserBean> list) {
        this.list = list;
        positions = new int[this.list.size()];
        isAtten=new boolean[this.list.size()];
        notifyDataSetChanged();
    }

    private class ViewHolder {
        private ImageView head;     //头像
        private TextView name;      //昵称
        private TextView callName;  //称号
        private TextView stadus;    //状态
    }

}
