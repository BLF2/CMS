package net.blf2.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by blf2 on 16-3-31.
 * 格式化时间类
 */
public class DateFormat {
    public String getCurrentDateTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date());
    }
}
