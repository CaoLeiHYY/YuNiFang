package com.nababy.caokexin.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nababy.caokexin.R;
import com.nababy.caokexin.activity.ZhuActivity;

/**
 * 类的用途
 *
 * @author 曹可新
 * @time Date
 */
public class GouWu extends Fragment {

    private View view;
    String url = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=83560&encode=3108ed0b9a42c1e160b2912a78692263&category_id=9%E6%8E%A7%E6%B2%B9%E7%A5%9B%E7%97%98";
    GouWuChengGong gouWuChengGong = new GouWuChengGong();
    GouWuShiBai gouWuShiBai = new GouWuShiBai();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.gouwu_tihuan,null);
        initView();
        return view;
    }

    private void initView() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.tihuan_frame, gouWuChengGong,"chenggong");
        transaction.add(R.id.tihuan_frame, gouWuShiBai,"fenlei");
        transaction.hide(gouWuChengGong);
        transaction.hide(gouWuShiBai);
        transaction.commit();
        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        boolean flag = ZhuActivity.flag;
        Log.e("曹可新---》",""+flag);
        if (flag){
            showAndHide(gouWuChengGong,gouWuShiBai);
        }else {
            showAndHide(gouWuShiBai,gouWuChengGong);
        }
    }
    public void showAndHide(Fragment fragment1, Fragment fragment2){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.show(fragment1);
        transaction.hide(fragment2);
        transaction.commit();
    }
}
