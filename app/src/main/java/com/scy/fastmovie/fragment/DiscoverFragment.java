package com.scy.fastmovie.fragment;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.scy.fastmovie.R;
import com.scy.fastmovie.activity.MainActivity;
import com.scy.fastmovie.activity.SearchNewsResultActivity;
import com.scy.fastmovie.adapter.DiscoverAdapter;
import com.scy.fastmovie.bean.DiscoverBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现碎片
 */
public class DiscoverFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2{
    
    private Toolbar discover_toolbar;
    private View view;
    private View head;
    private Button search_edit,btn_top,btn_kuaixun,btn_piaofang;
    private PullToRefreshListView search_list;
    private MainActivity mainActivity;
    private DiscoverAdapter discoverAdapter;
    private List<DiscoverBean.DataBean.FeedsBean> data=new ArrayList<>();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_discover, container, false);
        head=inflater.inflate(R.layout.discover_item_head,container,false);

        //fragment设置toolbar
        mainActivity.setSupportActionBar(discover_toolbar);
        //添加ListView头
        search_list.getRefreshableView().addHeaderView(head);
        initViews();
        setListener();
        search_list.setMode(PullToRefreshBase.Mode.BOTH);
        search_list.setOnRefreshListener(this);
        
        discoverAdapter=new DiscoverAdapter(mainActivity);
        search_list.setAdapter(discoverAdapter);
        
        search_list.setRefreshing();
        
        return view;
    }

    /**
     * 初始化布局
     */
    private void initViews(){
        discover_toolbar= (Toolbar) view.findViewById(R.id.discover_toolbar);
        search_edit= (Button) view.findViewById(R.id.search_edit);
        search_list= (PullToRefreshListView) view.findViewById(R.id.search_list);
        btn_top= (Button) head.findViewById(R.id.btn_top);
        btn_kuaixun= (Button) head.findViewById(R.id.btn_kuaixun);
        btn_piaofang= (Button) head.findViewById(R.id.btn_piaofang);
    }

    /**
     * 控件监听
     */
    private void setListener(){
        search_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchNewsResultActivity.class));
            }
        });
        
    }

    /**
     * 加载数据
     */
    private void initData(){
        
    }
    //下拉刷新
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        
    }
    //上拉加载
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}
