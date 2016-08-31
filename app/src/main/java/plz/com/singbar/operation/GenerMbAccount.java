package plz.com.singbar.operation;

/**
 * Created by Administrator on 2016/8/31.
 */
public class GenerMbAccount {
    public static String generMbAccount() {
        int id = DbOperation.findLastBeanId()+1;
        if (id < 1000000) {
            if (id < 100000) {
                if (id < 10000) {
                    if (id < 1000) {
                        if (id < 100) {
                            if (id < 10) {
                                return "mb00000" + id;
                            }
                            return "mb0000" + id;
                        }
                        return "mb000" + id;
                    }
                    return "mb00" + id;
                }
                return "mb0" + id;
            }
            return "mb" + id;
        }
        return null;
    }
}
