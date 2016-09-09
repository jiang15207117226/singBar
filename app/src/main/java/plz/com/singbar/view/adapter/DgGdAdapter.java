package plz.com.singbar.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import plz.com.singbar.R;
import plz.com.singbar.view.info.DgGdInfo;


/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class DgGdAdapter extends BaseAdapter{
    private List<DgGdInfo> list;
    private Context context;
    private LayoutInflater inflater;
    private int ii;
    public DgGdAdapter( Context context,List<DgGdInfo> list,int ii){
        this.context=context;
        this.list=list;
        this.ii=ii;
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view=inflater.inflate(R.layout.item_dg_gd, null);
            viewHolder=new ViewHolder();
            viewHolder.ima= (ImageView) view.findViewById(R.id.iv_dg_gd);
            viewHolder.tv= (TextView) view.findViewById(R.id.tv_dg_gd);
            view.setTag(viewHolder);
        }

        viewHolder= (ViewHolder) view.getTag();
        viewHolder.tv.setText(list.get(i).getText());
        final ViewHolder finalViewHolder = viewHolder;
        Transformation transformation = new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = finalViewHolder.ima.getWidth();

                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };


        Picasso.with(context).load(list.get(i).getIma()).fit().into(viewHolder.ima);
        return view;
    }
    class ViewHolder{
        private ImageView ima;
        private TextView tv;
    }

}
