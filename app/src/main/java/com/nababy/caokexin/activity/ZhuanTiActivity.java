package com.nababy.caokexin.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nababy.caokexin.R;
import com.nababy.caokexin.adapter.MingXingChanPinAdapter;
import com.nababy.caokexin.adapter.ZhuanTiAdapter;
import com.nababy.caokexin.bean.FirstBean;

import cz.msebera.android.httpclient.Header;

public class ZhuanTiActivity extends AppCompatActivity {

    private String url = "http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447";
    private GridView zhuanti_gridView;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuan_ti);
        Intent intent = getIntent();
        position = intent.getIntExtra("zhi",0);
        initView();
    }

    private void initView() {
        zhuanti_gridView = (GridView) findViewById(R.id.zhuanti_gridView);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(this, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new Gson();
                FirstBean firstBean = gson.fromJson(responseString, FirstBean.class);
                ZhuanTiAdapter adapter = new ZhuanTiAdapter(firstBean.getData().getSubjects().get(position).getGoodsList(),ZhuanTiActivity.this);
                zhuanti_gridView.setAdapter(adapter);
            }
        });
    }
}
