package com.nababy.caokexin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nababy.caokexin.R;
import com.nababy.caokexin.activity.BannerActivity;
import com.nababy.caokexin.activity.GoodsActivity;
import com.nababy.caokexin.activity.ZhuanTiActivity;
import com.nababy.caokexin.adapter.MyAdapter;
import com.nababy.caokexin.adapter.MyAdapter1;
import com.nababy.caokexin.adapter.MyAdapter2;
import com.nababy.caokexin.adapter.ZhuYe_Grid_Adapter;
import com.nababy.caokexin.bean.FirstBean;
import com.nababy.caokexin.util.FullyLinearLayoutManager;
import com.nababy.caokexin.util.GlideImageLoader;
import com.nababy.caokexin.view.HorizontalListView;
import com.nababy.caokexin.view.MyDecoration;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * 类的用途
 *
 * @author 曹可新
 * @time Date
 */
public class ShouYe extends Fragment implements View.OnClickListener {

    private View view;
    ArrayList<String> list_viewPager_images = new ArrayList<>();
    private String url = "http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447";
    private HorizontalListView horizontalListView;
    private RecyclerView recyclerView;
    private RecyclerView zhuye_recyclerView;
    private FirstBean bean;
    private RecyclerView xinpinchangxian_recyclerView;
    private ImageView zhuye_xinpinchangxian;
    private ImageView zhuye_tieshimianmo;
    private RecyclerView tieshimianmo_recyclerView;
    private ImageView zhuye_jubudanpin;
    private RecyclerView jubudanpin_recylerView;
    private ImageView zhuye_shihuitaozhuang;
    private RecyclerView shihuitaozhuang_recylerView;
    private ImageView zhuye_nongjiangshuimian;
    private RecyclerView nongjiangshuimian_recylerView;
    private ImageView zhuye_xingnanhuli;
    private RecyclerView xingnanhuli_recylerView;
    private ImageView zhuye_coudanzhuanqu;
    private RecyclerView coudanzhuanqu_recylerView;
    private GridView zhuye_gridView;
    private Banner banner;
    private ImageView zhuye_image_remen;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.zhuye_layout,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    private void initView() {
//        horizontalListView = (HorizontalListView) view.findViewById(R.id.zhuye_horizontal);
        banner = (Banner) view.findViewById(R.id.banner);
        recyclerView = (RecyclerView) view.findViewById(R.id.zhuye_horizontal);
        zhuye_recyclerView = (RecyclerView) view.findViewById(R.id.zhuye_recyclerView);
        zhuye_xinpinchangxian = (ImageView) view.findViewById(R.id.zhuye_xinpinchangxian);
        xinpinchangxian_recyclerView = (RecyclerView) view.findViewById(R.id.xinpinchangxian_recyclerView);
        zhuye_tieshimianmo = (ImageView) view.findViewById(R.id.zhuye_tieshimianmo);
        tieshimianmo_recyclerView = (RecyclerView) view.findViewById(R.id.tieshimianmo_recyclerView);
        zhuye_jubudanpin = (ImageView) view.findViewById(R.id.zhuye_jubudanpin);
        jubudanpin_recylerView = (RecyclerView) view.findViewById(R.id.jubudanpin_recyclerView);
        zhuye_shihuitaozhuang = (ImageView) view.findViewById(R.id.zhuye_shihuitaohuang);
        shihuitaozhuang_recylerView = (RecyclerView) view.findViewById(R.id.shihuitaozhuang_recyclerView);
        zhuye_nongjiangshuimian = (ImageView) view.findViewById(R.id.zhuye_nongjiangshuimian);
        nongjiangshuimian_recylerView = (RecyclerView) view.findViewById(R.id.nongjiangshuimian_recyclerView);
        zhuye_xingnanhuli = (ImageView) view.findViewById(R.id.zhuye_xingnanhuli);
        xingnanhuli_recylerView = (RecyclerView) view.findViewById(R.id.xingnanhuli_recyclerView);
        zhuye_coudanzhuanqu = (ImageView) view.findViewById(R.id.zhuye_coudanzhuanqu);
        coudanzhuanqu_recylerView = (RecyclerView) view.findViewById(R.id.coudanzhuanqu_recyclerView);
        zhuye_gridView = (GridView) view.findViewById(R.id.zhuye_gridView);
        zhuye_xinpinchangxian.setOnClickListener(this);
        zhuye_tieshimianmo.setOnClickListener(this);
        zhuye_jubudanpin.setOnClickListener(this);
        zhuye_shihuitaozhuang.setOnClickListener(this);
        zhuye_nongjiangshuimian.setOnClickListener(this);
        zhuye_xingnanhuli.setOnClickListener(this);
        zhuye_coudanzhuanqu.setOnClickListener(this);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getActivity(), url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(), "网络请求成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Toast.makeText(getActivity(), "网络请求成功", Toast.LENGTH_SHORT).show();
                final Gson gson = new Gson();
                bean = gson.fromJson(responseString, FirstBean.class);
                for (int i = 0; i < bean.getData().getAd1().size(); i++) {
                    list_viewPager_images.add(bean.getData().getAd1().get(i).getImage());
                }
                Banner banner = (Banner) view.findViewById(R.id.banner);
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner.setImages(list_viewPager_images);
                //banner设置方法全部调用完毕时最后调用
                banner.start();
                recyclerViewShiPei();
                //设置布局管理器
                FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                zhuye_recyclerView.setLayoutManager(linearLayoutManager);
                //添加华丽的分割线
//                MyDecoration decoration = new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST);
//                recyclerView.addItemDecoration(decoration);
                MyAdapter1 adapter1 = new MyAdapter1(getActivity(),bean.getData().getActivityInfo().getActivityInfoList());
                zhuye_recyclerView.setAdapter(adapter1);
                adapter1.setOnItemClickListener(new MyAdapter1.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                    }
                });
                adapter1.setOnItemLongClickListener(new MyAdapter1.OnItemLongClickListener() {
                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });
                shiPeiRecylerView(0,bean.getData().getSubjects(),zhuye_xinpinchangxian,xinpinchangxian_recyclerView);
                shiPeiRecylerView(1,bean.getData().getSubjects(),zhuye_tieshimianmo,tieshimianmo_recyclerView);
                shiPeiRecylerView(2,bean.getData().getSubjects(),zhuye_jubudanpin,jubudanpin_recylerView);
                shiPeiRecylerView(3,bean.getData().getSubjects(),zhuye_shihuitaozhuang,shihuitaozhuang_recylerView);
                shiPeiRecylerView(4,bean.getData().getSubjects(),zhuye_nongjiangshuimian,nongjiangshuimian_recylerView);
                shiPeiRecylerView(5,bean.getData().getSubjects(),zhuye_xingnanhuli,xingnanhuli_recylerView);
                shiPeiRecylerView(6,bean.getData().getSubjects(),zhuye_coudanzhuanqu,coudanzhuanqu_recylerView);
                ZhuYe_Grid_Adapter adapter = new ZhuYe_Grid_Adapter(bean.getData().getDefaultGoodsList(),getActivity());
                zhuye_gridView.setAdapter(adapter);
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(getActivity(), BannerActivity.class);
                        intent.putExtra("url",bean.getData().getAd1().get(position).getAd_type_dynamic_data());
                        startActivity(intent);
                    }
                });
                zhuye_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String json = gson.toJson(bean.getData().getDefaultGoodsList().get(position));
                        Intent intent = new Intent(getActivity(),GoodsActivity.class);
                        intent.putExtra("json",json);
                        startActivity(intent);
                    }
                });
            }
        });
    }
    public void recyclerViewShiPei(){
        //设置布局管理器
        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //添加华丽的分割线
//        MyDecoration dec mDecoration(decoration);
        MyAdapter adapter = new MyAdapter(getActivity(), bean.getData().getBestSellers().get(0).getGoodsList());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        });
        adapter.setOnItemLongClickListener(new MyAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }
    public void shiPeiRecylerView(final int position, final List<FirstBean.DataBean.SubjectsBean> list1, ImageView image, RecyclerView recyclerView){
        x.image().bind(image,list1.get(position).getImage());
//设置布局管理器
        FullyLinearLayoutManager xinpinchangxianManager = new FullyLinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(xinpinchangxianManager);
        //添加分割线
//                MyDecoration xinpinchangxianDecoration = new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST);
//                xinpinchangxian_recyclerView.addItemDecoration(xinpinchangxianDecoration);
        MyAdapter2 myAdapter2 = new MyAdapter2(getActivity(), list1.get(position).getGoodsList());
        recyclerView.setAdapter(myAdapter2);
        myAdapter2.setOnItemClickListener(new MyAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Gson gson = new Gson();
                String json = gson.toJson(list1.get(position).getGoodsList().get(i));
                Intent intent = new Intent(getActivity(), GoodsActivity.class);
                intent.putExtra("json",json);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuye_xinpinchangxian:
                intent = new Intent(getActivity(), ZhuanTiActivity.class);
                intent.putExtra("zhi",0);
                startActivity(intent);
                break;
            case R.id.zhuye_tieshimianmo:
                intent.putExtra("zhi",1);
                startActivity(intent);
                break;
            case R.id.zhuye_jubudanpin:
                intent.putExtra("zhi",2);
                startActivity(intent);
                break;
            case R.id.zhuye_shihuitaohuang:
                intent.putExtra("zhi",3);
                startActivity(intent);
                break;
            case R.id.zhuye_nongjiangshuimian:
                intent.putExtra("zhi",4);
                startActivity(intent);
                break;
            case R.id.zhuye_xingnanhuli:
                intent.putExtra("zhi",5);
                startActivity(intent);
                break;
            case R.id.zhuye_coudanzhuanqu:
                intent.putExtra("zhi",6);
                startActivity(intent);
                break;
        }
    }
}
