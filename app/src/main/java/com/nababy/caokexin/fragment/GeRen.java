package com.nababy.caokexin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nababy.caokexin.R;

/**
 * 类的用途
 *
 * @author 曹可新
 * @time Date
 */
public class GeRen extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.geren_layout,null);
        return view;
    }
}
