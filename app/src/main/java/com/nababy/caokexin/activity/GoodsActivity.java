package com.nababy.caokexin.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nababy.caokexin.R;
import com.nababy.caokexin.bean.FirstBean;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.xutils.x;

import java.io.IOException;

public class GoodsActivity extends AppCompatActivity {

    private int userID = 67;
    private String name;
    private String pic;
    private float price;
    private FirstBean.DataBean.SubjectsBean.GoodsListBean bean1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ImageView shop_pic = (ImageView) findViewById(R.id.shop_pic);
        TextView shop_name = (TextView) findViewById(R.id.shop_name);
        TextView shop_money = (TextView) findViewById(R.id.shop_money);
        TextView shang_jia = (TextView) findViewById(R.id.shang_jia);
        Intent intent = getIntent();
        String json = intent.getStringExtra("json");
        Gson gson = new Gson();
        bean1 = gson.fromJson(json, FirstBean.DataBean.SubjectsBean.GoodsListBean.class);
        x.image().bind(shop_pic, bean1.getGoods_img());
        shop_name.setText(bean1.getGoods_name());
        shop_money.setText("￥"+ bean1.getShop_price());
        shang_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSuccess();
            }
        });
    }

    public void getSuccess(){
        if(ZhuActivity.flag){
            tianjiachenggong();
        }else{
            tianjiashibai();
        }
    }

    public void tianjiashibai(){
        Intent intent = new Intent(GoodsActivity.this,DengLuctivity.class);
        startActivity(intent);
    }

    public void tianjiachenggong(){
        name = bean1.getGoods_name();
        pic = bean1.getGoods_img();
        price = (float) bean1.getShop_price();
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("productID","1");
        builder.add("count","1");
        builder.add("colorID","1");
        builder.add("sizeID","1");
        builder.add("userID",userID+"");
        builder.add("name",name);
        builder.add("pic",pic);
        builder.add("price",price+"");
        Request request = new Request.Builder()
                .url("http://169.254.94.62:8080/bullking1/cart?")
                .post(builder.build())
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("失败","失败");
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                Log.e("caokexin---->","成功");
            }
        });
    }
}
