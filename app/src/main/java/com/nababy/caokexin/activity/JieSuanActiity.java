package com.nababy.caokexin.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nababy.caokexin.R;

import org.xutils.x;

public class JieSuanActiity extends AppCompatActivity implements View.OnClickListener {

    private int count = 1;
    private ImageView quern_jiashangpin;
    private ImageView queren_shangpinjian;
    private TextView queren_shangpincount;
    private TextView queren_zongji;
    private Double price;
    private TextView queren_shifu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_suan_actiity);
        ImageView queren_image = (ImageView) findViewById(R.id.queren_image);
        TextView queren_name = (TextView) findViewById(R.id.queren_name);
        TextView queren_item_jiage = (TextView) findViewById(R.id.queren_item_jiage);
        quern_jiashangpin = (ImageView) findViewById(R.id.quern_jiashangpin);
        quern_jiashangpin.setOnClickListener(this);
        queren_shangpinjian = (ImageView) findViewById(R.id.queren_shangpinjian);
        queren_shangpinjian.setOnClickListener(this);
        queren_shangpincount = (TextView) findViewById(R.id.queren_shangpincount);
        queren_zongji = (TextView) findViewById(R.id.queren_zongji);
        queren_shifu = (TextView) findViewById(R.id.queren_shifu);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String image = intent.getStringExtra("image");
        price = intent.getDoubleExtra("price",0);
        x.image().bind(queren_image,image);
        queren_name.setText(name);
        queren_item_jiage.setText("总价：￥"+ price +"元");
        queren_shifu.setText("实付：￥"+price*count+"元");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.quern_jiashangpin:
                count += 1;
                queren_shangpincount.setText(""+count);
                queren_zongji.setText("共计"+count+"件商品，共"+price*count+"元");
                queren_shifu.setText("实付：￥"+price*count+"元");
                break;
            case R.id.queren_shangpinjian:
                count -= 1;
                queren_shangpincount.setText(""+count);
                queren_zongji.setText("共计"+count+"件商品，共"+price+"元");
                queren_shifu.setText("实付：￥"+price*count+"元");
                break;
        }
    }
}
