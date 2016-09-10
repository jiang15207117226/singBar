package plz.com.singbar.view.activity;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Administrator on 2016/9/7.
 */
class MusicFilter implements FilenameFilter {
    @Override
    public boolean accept(File file, String s) {
        return (s.endsWith(".amr"));
    }
}
