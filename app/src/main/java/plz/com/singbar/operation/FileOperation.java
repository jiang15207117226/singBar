package plz.com.singbar.operation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/8/29.
 */
public class FileOperation {
    public String read(String path){
        try {
            FileInputStream inputStream=new FileInputStream(path);
            byte[]b=new byte[1024];
            int readed=0;
            StringBuffer buffer=new StringBuffer();
            while((readed=inputStream.read(b))>0){
                buffer.append(new String(b,0,readed));
            }
            inputStream.close();
            return buffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void write(String path,String data){
        if (data==null||data.length()<1){
            return;
        }
        try {
            FileOutputStream outputStream=new FileOutputStream(path,true);
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
