package com.nababy.caokexin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nababy.caokexin.R;
import com.nababy.caokexin.activity.DengLuctivity;

/**
 * 类的用途
 *
 * @author 曹可新
 * @time Date
 */
public class GeRen extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView geren_login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.geren_layout,null);
        initView();
        return view;
    }

    private void initView() {
        geren_login = (ImageView) view.findViewById(R.id.geren_login);
        geren_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.geren_login:
                Intent intent = new Intent(getActivity(), DengLuctivity.class);
                startActivity(intent);
                break;
        }
    }
}
