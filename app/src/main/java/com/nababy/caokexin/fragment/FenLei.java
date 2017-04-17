package com.nababy.caokexin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nababy.caokexin.R;
import com.nababy.caokexin.adapter.MingXingChanPinAdapter;
import com.nababy.caokexin.bean.MingXingChanPinBean;

import cz.msebera.android.httpclient.Header;

/**
 * 类的用途
 *
 * @author 曹可新
 * @time Date
 */
public class FenLei extends Fragment implements View.OnClickListener {

    private View view;
    private int[] tu_grids;
    private String url = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=83560&encode=3108ed0b9a42c1e160b2912a78692263&category_id=9%E6%8E%A7%E6%B2%B9%E7%A5%9B%E7%97%98http:/";
    private GridView mingxingchanpin_grid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.feinlei_layout,null);
        initView();
        return view;
    }

    private void initView() {
        GridView fenlei_grid = (GridView) view.findViewById(R.id.fenlei_gridView);
        mingxingchanpin_grid = (GridView) view.findViewById(R.id.fenlei_mingxingchanpin_grid);
        ImageView fenlei_mianmo = (ImageView) view.findViewById(R.id.fenlei_mianmo);
        fenlei_mianmo.setOnClickListener(this);
        ImageView fenlei_bushuibaoshi = (ImageView) view.findViewById(R.id.fenlei_bushuibaoshi);
        fenlei_bushuibaoshi.setOnClickListener(this);
        ImageView fenlei_shuhuanxinfu = (ImageView) view.findViewById(R.id.fenlei_shuhuanxinfu);
        fenlei_shuhuanxinfu.setOnClickListener(this);
        ImageView fenlei_kongyouqudou = (ImageView) view.findViewById(R.id.fenlei_kongyouqudou);
        fenlei_kongyouqudou.setOnClickListener(this);
        ImageView fenlei_meibaitiliang = (ImageView) view.findViewById(R.id.fenlei_meibaitiliang);
        fenlei_meibaitiliang.setOnClickListener(this);
        ImageView fenlei_jinzhikangzhou = (ImageView) view.findViewById(R.id.fenlei_jinzhikangzhou);
        fenlei_jinzhikangzhou.setOnClickListener(this);
        Button type_fz_huihe = (Button) view.findViewById(R.id.type_fz_huihe);
        type_fz_huihe.setOnClickListener(this);
        Button type_fz_zhongxing = (Button) view.findViewById(R.id.type_fz_zhongxing);
        type_fz_zhongxing.setOnClickListener(this);
        Button type_fz_ganxing = (Button) view.findViewById(R.id.type_fz_ganxing);
        type_fz_ganxing.setOnClickListener(this);
        Button type_fz_youxing = (Button) view.findViewById(R.id.type_fz_youxing);
        type_fz_youxing.setOnClickListener(this);
        Button type_fz_doudou = (Button) view.findViewById(R.id.type_fz_doudou);
        type_fz_doudou.setOnClickListener(this);
        Button type_fz_mingan = (Button) view.findViewById(R.id.type_fz_mingan);
        type_fz_mingan.setOnClickListener(this);
        tu_grids = new int[]{R.mipmap.classify_emollient_water,R.mipmap.classify_body_lotion, R.mipmap.classify_facial_cleanser,R.mipmap.classify_other};
        fenlei_grid.setAdapter(new MyFenLeiGridAdapter());
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getActivity(), url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new Gson();
                MingXingChanPinBean bean = gson.fromJson(responseString, MingXingChanPinBean.class);
                MingXingChanPinAdapter adapter = new MingXingChanPinAdapter(bean.data,getActivity());
                mingxingchanpin_grid.setAdapter(adapter);
            }
        });
        //分类的点击事件
        fenlei_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        //明星产品的点击事件
        mingxingchanpin_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fenlei_mianmo:
                break;
            case R.id.fenlei_bushuibaoshi:
                break;
            case R.id.fenlei_shuhuanxinfu:
                break;
            case R.id.fenlei_kongyouqudou:
                break;
            case R.id.fenlei_meibaitiliang:
                break;
            case R.id.fenlei_jinzhikangzhou:
                break;
            case R.id.type_fz_huihe:
                break;
            case R.id.type_fz_zhongxing:
                break;
            case R.id.type_fz_ganxing:
                break;
            case R.id.type_fz_youxing:
                break;
            case R.id.type_fz_doudou:
                break;
            case R.id.type_fz_mingan:
                break;
        }
    }

    class MyFenLeiGridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return tu_grids.length;
        }

        @Override
        public Object getItem(int position) {
            return tu_grids[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(getActivity(),R.layout.fenleigrid_item,null);
            ImageView image = (ImageView) convertView.findViewById(R.id.fenlei_grid_image);
            image.setImageResource(tu_grids[position]);
            return convertView;
        }
    }
}
