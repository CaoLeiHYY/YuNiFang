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

import java.util.ArrayList;
import java.util.List;

/**
 * 类的用途
 *
 * @author 曹可新
 * @time Date
 */
public class ZhuYe_Grid_Adapter extends BaseAdapter {

    List<FirstBean.DataBean.DefaultGoodsListBean> list;
    Context context;

    public ZhuYe_Grid_Adapter(List<FirstBean.DataBean.DefaultGoodsListBean> list, Context context) {
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
        convertView = View.inflate(context, R.layout.zhuye_grid_item_layout,null);
        ImageView grid_image = (ImageView) convertView.findViewById(R.id.zhuye_grid_image);
        TextView grid_text_name = (TextView) convertView.findViewById(R.id.zhuye_grid_text_name);
        TextView grid_text_describe = (TextView) convertView.findViewById(R.id.zhuye_grid_text_describe);
        x.image().bind(grid_image,list.get(position).getGoods_img());
        grid_text_name.setText(list.get(position).getEfficacy());
        grid_text_describe.setText(list.get(position).getGoods_name());
        return convertView;
    }
}
