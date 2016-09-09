package plz.com.singbar.operation;

import android.util.Log;

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
    public static UserBean queryById(int id){
        return DataSupport.find(UserBean.class,id);
    }
    public static int findLastBeanId(){
        UserBean bean=DataSupport.findLast(UserBean.class);
        if (bean!=null){
            return bean.getId();
        }
        return 0;
    }
    public static int queryPhone(long phoneNumber) {
        List<UserBean> list = DataSupport.where("phone = ?", phoneNumber + "").find(UserBean.class);
        if (list != null && list.size() >= 1) {
            Log.i("result","queryPhone-->"+list.size());
            return list.size();
        }
        Log.i("result","queryPhone-->"+list.size());
        return 0;
    }
}
