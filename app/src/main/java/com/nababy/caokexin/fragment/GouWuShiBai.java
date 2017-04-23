package com.nababy.caokexin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nababy.caokexin.R;
import com.nababy.caokexin.activity.DengLuctivity;
import com.nababy.caokexin.activity.JieSuanActiity;
import com.nababy.caokexin.activity.ZhuActivity;
import com.nababy.caokexin.adapter.GouWuNorMalAdapter;
import com.nababy.caokexin.bean.GouwucheBean;
import com.nababy.caokexin.bean.MingXingChanPinBean;
import com.nababy.caokexin.util.select_tools.SelectTools;
import com.nababy.caokexin.util.select_tools.Select_Tools_Realize;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * 类的用途
 *
 * @author 曹可新
 * @time Date
 */
public class GouWuShiBai extends Fragment {

    private View success;
    private View failed;
    String url = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=83560&encode=3108ed0b9a42c1e160b2912a78692263&category_id=9%E6%8E%A7%E6%B2%B9%E7%A5%9B%E7%97%98";
    private ListView listView;
    private float count = 0;
    private TextView gouwu_zongjia;
    private CheckBox gouwu_quanxuan;
    private SelectTools st;
    private View view;
    private ArrayList<GouwucheBean.CartItemList> cartItemList1;
    private int id = 67;
    private GouWuNorMalAdapter adapter;
    private Button jiesuan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.gouwu_shibai,null);
        success = view.findViewById(R.id.success_layout);
        failed = view.findViewById(R.id.failed_layout);
        listView = (ListView) view.findViewById(R.id.gouwu_listView);
        gouwu_zongjia = (TextView) view.findViewById(R.id.gouwu_zongjia);
        gouwu_quanxuan = (CheckBox) view.findViewById(R.id.gouwu_quanxuan);
        jiesuan = (Button) view.findViewById(R.id.jiesuan);
        return view;
    }

    public void setSuccess(){
        if(ZhuActivity.flag){
            success.setVisibility(View.VISIBLE);
            failed.setVisibility(View.GONE);
            questDatas(id);
        }else{
            success.setVisibility(View.GONE);
            failed.setVisibility(View.VISIBLE);
            initData();
        }
    }

    private void initData() {
        Button gouwu_login = (Button) view.findViewById(R.id.gouwu_login);
        gouwu_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DengLuctivity.class);
                startActivity(intent);
            }
        });
    }

    //写个请求的方法
    public void  questDatas(int id){
        st = new Select_Tools_Realize();

        String url="http://169.254.94.62:8080/bullking1/cart?userID="+id;
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(getActivity(), url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson=new Gson();
                Log.i("xxx",responseString);
                GouwucheBean bean = gson.fromJson(responseString, GouwucheBean.class);
                cartItemList1 = bean.getCartItemList();
                adapter = new GouWuNorMalAdapter(cartItemList1,getActivity(),st);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        gouwu_quanxuan.setChecked(st.clickItem(cartItemList1.get(position)));
                        gouwu_zongjia.setText("总价：￥"+st.getTotalPrice());
                        adapter.notifyDataSetChanged();
                    }
                });

                gouwu_quanxuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        if (cb.isChecked()){
                            st.selectAll();
                        }else {
                            st.unselect();
                        }
                        gouwu_zongjia.setText("总价：￥"+st.getTotalPrice());
                        adapter.notifyDataSetChanged();
                    }
                });
                jiesuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),JieSuanActiity.class);
                        startActivity(intent);
                    }
                });
            }
        });

    }


//    private void initView() {
//        st = new Select_Tools_Realize();
//        listView = (ListView) view.findViewById(R.id.gouwu_listView);
//        gouwu_zongjia = (TextView) view.findViewById(R.id.gouwu_zongjia);
//        gouwu_quanxuan = (CheckBox) view.findViewById(R.id.gouwu_quanxuan);
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get(getActivity(), url, new TextHttpResponseHandler() {
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                Gson gson = new Gson();
//                MingXingChanPinBean bean = gson.fromJson(responseString, MingXingChanPinBean.class);
//                list = bean.data;
//                st.putItems(list, false);
////                adapter = new GouWuNorMalAdapter(list, getActivity(), st);
//                listView.setAdapter(adapter);
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        gouwu_quanxuan.setChecked(st.clickItem(cartItemList1.get(position)));
//                        gouwu_zongjia.setText("总价：￥"+st.getTotalPrice());
//                        adapter.notifyDataSetChanged();
//                    }
//                });
//
//                gouwu_quanxuan.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        CheckBox cb = (CheckBox) v;
//                        if (cb.isChecked()){
//                            st.selectAll();
//                        }else {
//                            st.unselect();
//                        }
//                        gouwu_zongjia.setText("总价：￥"+st.getTotalPrice());
//                        adapter.notifyDataSetChanged();
//                    }
//                });
//            }
//        });
//    }

}
