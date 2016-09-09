package plz.com.singbar.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.view.info.FocusitemInfo;

/**
 * Created by Administrator on 2016/8/29.
 */
public class HomepageAdapter extends BaseAdapter {
    private List<FocusitemInfo> list;
    private Context context;
    private LayoutInflater inflater;

    public HomepageAdapter(List<FocusitemInfo> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
        ViewHold hold;
        if (view == null) {
            view = inflater.inflate(R.layout.itme_homepage, null);
            hold = new ViewHold();
            hold.head = (ImageView) view.findViewById(R.id.iv_focus_image);
            hold.name = (TextView) view.findViewById(R.id.tv_focus_name);
            hold.singname = (TextView) view.findViewById(R.id.tv_focus_singname);
            hold.time = (TextView) view.findViewById(R.id.tv_focus_time);
            hold.flower = (TextView) view.findViewById(R.id.tv_focus_flower);
            hold.comment = (TextView) view.findViewById(R.id.tv_focus_comment);
            hold.singnum = (TextView) view.findViewById(R.id.tv_focus_singnum);
            view.setTag(hold);
        }
        hold = (ViewHold) view.getTag();
        hold.name.setText(list.get(i).getName());
        hold.singname.setText(list.get(i).getSingname());
        hold.time.setText(list.get(i).getTime());
        hold.singnum.setText(list.get(i).getSingnum() + "");
        hold.comment.setText(list.get(i).getComment() + "");
        hold.flower.setText(list.get(i).getFlower() + "");
        return view;
    }

    private class ViewHold {
        ImageView head;
        TextView name;
        TextView singname;
        TextView time;
        TextView comment;
        TextView flower;
        TextView singnum;
    }
}
