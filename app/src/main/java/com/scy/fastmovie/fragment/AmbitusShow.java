package com.scy.fastmovie.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scy.fastmovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmbitusShow extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ambitus_show,container,false);
        return view;
    }

}
