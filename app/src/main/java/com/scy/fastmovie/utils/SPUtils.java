package com.scy.fastmovie.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by TimiZhuo on 2017/1/11.
 *
 */
public class SPUtils {
    public static SharedPreferences getSp(Context context){
        return context.getSharedPreferences("timi",Context.MODE_PRIVATE);
    }
}
