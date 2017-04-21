package com.nababy.caokexin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nababy.caokexin.R;
import com.nababy.caokexin.bean.FirstBean;

import org.xutils.x;

import java.util.List;

/**
 * 类的用途
 *
 * @author 曹可新
 * @time Date
 */
public class ZhuanTiAdapter extends BaseAdapter {

    List<FirstBean.DataBean.SubjectsBean.GoodsListBean> list;
    Context context;

    public ZhuanTiAdapter(List<FirstBean.DataBean.SubjectsBean.GoodsListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

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
        convertView = View.inflate(context, R.layout.mingxingchanpin_layout,null);
        ImageView image = (ImageView) convertView.findViewById(R.id.mingxingchanpin_image);
        TextView name = (TextView) convertView.findViewById(R.id.mingxingchanpin_text_name);
        TextView describe = (TextView) convertView.findViewById(R.id.mingxingchanpin_text_describe);
        TextView price = (TextView) convertView.findViewById(R.id.mingxingchanpin_text_price);
        x.image().bind(image,list.get(position).getGoods_img());
        name.setText(list.get(position).getEfficacy());
        describe.setText(list.get(position).getGoods_name());
        price.setText("￥"+list.get(position).getShop_price());
        return convertView;
    }

}
