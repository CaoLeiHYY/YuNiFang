package com.nababy.caokexin.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nababy.caokexin.R;
import com.nababy.caokexin.fragment.NiJiangMianMo;
import com.nababy.caokexin.fragment.ShuiMianMianMo;
import com.nababy.caokexin.fragment.TieShiMianMo;

import java.util.ArrayList;

public class FenLeiMianMoActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<TextView> list_tests = new ArrayList<>();
    ArrayList<Fragment> list_frgs = new ArrayList<>();
    private TextView tieshimianmo;
    private TextView shuimianmianmo;
    private TextView nijaingmianmo;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fen_lei_mian_mo);
        tieshimianmo = (TextView) findViewById(R.id.tieshimianmo);
        tieshimianmo.setOnClickListener(this);
        shuimianmianmo = (TextView) findViewById(R.id.shuimianmianmo);
        shuimianmianmo.setOnClickListener(this);
        nijaingmianmo = (TextView) findViewById(R.id.nijaingmianmo);
        nijaingmianmo.setOnClickListener(this);
        viewPager = (ViewPager) findViewById(R.id.fenlei_viewPager);
        list_tests.add(tieshimianmo);
        list_tests.add(shuimianmianmo);
        list_tests.add(nijaingmianmo);
        TieShiMianMo tieShiMianMo = new TieShiMianMo();
        ShuiMianMianMo shuiMianMianMo = new ShuiMianMianMo();
        NiJiangMianMo niJiangMianMo = new NiJiangMianMo();
        list_frgs.add(tieShiMianMo);
        list_frgs.add(shuiMianMianMo);
        list_frgs.add(niJiangMianMo);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tieshimianmo:
                viewPager.setCurrentItem(0);
                break;
            case R.id.shuimianmianmo:
                viewPager.setCurrentItem(1);
                break;
            case R.id.nijaingmianmo:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list_frgs.get(position);
        }

        @Override
        public int getCount() {
            return list_frgs.size();
        }
    }
}
