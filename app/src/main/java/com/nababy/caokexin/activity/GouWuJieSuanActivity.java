package com.nababy.caokexin.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nababy.caokexin.R;
import com.nababy.caokexin.adapter.MyAdapter;
import com.nababy.caokexin.bean.YuDingBean;

import org.xutils.x;

import java.util.ArrayList;

public class GouWuJieSuanActivity extends AppCompatActivity {

    private ArrayList<YuDingBean.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou_wu_jie_suan);
        Intent intent = getIntent();
        ListView gouteujiesuan_list = (ListView) findViewById(R.id.gouteujiesuan_list);
        list = (ArrayList<YuDingBean.DataBean>) intent.getSerializableExtra("list");
        MyBaseAdapter adapter = new MyBaseAdapter();
        gouteujiesuan_list.setAdapter(adapter);
    }
    class MyBaseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(GouWuJieSuanActivity.this,R.layout.gouwujiesuan_item,null);
            ImageView gouwujiesuan_image = (ImageView) convertView.findViewById(R.id.gouwujiesuan_image);
            TextView gouwujiesuan_name = (TextView) convertView.findViewById(R.id.gouwujiesuan_name);
            TextView gouwujiesuan_jiage = (TextView) convertView.findViewById(R.id.gouwujiesuan_jiage);
            x.image().bind(gouwujiesuan_image,list.get(position).getContact());
            gouwujiesuan_name.setText(list.get(position).getGoods_name());
            gouwujiesuan_jiage.setText(list.get(position).getChild_price());
            return convertView;
        }
    }
}
