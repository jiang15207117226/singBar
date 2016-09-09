package plz.com.singbar.operation;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import plz.com.singbar.bean.NoteBean;

/**
 * Created by Administrator on 2016/9/1.
 */
public class DataOperation extends AsyncTask<String, Void, List<NoteBean>> {
    private OnPostFinish onPostFinish;
    @Override
    protected void onPreExecute() {
        onPostFinish.onPre();
    }

    @Override
    protected List<NoteBean> doInBackground(String... urls) {
        Log.i("result", "doInBackground");
        List<NoteBean> list = new ArrayList<>();
        for (String url : urls) {
            try {
                Document document = Jsoup.connect(url).get();
                Element el = document.select(".article-top").first();//解析得到第一个class=“article-top”标签
                String title = el.select(".article-title").text();
                Log.i("result", "doInBackground: title --  " + title);
                String imgUrl = el.select(".article-img").attr("src");
                Log.i("result", "doInBackground: img -- " + imgUrl);
                Element ele = document.select(".article").first();
                Elements es = ele.select("span");
                String content = "";
                for (Element e : es) {
                    if (content.length() < 50) {
                        String con = e.text();
                        if (con != null && con.length() >= 1) {
                            content += con;
                        }
                    } else {
                        Log.i("result", content);
                        break;
                    }

                }
                NoteBean noteBean = new NoteBean();
                noteBean.setTitle(title);
                noteBean.setUrl(url);
                noteBean.setImgUrl(imgUrl);
                noteBean.setFirstLine(content);
                list.add(noteBean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<NoteBean> noteBeen) {
        Log.i("result", "onPostExecute");
        onPostFinish.onPostFinish(noteBeen);
    }



    public void setOnPostFinish(OnPostFinish onPostFinish) {
        this.onPostFinish = onPostFinish;
    }

    public interface OnPostFinish {
        void onPostFinish(List<NoteBean> noteBeen);
        void onPre();
    }
}