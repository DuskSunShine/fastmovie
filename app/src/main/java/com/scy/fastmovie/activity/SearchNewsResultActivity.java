package com.scy.fastmovie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.scy.fastmovie.R;
import com.scy.fastmovie.adapter.SearchResultAdapter;
import com.scy.fastmovie.baseurl.BaseUrl;
import com.scy.fastmovie.bean.SearchResultBean;
import com.scy.fastmovie.httpapiservice.HttpApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 点击查找资讯跳转后界面
 */
public class SearchNewsResultActivity extends AppCompatActivity implements 
        PullToRefreshBase.OnRefreshListener2,AdapterView.OnItemClickListener{
    private Toolbar result_toolbar;
    private EditText search_edit_result;
    private Button btn_cancel;
    private PullToRefreshListView search_result_list;
    private SearchResultAdapter resultAdapter;
    private int pageNo=0;
    private List<SearchResultBean.DataBean.ListBean> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_news_result);
        initViews();
        setSupportActionBar(result_toolbar);
        search_result_list.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        setListener();
        resultAdapter=new SearchResultAdapter(this);
        search_result_list.setAdapter(resultAdapter);
        
    }
    /**
     * 初始化布局
     */
    private void initViews(){
        result_toolbar= (Toolbar) findViewById(R.id.result_toolbar);
        search_edit_result= (EditText) findViewById(R.id.search_edit_result);
        btn_cancel= (Button) findViewById(R.id.btn_cancel);
        search_result_list = (PullToRefreshListView) findViewById(R.id.search_result_list);
    }

    /**
     * 控件监听
     */
    private void setListener(){
        search_result_list.setOnRefreshListener(this);
        search_result_list.setOnItemClickListener(this);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0,0);
            }
        });
        search_edit_result.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("===beforeTextChanged==",s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("===onTextChanged==",s.toString());
                if (s.toString()!=null){
                    search_result_list.setRefreshing();
                    initData(s.toString(),0);
                }
                
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("==afterTextChanged===",s.toString());
                if (s.toString()!=null){
                    search_result_list.setRefreshing();
                    data.clear();
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
        Observable<SearchResultBean> observable=httpApiService.getSearchResultData(
                "1",keyword,"4","0","true","10",pageNo+"","59"
        );
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchResultBean>() {
                    @Override
                    public void onCompleted() {
                        
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchResultBean searchResultBean) {
                        Log.i("====onnext====","=="+searchResultBean.getData().size());
                        if (searchResultBean!=null){
                            List<SearchResultBean.DataBean.ListBean> list=searchResultBean.getData().get(0).getList();
                            data.addAll(list);
                            resultAdapter.setData(data);
                            resultAdapter.notifyDataSetChanged();
                            search_result_list.onRefreshComplete();
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
        initData(search_edit_result.getText().toString(),pageNo);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this,DiscoverItemActivity.class);
            intent.putExtra("source",data.get(position-1).getSource());
            intent.putExtra("id",data.get(position-1).getId());
            startActivity(intent);
            overridePendingTransition(0,0);
        
    }
}
