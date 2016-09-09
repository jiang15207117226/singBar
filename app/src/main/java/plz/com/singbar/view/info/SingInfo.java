package plz.com.singbar.view.info;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/5 0005.
 */
public class SingInfo implements Serializable{

    private int current_page;
    private String keyword ;
    private int total_rows;


    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }



    private int total_page;
    private int page_size;
    private  List<SingInfoo> singInfoo=new ArrayList<>();

    public List<SingInfoo> getSingInfoo() {
        return singInfoo;
    }

    public void setSingInfoo(List<SingInfoo> singInfoo) {
        this.singInfoo = singInfoo;
    }
}
