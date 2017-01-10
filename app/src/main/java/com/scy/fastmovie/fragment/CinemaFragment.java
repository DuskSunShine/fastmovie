package com.scy.fastmovie.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scy.fastmovie.R;

/**
 * 影院
 */
public class CinemaFragment extends Fragment {
    private Context context;
    private View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_cinema, container, false);
        
        
        return view;
    }
}
