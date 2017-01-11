package com.scy.fastmovie.db;

import android.content.Context;

import com.scy.fastmovie.bean.DaoMaster;
import com.scy.fastmovie.bean.DaoSession;
import com.scy.fastmovie.bean.MovieDaoBeanDao;

/**
 * Created by TimiZhuo on 2017/1/10.
 */
public class MovieDbManager {

    public static MovieDaoBeanDao getDao(Context context){
        DaoSession session= DaoMaster.newDevSession(context,"newmovie");
        return session.getMovieDaoBeanDao();
    }
}
