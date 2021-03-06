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
 * Created by Administrator on 2016/8/29.
 */
public class FocusitemAdapter extends BaseAdapter {
    private List<UserOwnSongsBean> list;
    private List<UserBean> beanList;
    private Context context;
    private LayoutInflater inflater;

    public FocusitemAdapter(List<UserOwnSongsBean> list,List<UserBean> beanList, Context context) {
        this.list = list;
        this.beanList=beanList;
        this.context = context;
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
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder hold;
        if (view==null){
            view=inflater.inflate(R.layout.item_focus,null);
            hold=new ViewHolder();
            hold.head= (ImageView) view.findViewById(R.id.iv_focus_image);
            hold.name= (TextView) view.findViewById(R.id.tv_focus_name);
            hold.singname= (TextView) view.findViewById(R.id.tv_focus_singname);
            hold.time= (TextView) view.findViewById(R.id.tv_focus_time);
            hold.flower= (TextView) view.findViewById(R.id.tv_focus_flower);
            hold.comment= (TextView) view.findViewById(R.id.tv_focus_comment);
            hold.singnum= (TextView) view.findViewById(R.id.tv_focus_singnum);
            hold.contents= (TextView) view.findViewById(R.id.tv_item_source);
            view.setTag(hold);
        }
        hold= (ViewHolder) view.getTag();
        hold.name.setText(beanList.get(i).getPetName());
        hold.singname.setText(list.get(i).getSongName());
        hold.time.setText(list.get(i).getTime());
        hold.singnum.setText(list.get(i).getTrys()+"");
        hold.comment.setText(list.get(i).getComments()+"");
        hold.flower.setText(list.get(i).getFlowers()+"");
        hold.contents.setText("...来听听我唱的《"+list.get(i).getSongName()+"》");
        Picasso.with(context).load("file://"+beanList.get(i).getHead()).transform(new CircleTrans()).resize(80,80).placeholder(R.mipmap.health_guide_woman_selected).centerCrop().into(hold.head);
        return view;
    }
    public void notifyData(List<UserOwnSongsBean> list){
        this.list=list;
        notifyDataSetChanged();
    }
    private class ViewHolder{
        ImageView head;
        TextView name;
        TextView singname;
        TextView time;
        TextView comment;
        TextView flower;
        TextView singnum;
        TextView contents;
    }
}
