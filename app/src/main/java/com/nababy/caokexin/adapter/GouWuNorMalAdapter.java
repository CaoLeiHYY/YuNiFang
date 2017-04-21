package com.nababy.caokexin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.nababy.caokexin.R;
import com.nababy.caokexin.bean.MingXingChanPinBean;
import com.nababy.caokexin.util.select_tools.SelectTools;

import org.xutils.x;

import java.util.List;

/**
 * 类的用途
 *
 * @author 曹可新
 * @time Date
 */
public class GouWuNorMalAdapter extends BaseAdapter {

    List<MingXingChanPinBean.DataBean> lists;
    Context context;
    SelectTools st;

    public GouWuNorMalAdapter(List<MingXingChanPinBean.DataBean> lists, Context context, SelectTools st) {
        this.lists = lists;
        this.context = context;
        this.st = st;
    }

    @Override
    public int getCount() {
        return lists.size();
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
        ViewHolder holder = new ViewHolder();
        if (convertView == null){
            convertView = View.inflate(context, R.layout.gouwu_item_normal,null);
            holder.gouwu_check = (CheckBox) convertView.findViewById(R.id.gouwu_checkBox);
            holder.image = (ImageView) convertView.findViewById(R.id.gouwu_image);
            holder.name = (TextView) convertView.findViewById(R.id.gouwu_item_text_name);
            holder.danjia = (TextView) convertView.findViewById(R.id.gouwu_item_jiage);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.gouwu_check.setChecked(st.getState(lists.get(position)));
        x.image().bind(holder.image,lists.get(position).goods_img);
        holder.name.setText(lists.get(position).goods_name);
        holder.danjia.setText("￥"+lists.get(position).shop_price);
        return convertView;
    }

    class ViewHolder{
        CheckBox gouwu_check;
        ImageView image;
        TextView name;
        TextView danjia;
    }

}
