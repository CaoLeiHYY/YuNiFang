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
import com.nababy.caokexin.fragment.TieShiMianMo;

import java.util.ArrayList;

public class AnFuZhiActivity extends AppCompatActivity {

    ArrayList<RadioButton> listrads = new ArrayList<>();
    ArrayList<Fragment> listfras = new ArrayList<>();
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_fu_zhi);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        page = intent.getIntExtra("page", 0);
        ViewPager fuzhi_ViewPaget = (ViewPager) findViewById(R.id.fuzhi_viewPager);
        RadioButton fuzhi_hunhepifu = (RadioButton) findViewById(R.id.fuzhi_hunhepifu);
        RadioButton fuzhi_zhongxingpifu = (RadioButton) findViewById(R.id.fuzhi_zhongxingpifu);
        RadioButton fuzhi_ganxingpifu = (RadioButton) findViewById(R.id.fuzhi_ganxingpifu);
        RadioButton fuzhi_youxingpifu = (RadioButton) findViewById(R.id.fuzhi_youxingpifu);
        RadioButton fuzhi_doudoufuzhi = (RadioButton) findViewById(R.id.fuzhi_doudoufuzhi);
        RadioButton fuzhi_minganfuzhi = (RadioButton) findViewById(R.id.fuzhi_minganfuzhi);
        listrads.add(fuzhi_hunhepifu);listrads.add(fuzhi_zhongxingpifu);
        listrads.add(fuzhi_ganxingpifu);listrads.add(fuzhi_youxingpifu);
        listrads.add(fuzhi_doudoufuzhi);listrads.add(fuzhi_minganfuzhi);
        BuShiBaoShi buShiBaoShi = new BuShiBaoShi();
        ShuHuanXinHu shuHuanXinHu = new ShuHuanXinHu();
        KongYouQuDou kongYouQuDou = new KongYouQuDou();
        MeiBaiTiLiang meiBaiTiLiang = new MeiBaiTiLiang();
        JinZhiKangZhou jinZhiKangZhou = new JinZhiKangZhou();
        TieShiMianMo tieShiMianMo = new TieShiMianMo();
        listfras.add(buShiBaoShi);listfras.add(shuHuanXinHu);listfras.add(kongYouQuDou);
        listfras.add(meiBaiTiLiang);listfras.add(jinZhiKangZhou);listfras.add(tieShiMianMo);
        fuzhi_ViewPaget.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        listrads.get(page).setSelected(true);
        fuzhi_ViewPaget.setCurrentItem(page);
        fuzhi_ViewPaget.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    class MyPagerAdapter extends FragmentPagerAdapter {

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
