package plz.com.singbar.operation;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

/**
 * Created by Administrator on 2016/9/1.
 */
public class CircleTrans implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();//回收垃圾
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap,
                BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);//定义一个渲染器
        paint.setShader(shader);//设置渲染器
        paint.setAntiAlias(true);

        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);//绘制图形

        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }

    //缩放
    private Bitmap zoomBitmap(Bitmap map, int width, int height) {
        int w = map.getWidth();
        int h = map.getHeight();
        float scaleW = (float) width / w;
        float scaleH = (float) height / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleW, scaleH);
        return Bitmap.createBitmap(map, 0, 0, w, h, matrix, true);
    }
}
