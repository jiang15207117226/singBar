package plz.com.singbar.operation;

import android.content.ContentValues;
import android.util.Log;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import plz.com.singbar.bean.AttenBean;
import plz.com.singbar.bean.UserBean;
import plz.com.singbar.bean.UserDetailBean;
import plz.com.singbar.bean.UserOwnSongsBean;

/**
 * Created by Administrator on 2016/8/29.
 */
public class DbOperation {

    public static List<UserBean> query(String account) {
        return DataSupport.where("account = ?", account).find(UserBean.class);
    }

    public static List<UserBean> queryByPetName(String petName) {
        return DataSupport.where("petName = ?", petName).find(UserBean.class);
    }

    public static UserDetailBean queryDetail(int id) {
        List<UserDetailBean> list = DataSupport.where("userbean_id = ?", id + "").find(UserDetailBean.class);
        if (list == null || list.size() < 1) {
            return null;
        }
        return list.get(0);
    }

    public static UserBean queryById(int id) {
        return DataSupport.find(UserBean.class, id);
    }

    public static int querySongsId(String songName) {
        return DataSupport.where("songName = ?", songName).find(UserOwnSongsBean.class).get(0).getId();
    }

    public static List<AttenBean> queryByUserId(int user_id) {
        List<AttenBean> list = DataSupport.where("userid = ?", user_id + "").find(AttenBean.class);
        if (list == null || list.size() < 1) {
            return null;
        }
        return list;
    }

    public static void updateSongsUserId(int user_id, int id) {
        ContentValues values = new ContentValues();
        values.put("userbean_id", user_id);
        DataSupport.update(UserOwnSongsBean.class, values, id);
    }

    public static void updateAttenUserId(int user_id, int id) {
        ContentValues values = new ContentValues();
        values.put("userId", user_id);
        values.put("userbean_id", user_id);
        DataSupport.update(AttenBean.class, values, id);
    }

    public static void updateUserAttenCount(int count, int id) {
        ContentValues value = new ContentValues();
        value.put("attentionCount", count);
        DataSupport.update(UserBean.class, value, id);
    }

    public static int findLastBeanId() {
        UserBean bean = DataSupport.findLast(UserBean.class);
        if (bean != null) {
            return bean.getId();
        }
        return 0;
    }

    public static int findTargetAttenBeanId(int id) {
        List<AttenBean> list = DataSupport.where("id = ?", id + "").find(AttenBean.class);
        return list == null ? 0 : list.get(0).getId();
    }

    public static int queryPhone(long phoneNumber) {
        List<UserBean> list = DataSupport.where("phone = ?", phoneNumber + "").find(UserBean.class);
        if (list != null && list.size() >= 1) {
            Log.i("result", "queryPhone-->" + list.size());
            return list.size();
        }
        Log.i("result", "queryPhone-->" + list.size());
        return 0;
    }

    public static void insertData(int i) {
        UserBean bean = new UserBean();
        UserDetailBean userDetailBean = new UserDetailBean();
        String account=GenerMbAccount.generMbAccount();
        bean.setAccount(account);
        bean.setPw("123456");
        bean.setPhone(Long.parseLong("132" + (int) ((Math.random() * 9 + 1) * 10000000)));
        bean.setPetName("plz" + Integer.parseInt(account.substring(2)));
        if (i % 10 == 0) {
            bean.setButility("歌唱达人");
        } else if (i % 8 == 0) {
            bean.setButility("超级明星");
        } else {
            bean.setButility("歌唱新秀");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        if (i % 10 == 0) {
            date.setMonth(5);
        }
        if (i % 7 == 0) {
            date.setMonth(7);
        }
        String time = format.format(date);
        bean.setTime(time);
        bean.setUserDetailBean(userDetailBean);
        bean.save();
        userDetailBean.save();
    }

    public static int[] queryFansByUserId(int id) {
        List<AttenBean> list = DataSupport.select("userid", "id").where("attenuserid = ?", id + "").find(AttenBean.class);
        if (list != null) {
            int[] _ids = new int[list.size()];
            Log.i("result", list.size() + "");
            for (int i = 0; i < list.size(); i++) {
                AttenBean attenBean = list.get(i);
                int _id = attenBean.getUserId();
                Log.i("_id", _id + "-->_id");
                _ids[i]=_id;
            }
            return _ids;
        }
        return null;
    }

    public static List<UserBean> getRandomData(int id) {
        List<UserBean> list = new ArrayList<>();
        int[] positions = new int[10];
        for (int i = 0; i < 10; i++) {
            boolean isExit = false;
            int position = (int) ((Math.random() * 9 + 1) * 5);
            Log.i("list", position + "-->position");
            if (position == id) {
                break;
            }
            for (int j = 0; j < positions.length; j++) {
                if (positions[j] != position) {
                    continue;
                }
                isExit = true;
            }
            if (!isExit) {
                positions[i] = position;
                List<UserBean> list1 = DbOperation.queryPartById(position);
                UserBean bean = list1.get(0);
                list.add(bean);
            }
        }
        return list;
    }

    public static List<UserBean> queryPartById(int id) {
        return DataSupport.select("petName", "butility", "head").where("id = ?", id + "").find(UserBean.class);
    }
}
