package com.scy.fastmovie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.scy.fastmovie.R;
import com.scy.fastmovie.adapter.CinemaResultAdapter;
import com.scy.fastmovie.baseurl.BaseUrl;
import com.scy.fastmovie.bean.SearchCinemaBean;
import com.scy.fastmovie.bean.SearchResultBean;
import com.scy.fastmovie.httpapiservice.HttpApiService;
import com.scy.fastmovie.interfaces.ShuJu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.scy.fastmovie.R.id.search_edit_result;

public class SearchCinemaActivity extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener2{
    private Toolbar cinema_toolbar;
    private EditText search_cinema_result;
    private Button btn_cancel1;
    private PullToRefreshListView search_cinema_list;
    private List<SearchCinemaBean.DataBean> data=new ArrayList<>();
    private int pageNo=0;
    private CinemaResultAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cinema);
        ShuJu.activitys.add(this);
        initViews();
        setSupportActionBar(cinema_toolbar);
        search_cinema_list.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        setListener();
        adapter=new CinemaResultAdapter(this);
        search_cinema_list.setAdapter(adapter);
    }
    /**
     * 初始化布局
     */
    private void initViews(){
        cinema_toolbar= (Toolbar) findViewById(R.id.cinema_toolbar);
        search_cinema_result= (EditText) findViewById(R.id.search_cinema_result);
        btn_cancel1= (Button) findViewById(R.id.btn_cancel1);
        search_cinema_list = (PullToRefreshListView) findViewById(R.id.search_cinema_list);
    }
    private void setListener(){
        search_cinema_list.setOnRefreshListener(this);
        btn_cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0,0);
            }
        });
        search_cinema_result.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("===beforeTextChanged==",s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("===onTextChanged==",s.toString());
                if (s.toString()!=null){
                    search_cinema_list.setRefreshing();
                    adapter.setKeyword(s.toString());
                    initData(s.toString(),0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("==afterTextChanged===",s.toString());
                if (s.toString()!=null){
                    data.clear();
                    adapter.setKeyword(s.toString());
                    search_cinema_list.setRefreshing();
                    initData(s.toString(),0);
                }

            }
        });
    }
    private void initData(String keyword,int pageNo){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl.DISCOVERBASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        HttpApiService httpApiService=retrofit.create(HttpApiService.class);
        Observable<SearchCinemaBean> observable=httpApiService.getSearchCinemaData(
                "59",keyword,"1","0","10",pageNo+"","59");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchCinemaBean>() {
                    @Override
                    public void onCompleted() {
                        
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("==onError=",e+"");
                    }

                    @Override
                    public void onNext(SearchCinemaBean searchCinemaBean) {
                        Log.i("====SearchCinemaBean","=="+searchCinemaBean.getData().size());
                        if (searchCinemaBean!=null){
                            List<SearchCinemaBean.DataBean> list = searchCinemaBean.getData();
                            data.addAll(list);
                            adapter.setData(data);
                            search_cinema_list.onRefreshComplete();
                        }
                    }
                });
    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        pageNo+=10;
        initData(search_cinema_result.getText().toString(),pageNo);
    }
}
