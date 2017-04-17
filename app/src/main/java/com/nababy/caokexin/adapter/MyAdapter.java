package com.nababy.caokexin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nababy.caokexin.R;
import com.nababy.caokexin.bean.FirstBean;

import org.xutils.x;

import java.util.List;

/**
 * 1. 类的用途
 * 2. @author forever
 * 3. @date 2017/4/13 15:21
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<FirstBean.DataBean.BestSellersBean.GoodsListBeanX> data;
    //声明接口
    public OnItemClickListener itemClickListener;
    public OnItemLongClickListener itemLongClickListener;

    //定义条目点击事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    //定义条目的长按事件
    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    //对外提供两个访问的set方法
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public MyAdapter(Context context, List<FirstBean.DataBean.BestSellersBean.GoodsListBeanX> data) {
        this.context = context;
        this.data = data;
    }

    //把view绑定给ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.horizontal_buju, null);
        final MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                //注册点击监听
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(v, position);
                }
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getLayoutPosition();
                if (itemLongClickListener != null) {
                    //注册长按监听
                    itemLongClickListener.onItemLongClick(v, position);
                }
                //事件消费，不继续传递
                return true;
            }
        });
        return holder;
    }

    //把数据赋值给view控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_title.setText(data.get(position).getGoods_name());
        x.image().bind(holder.horizontal_imageView,data.get(position).getGoods_img());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //自定义ViewHolder 持有item界面的所有元素
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_title;
        private final ImageView horizontal_imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            horizontal_imageView = (ImageView) itemView.findViewById(R.id.horizontal_iamgeView);
            tv_title = (TextView) itemView.findViewById(R.id.horizontal_textView);
        }
    }
}
