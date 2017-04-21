package com.nababy.caokexin.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nababy.caokexin.R;
import com.nababy.caokexin.bean.FirstBean;

import org.xutils.x;

public class GoodsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ImageView shop_pic = (ImageView) findViewById(R.id.shop_pic);
        TextView shop_name = (TextView) findViewById(R.id.shop_name);
        TextView shop_money = (TextView) findViewById(R.id.shop_money);
        Intent intent = getIntent();
        String json = intent.getStringExtra("json");
        Gson gson = new Gson();
        FirstBean.DataBean.SubjectsBean.GoodsListBean bean1 = gson.fromJson(json, FirstBean.DataBean.SubjectsBean.GoodsListBean.class);
        x.image().bind(shop_pic,bean1.getGoods_img());
        shop_name.setText(bean1.getGoods_name());
        shop_money.setText("￥"+bean1.getShop_price());
    }
}
