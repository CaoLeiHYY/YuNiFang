package com.nababy.caokexin.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nababy.caokexin.R;

import org.xutils.x;

public class JieSuanActiity extends AppCompatActivity {

    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_suan_actiity);
        ImageView queren_image = (ImageView) findViewById(R.id.queren_image);
        TextView queren_name = (TextView) findViewById(R.id.queren_name);
        TextView queren_item_jiage = (TextView) findViewById(R.id.queren_item_jiage);
        TextView queren_count = (TextView) findViewById(R.id.queren_count);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String image = intent.getStringExtra("image");
        Double price = intent.getDoubleExtra("price",0);
        x.image().bind(queren_image,image);
        queren_name.setText(name);
        queren_item_jiage.setText("总价：￥"+price+"元");
        queren_count.setText("数量："+count);
    }
}
