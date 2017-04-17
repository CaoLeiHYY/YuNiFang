package com.nababy.caokexin.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nababy.caokexin.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    private int[] tus1;
    private ViewPager main_viewPager;
    private boolean flag = true;
    ArrayList<ImageView> list_iamge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        main_viewPager = (ViewPager) findViewById(R.id.main_viewPager);
        tus1 = new int[]{R.mipmap.daohangtu1,R.mipmap.daohangtu2,R.mipmap.daohangtu3,
                R.mipmap.daohangtu4,R.mipmap.daohangtu5,R.mipmap.huangying};
        int[] tus2 = new int[]{R.mipmap.ic_launcher};
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        flag = preferences.getBoolean("flag",true);
        if (flag ){
            list_iamge = new ArrayList<>();
            for (int i = 0; i < tus1.length; i++) {
                ImageView image = new ImageView(this);
                image.setImageResource(tus1[i]);
                list_iamge.add(image);
            }
            MyPagerAdapter adapter = new MyPagerAdapter(list_iamge);
            main_viewPager.setAdapter(adapter);
            main_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == tus1.length-1){
                        flag = false;
                        editor.putBoolean("flag",flag);
                        editor.commit();
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this,ZhuActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        },3000);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }else {
            list_iamge = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                ImageView image = new ImageView(this);
                image.setImageResource(R.mipmap.huangying);
                list_iamge.add(image);
            }
            MyPagerAdapter adapter = new MyPagerAdapter(list_iamge);
            main_viewPager.setAdapter(adapter);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this,ZhuActivity.class);
                    startActivity(intent);
                    finish();
                }
            },3000);
        }

    }
    class MyPagerAdapter extends PagerAdapter{

        private ArrayList<ImageView> listimage;

        public MyPagerAdapter(ArrayList<ImageView> listimage) {
            this.listimage = listimage;
        }

        @Override
        public int getCount() {
            return listimage.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(listimage.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(listimage.get(position));
            return listimage.get(position);
        }
    }
}
