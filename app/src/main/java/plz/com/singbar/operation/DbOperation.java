package plz.com.singbar.operation;

import org.litepal.crud.DataSupport;

import java.util.List;

import plz.com.singbar.bean.UserBean;

/**
 * Created by Administrator on 2016/8/29.
 */
public class DbOperation {
    public static List<UserBean> query(String content) {
        return DataSupport.where("account = ?", content).find(UserBean.class);
    }
}
