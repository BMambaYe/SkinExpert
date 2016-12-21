package com.zhanghao.skinexpert.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhanghao.skinexpert.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {


    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_me,null);
        return view;
    }

}
