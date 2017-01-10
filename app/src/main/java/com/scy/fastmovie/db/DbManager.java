package com.scy.fastmovie.db;

import android.content.Context;

import com.scy.fastmovie.bean.CinemaBean;
import com.scy.fastmovie.bean.CinemaBeanDao;
import com.scy.fastmovie.bean.DaoMaster;
import com.scy.fastmovie.bean.DaoSession;

import java.util.List;

/**
 * Created by SCY on 2017/1/9 17:53.
 */

public class DbManager {
    private CinemaBeanDao cinemaBeanDao;
    private static DbManager dbManager=null;
    public static DbManager getDbManager(Context context){
        if (dbManager==null){
            dbManager=new DbManager(context);
        }
        return dbManager;
    }
    public  DbManager(Context context){
        DaoSession daoSession= DaoMaster.newDevSession(context,"cinema");
        cinemaBeanDao=daoSession.getCinemaBeanDao();
    }
    public void insert(CinemaBean cinemaBean){
        cinemaBeanDao.insertOrReplaceInTx(cinemaBean);
    }
    public void insert(List<CinemaBean> cinemaBeen){
        cinemaBeanDao.insertOrReplaceInTx(cinemaBeen);
    }
    //查询所有
    public List<CinemaBean> queryAll() {
        List<CinemaBean> list = cinemaBeanDao.queryBuilder().list();
        return list;
    }

    //分页查询 0,10,(0-9)  10 ,10(10-19),20,10(20-29)
    //pageNo大于等于1
    public List<CinemaBean> queryByPage(int pageNo, int pageSize) {
        List<CinemaBean> list = cinemaBeanDao.queryBuilder().limit(pageSize)
                .offset((pageNo - 1) * pageSize).list();
        return list;
    }
}
