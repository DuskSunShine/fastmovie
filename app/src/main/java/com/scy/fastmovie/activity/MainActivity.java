package com.scy.fastmovie.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import java.util.List;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.List;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiAddrInfo;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.scy.fastmovie.R;
import com.scy.fastmovie.fragment.CinemaFragment;
import com.scy.fastmovie.fragment.DiscoverFragment;
import com.scy.fastmovie.fragment.MineFragment;
import com.scy.fastmovie.fragment.MovieFragment;
import com.scy.fastmovie.interfaces.DataCallBack;
import com.scy.fastmovie.interfaces.ShuJu;
import com.scy.fastmovie.utils.NetWorkUtils;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements BDLocationListener{





    private RadioGroup rgb_bottom;
    private RadioButton rb1;
    private FragmentManager fragmentManager;
    private MovieFragment fragment_movie;
    private CinemaFragment fragment_cinema;
    private DiscoverFragment fragment_discover;
    private MineFragment fragment_mine;
    private MapView baiduMap;
    private LocationClient locationClient;
    double lat,lng;
    long exitTime=0;
    int flag=0;
    private PoiSearch poiSearch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShuJu.activitys.add(this);
        if (Build.VERSION.SDK_INT>=21){
            getWindow().setStatusBarColor(Color.argb(200,63,81,181));
        }
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        this.fragment_movie = new MovieFragment();
        transaction.add(R.id.layout, this.fragment_movie);
        this.fragment_cinema = new CinemaFragment();
        transaction.add(R.id.layout, this.fragment_cinema);
        this.fragment_discover = new DiscoverFragment();
        transaction.add(R.id.layout, this.fragment_discover);
        this.fragment_mine = new MineFragment();
        transaction.add(R.id.layout, this.fragment_mine);
        transaction.show(this.fragment_movie);
        transaction.hide(this.fragment_cinema);
        transaction.hide(this.fragment_discover);
        transaction.hide(this.fragment_mine);
        transaction.commit();
        initViews();
        setClickListener();
        initLocation();
        if (NetWorkUtils.isConnect(this)){
            locationClient.start();
        }
        useViews();
        poiSearch=PoiSearch.newInstance();
        OnGetPoiSearchResultListener poiSearchResultListener=new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult!=null) {
                    List<PoiInfo> allPoi = poiResult.getAllPoi();
                    if (poiResult.isHasAddrInfo()) {
                        List<PoiAddrInfo> allAddr = poiResult.getAllAddr();
                        Log.e("===onGetPoiResult===", "***" +allAddr );
                    }
                    int totalPageNum = poiResult.getTotalPageNum();

                    Log.e("===onGetPoiResult===", "***" + allPoi);

                    Log.e("===onGetPoiResult===", "***" + totalPageNum);
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                if (poiDetailResult!=null) {
                    String address = poiDetailResult.getAddress();
                    int i = poiDetailResult.describeContents();
                    String detailUrl = poiDetailResult.getDetailUrl();
                    String name = poiDetailResult.getName();
                    double price = poiDetailResult.getPrice();
                    Log.e("===onGetPoiDetailResult", "***" + address);
                    Log.e("===onGetPoiDetailResult", "***" + i);
                    Log.e("===onGetPoiDetailResult", "***" + detailUrl);
                    Log.e("===onGetPoiDetailResult", "***" + name);
                    Log.e("===onGetPoiDetailResult", "***" + price);
                }
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        };
        poiSearch.setOnGetPoiSearchResultListener(poiSearchResultListener);
        poiSearch.searchNearby(new PoiNearbySearchOption()
        .keyword("餐饮").location(new LatLng(lat,lng))
                .radius(5000)
        .pageNum(10));

    }

    private void setClickListener() {
        rgb_bottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i <rgb_bottom.getChildCount() ; i++) {
                    RadioButton rb = (RadioButton) rgb_bottom.getChildAt(i);
                    if (rb.isChecked()){
                        switch (i){
                            case 0:
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.show(fragment_movie);
                                transaction.hide(fragment_mine);
                                transaction.hide(fragment_discover);
                                transaction.hide(fragment_cinema);
                                transaction.commit();
                                break;
                            case 1:
                                FragmentTransaction transaction2 = fragmentManager.beginTransaction();
                                transaction2.hide(fragment_movie);
                                transaction2.hide(fragment_mine);
                                transaction2.hide(fragment_discover);
                                transaction2.show(fragment_cinema);
                                transaction2.commit();
                                break;
                            case 2:
                                FragmentTransaction transaction3 = fragmentManager.beginTransaction();
                                transaction3.hide(fragment_movie);
                                transaction3.hide(fragment_mine);
                                transaction3.show(fragment_discover);
                                transaction3.hide(fragment_cinema);
                                transaction3.commit();
                                break;
                            case 3:
                                FragmentTransaction transaction4 = fragmentManager.beginTransaction();
                                transaction4.hide(fragment_movie);
                                transaction4.show(fragment_mine);
                                transaction4.hide(fragment_discover);
                                transaction4.hide(fragment_cinema);
                                transaction4.commit();
                                break;
                        }
                    }
                }
            }
        });
        locationClient.registerLocationListener(this);
    }

    private void useViews() {
        rb1.setChecked(true);
    }

    private void initViews() {
        rgb_bottom = (RadioGroup) findViewById(R.id.rgb_bottom);
        rb1 = (RadioButton) findViewById(R.id.rb_1);
        baiduMap = ((MapView)findViewById(R.id.baiduMap));
        locationClient = new LocationClient(getApplicationContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        baiduMap.onDestroy();
        poiSearch.destroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        baiduMap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        baiduMap.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis()-exitTime>2000){
                Toast.makeText(MainActivity.this, "再次点击退出", Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }else {
                for (AppCompatActivity str:ShuJu.activitys){
                    str.finish();
                }
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation.getLocType()==61||bdLocation.getLocType()==161){
            double lat=bdLocation.getLatitude();
            double lng=bdLocation.getLongitude();
            this.lat=lat;
            this.lng=lng;
            String city = bdLocation.getCity();
            Log.e("city",city+lat+lng);
            flag++;
            if (flag==1){
                Toast.makeText(MainActivity.this, "定位成功。。。", Toast.LENGTH_SHORT).show();
                ((DataCallBack)(fragment_movie)).getDataCallBack(city);
            }
//            cityCode=bdLocation.getBuildingID();
//            Toast.makeText(MainActivity.this,cityCode,Toast.LENGTH_LONG).show();
        }
    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=5000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        locationClient.setLocOption(option);
    }

}
