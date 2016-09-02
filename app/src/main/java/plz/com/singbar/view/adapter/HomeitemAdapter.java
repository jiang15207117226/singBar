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
import plz.com.singbar.view.info.HomeitemInfo;


/**
 * Created by Administrator on 2016/8/29.
 */
public class HomeitemAdapter extends BaseAdapter {
    private List<HomeitemInfo> list;
    private Context context;
    private LayoutInflater inflater;

    public HomeitemAdapter(List<HomeitemInfo> list, Context context) {
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
            view = inflater.inflate(R.layout.item_home, null);
            hold = new ViewHold();
            hold.title = (TextView) view.findViewById(R.id.tv_home_title);
            hold.time = (TextView) view.findViewById(R.id.tv_home_time);
            hold.image = (ImageView) view.findViewById(R.id.iv_home_pic);
            hold.content = (TextView) view.findViewById(R.id.tv_home_content);
            view.setTag(hold);
        }
        hold = (ViewHold) view.getTag();
        hold.title.setText(list.get(i).getTitle());
        hold.time.setText(list.get(i).getTime());
        hold.content.setText(list.get(i).getContent());
        return view;
    }

    private class ViewHold {
        TextView title;
        TextView time;
        TextView content;
        ImageView image;


    }
}
