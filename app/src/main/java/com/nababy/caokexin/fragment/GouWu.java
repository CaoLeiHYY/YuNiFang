package com.nababy.caokexin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nababy.caokexin.R;
import com.nababy.caokexin.adapter.GouWuNorMalAdapter;
import com.nababy.caokexin.bean.MingXingChanPinBean;
import com.nababy.caokexin.util.select_tools.SelectTools;
import com.nababy.caokexin.util.select_tools.Select_Tools_Realize;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * 类的用途
 *
 * @author 曹可新
 * @time Date
 */
public class GouWu extends Fragment {

    private View view;
    String url = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=83560&encode=3108ed0b9a42c1e160b2912a78692263&category_id=9%E6%8E%A7%E6%B2%B9%E7%A5%9B%E7%97%98";
    private ListView listView;
    private float count = 0;
    private TextView gouwu_zongjia;
    private CheckBox gouwu_quanxuan;
    private SelectTools st;
    private GouWuNorMalAdapter adapter;
    private List<MingXingChanPinBean.DataBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.gouwu_layout,null);
        initView();
        return view;
    }

    private void initView() {
        st = new Select_Tools_Realize();
        listView = (ListView) view.findViewById(R.id.gouwu_listView);
        gouwu_zongjia = (TextView) view.findViewById(R.id.gouwu_zongjia);
        gouwu_quanxuan = (CheckBox) view.findViewById(R.id.gouwu_quanxuan);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getActivity(), url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new Gson();
                MingXingChanPinBean bean = gson.fromJson(responseString, MingXingChanPinBean.class);
                list = bean.data;
                st.putItems(list, false);
                /*for (int i = 0; i < bean.data.size(); i++) {
                    bean.data.get(i).flag = false;*/
                adapter = new GouWuNorMalAdapter(list, getActivity(), st);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        /*if (bean.data.get(position).flag == true){
                            bean.data.get(position).flag = false;
                            count = (count-bean.data.get(position).shop_price);
                            gouwu_zongjia.setText("总价：￥"+(float)(((int)(count*100))/100f));
                        }else {
                            bean.data.get(position).flag = true;
                            count = (count+bean.data.get(position).shop_price);
                            gouwu_zongjia.setText("总价：￥"+(float)(((int)(count*100))/100f));
                        }
                        boolean flag = true;
                        for (int i = 0; i < bean.data.size(); i++) {
                            if (bean.data.get(i).flag == false){
                                flag = false;
                                break;
                            }
                        }*/
                        gouwu_quanxuan.setChecked(st.clickItem(list.get(position)));
                        gouwu_zongjia.setText("总价：￥"+st.getTotalPrice());
                        adapter.notifyDataSetChanged();
                    }
                });

                gouwu_quanxuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        if (cb.isChecked()){
                            /*for (int i = 0; i < bean.data.size(); i++) {
                                bean.data.get(i).flag = true;
                                count = (count+bean.data.get(i).shop_price);
                                gouwu_zongjia.setText("总价：￥"+(float)(((int)(count*100))/100f));
                            }*/
                            st.selectAll();
                        }else {
                            /*for (int i = 0; i < bean.data.size(); i++) {
                                bean.data.get(i).flag = false;
                                count = (count-count);
                                gouwu_zongjia.setText("总价：￥"+(float)(((int)(count*100))/100f));
                            }*/
                            st.unselect();
                        }
                        gouwu_zongjia.setText("总价：￥"+st.getTotalPrice());
                        adapter.notifyDataSetChanged();
                    }
                });

                /*gouwu_quanxuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    }
                });*/
            }
        });
    }
}
