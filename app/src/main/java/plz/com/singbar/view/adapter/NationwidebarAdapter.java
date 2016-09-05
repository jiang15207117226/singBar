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
import plz.com.singbar.view.info.NationwidebarInfo;

/**
 * Created by Administrator on 2016/9/1.
 */
public class NationwidebarAdapter extends BaseAdapter {


    private List<NationwidebarInfo> list;
    private Context context;
    private LayoutInflater inflater;

    public NationwidebarAdapter(List<NationwidebarInfo> list, Context context) {
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
            view = inflater.inflate(R.layout.itme_nationwiebar, null);
            hold = new ViewHold();

            hold.goldMedal = (ImageView) view.findViewById(R.id.itme_nation_Medal);
            hold.silverMedal = (ImageView) view.findViewById(R.id.itme_nation_Medal);
            hold.coppermedal = (ImageView) view.findViewById(R.id.itme_nation_Medal);
            hold.headPortraits = (ImageView) view.findViewById(R.id.item_homepage_image);
            hold.userName = (TextView) view.findViewById(R.id.text_itme_one);
            hold.singname = (TextView) view.findViewById(R.id.text_itme_two);
            hold.audition = (TextView) view.findViewById(R.id.itme_nation_shiting);
            view.setTag(hold);
        }

        hold = (ViewHold) view.getTag();
        hold.userName.setText(list.get(i).getUserName());
        hold.singname.setText(list.get(i).getSingname());
        hold.audition.setText(list.get(i).getAudition() + "");
        return view;
    }

    private class ViewHold {
        ImageView goldMedal;
        ImageView silverMedal;
        ImageView coppermedal;
        ImageView headPortraits;
        TextView userName;
        TextView singname;
        TextView audition;

    }
}
