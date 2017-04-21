package com.nababy.caokexin.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;

import com.nababy.caokexin.R;
import com.nababy.caokexin.fragment.BuShiBaoShi;
import com.nababy.caokexin.fragment.JinZhiKangZhou;
import com.nababy.caokexin.fragment.KongYouQuDou;
import com.nababy.caokexin.fragment.MeiBaiTiLiang;
import com.nababy.caokexin.fragment.ShuHuanXinHu;

import java.util.ArrayList;

public class AnGongXiaoActivity extends AppCompatActivity {

    private RadioButton gongxiao_bushuibaoshi;
    private RadioButton gongxiao_shuhuanxiuhu;
    private RadioButton gongxiao_kongyouqudou;
    private RadioButton gongxiao_meibaitiliang;
    private RadioButton gongxiao_jinzhikangzhou;
    ArrayList<RadioButton> listrads = new ArrayList<>();
    ArrayList<Fragment> listfras = new ArrayList<>();
    private ViewPager gongxiao_viewPager;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_gong_xiao);
        Intent intent = getIntent();
        page = intent.getIntExtra("page", 0);
        gongxiao_viewPager = (ViewPager) findViewById(R.id.gongxiao_ViewPager);
        gongxiao_bushuibaoshi = (RadioButton) findViewById(R.id.gongxiao_bushuibaoshi);
        gongxiao_shuhuanxiuhu = (RadioButton) findViewById(R.id.gongxiao_shuhuanxiuhu);
        gongxiao_kongyouqudou = (RadioButton) findViewById(R.id.gongxiao_kongyouqudou);
        gongxiao_meibaitiliang = (RadioButton) findViewById(R.id.gongxiao_meibaitiliang);
        gongxiao_jinzhikangzhou = (RadioButton) findViewById(R.id.gongxiao_jinzhikangzhou);
        listrads.add(gongxiao_bushuibaoshi);listrads.add(gongxiao_shuhuanxiuhu);
        listrads.add(gongxiao_kongyouqudou);listrads.add(gongxiao_meibaitiliang);
        listrads.add(gongxiao_jinzhikangzhou);
        BuShiBaoShi buShiBaoShi = new BuShiBaoShi();
        ShuHuanXinHu shuHuanXinHu = new ShuHuanXinHu();
        KongYouQuDou kongYouQuDou = new KongYouQuDou();
        MeiBaiTiLiang meiBaiTiLiang = new MeiBaiTiLiang();
        JinZhiKangZhou jinZhiKangZhou = new JinZhiKangZhou();
        listfras.add(buShiBaoShi);listfras.add(shuHuanXinHu);listfras.add(kongYouQuDou);
        listfras.add(meiBaiTiLiang);listfras.add(jinZhiKangZhou);
        gongxiao_viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        listrads.get(page).setSelected(true);
        gongxiao_viewPager.setCurrentItem(page);
        gongxiao_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < listrads.size() ; i++) {
                    if (i == position){
                        listrads.get(i).setSelected(true);
                    }else {
                        listrads.get(i).setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listfras.get(position);
        }

        @Override
        public int getCount() {
            return listfras.size();
        }
    }
}
