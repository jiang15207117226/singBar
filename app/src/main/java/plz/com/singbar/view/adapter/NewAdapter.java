package plz.com.singbar.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.view.info.NewSongInfo;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class NewAdapter extends BaseAdapter{
    private NewSongInfo info;
    private Context context;
    private LayoutInflater inflater;
    private List<NewSongInfo.SongBean> list;
    public NewAdapter(Context context,NewSongInfo info){
        this.context=context;
        this.info=info;
        list = info.getSong();
        inflater=LayoutInflater.from(context);
    }
    public void datachange(NewSongInfo info){
        this.info=info;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return info==null?0:info.getSong().size();
    }

    @Override
    public Object getItem(int i) {
        return info.getSong().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view=inflater.inflate(R.layout.time_songsets,null);
            viewHolder=new ViewHolder();
            viewHolder.singername= (TextView) view.findViewById(R.id.tv_dg_name);
            viewHolder.singname= (TextView) view.findViewById(R.id.tv_dg_gname);
            viewHolder.daxiao= (TextView) view.findViewById(R.id.tv_dg_daxiao);
            viewHolder.iv= (ImageView) view.findViewById(R.id.iv_dg_head);
            viewHolder.kge= (Button) view.findViewById(R.id.btn_dg_kg);   //K歌按钮
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();
        viewHolder.singname.setText(list.get(i).getSongname());
        viewHolder.singername.setText(list.get(i).getSinger());
    /*    double b=(info.getSingInfoo().get(i).getFilesize())/(1024*1024);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float vv=Float.parseFloat(decimalFormat.format(b));
        viewHolder.daxiao.setText(vv+"M-");*/
        String uri=info.getSong().get(i).getIma();
        if(uri!=null&&uri.length()>=1){

            Picasso.with(context).load(uri).placeholder(R.mipmap.health_guide_men_selected).into(viewHolder.iv);
        }


        viewHolder.kge.setTag(i);
        return view;
    }
    class ViewHolder{
        private ImageView iv;
        private TextView singname;
        private TextView daxiao;
        private TextView singername;
        private Button kge;
    }
}
