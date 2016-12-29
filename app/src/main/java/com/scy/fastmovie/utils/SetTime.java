package com.scy.fastmovie.utils;

import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by SCY on 2016/12/29 13:09.
 */

public class SetTime {
    /**
     * 设置发表时间
     *
     * @param sendTime 发表时间
     * @return
     */
    public static void timeLogic(String sendTime, TextView textView) {
        Calendar calendar = Calendar.getInstance();
        long now = calendar.getTimeInMillis();
        //发表时间
        long past = Long.parseLong(sendTime);

        // 相差的秒数  
        long time = (now - past) / 1000;

        if (time > 0 && time < 60) { // 1小时内 
            textView.setText(time + "秒前");
        } else if (time > 60 && time < 3600) {
            textView.setText(time / 60 + "分钟前");
        } else if (time >= 3600 && time < 3600 * 24) {
            textView.setText(time / 3600 + "小时前");
        } else if (time >= 3600 * 24 && time < 3600 * 48) {
            textView.setText("昨天");
        } else if (time >= 3600 * 48 && time < 3600 * 72) {
            textView.setText("前天");
        } else if (time >= 3600 * 72) {
            String time1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss")
                    .format(new Date(past));
            textView.setText(time1);
        }
    }
}
