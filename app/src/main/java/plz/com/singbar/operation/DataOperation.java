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
    @Override
    protected List<NoteBean> doInBackground(String... urls) {
        Log.i("result", "doInBackground");
//        String url = "http://changba.com/commonreport/testsrc/view2.php?id=899&msgid=899&curuserid=155553520&curuserid=155553520&code=Gt1bjDM0qnEZ4aFQeA8nF98Et52lvNZxS42el5XvYuKPpYCJ35x7XY9xHX6ZtjJhG27dKQO0DoVTpnKLpp_xxqm-UMdU9u3YXosRAVlEVu_0x0OJjGuS_A";
        List<NoteBean>list=new ArrayList<>();
        for (String url:urls){
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
                    if (content.length()<50) {
                        String con = e.text();
                        if (con != null && con.length() >= 1) {
                            content += con;
                        }
                    } else {
                        Log.i("result",content);
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
        Log.i("result","onPostExecute");
    }
}
