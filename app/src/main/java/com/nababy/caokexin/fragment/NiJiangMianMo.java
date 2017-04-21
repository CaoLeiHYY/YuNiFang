package com.nababy.caokexin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nababy.caokexin.R;
import com.nababy.caokexin.activity.GoodsActivity;
import com.nababy.caokexin.adapter.ZhuanTiAdapter;
import com.nababy.caokexin.bean.FirstBean;

import cz.msebera.android.httpclient.Header;

/**
 * 类的用途
 *
 * @author 曹可新
 * @time Date
 */
public class NiJiangMianMo extends Fragment {

    private View view;
    GridView mianmo_grid;
    private String url = "http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447";
    private Gson gson;
    private FirstBean bean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mianmo_layout,null);
        initView();
        return view;
    }

    private void initView() {
        mianmo_grid = (GridView) view.findViewById(R.id.mianmo_grid);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getActivity(), url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                gson = new Gson();
                bean = gson.fromJson(responseString, FirstBean.class);
                ZhuanTiAdapter adapter = new ZhuanTiAdapter(bean.getData().getSubjects().get(6).getGoodsList(), getActivity());
                mianmo_grid.setAdapter(adapter);
                mianmo_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String json = gson.toJson(bean.getData().getSubjects().get(6).getGoodsList().get(position));
                        Intent intent = new Intent(getActivity(), GoodsActivity.class);
                        intent.putExtra("json",json);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
