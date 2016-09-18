package plz.com.singbar.view.info;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class BdSingInfo implements Serializable {
    private String name;
    private String sname;
    private Long dx;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getDx() {
        return dx;
    }

    public void setDx(Long dx) {
        this.dx = dx;
    }

    public String getSname() {

        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }



    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    private String uri;
}
