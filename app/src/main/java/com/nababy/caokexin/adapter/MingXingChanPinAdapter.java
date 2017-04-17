package com.nababy.caokexin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nababy.caokexin.R;
import com.nababy.caokexin.bean.MingXingChanPinBean;

import org.xutils.x;

import java.util.List;

/**
 * 类的用途
 *
 * @author 曹可新
 * @time Date
 */
public class MingXingChanPinAdapter extends BaseAdapter {
    List<MingXingChanPinBean.DataBean> lists;
    Context context;

    public MingXingChanPinAdapter(List<MingXingChanPinBean.DataBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.mingxingchanpin_layout,null);
        ImageView image = (ImageView) convertView.findViewById(R.id.mingxingchanpin_image);
        TextView name = (TextView) convertView.findViewById(R.id.mingxingchanpin_text_name);
        TextView describe = (TextView) convertView.findViewById(R.id.mingxingchanpin_text_describe);
        TextView price = (TextView) convertView.findViewById(R.id.mingxingchanpin_text_price);
        x.image().bind(image,lists.get(position).goods_img);
        name.setText(lists.get(position).efficacy);
        describe.setText(lists.get(position).goods_name);
        price.setText("￥"+lists.get(position).shop_price);
        return convertView;
    }
}
