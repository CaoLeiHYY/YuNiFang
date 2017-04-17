package com.nababy.caokexin.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.nababy.caokexin.R;
import com.nababy.caokexin.fragment.FenLei;
import com.nababy.caokexin.fragment.GeRen;
import com.nababy.caokexin.fragment.GouWu;
import com.nababy.caokexin.fragment.MeiLa;
import com.nababy.caokexin.fragment.ShouYe;

import java.util.ArrayList;

public class ZhuActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton saoyisao;
    private ImageButton sousuo;
    private RadioButton radio_shouye;
    private RadioButton radio_fenlei;
    private RadioButton radio_meila;
    private RadioButton radio_wode;
    private RadioButton radio_gouwu;
    ArrayList<Fragment> list_fragments = new ArrayList<>();
    private ShouYe shouYe;
    private FenLei fenLei;
    private MeiLa meiLa;
    private GouWu gouWu;
    private GeRen geRen;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu);
        initView();
    }

    private void initView() {
        saoyisao = (ImageButton) findViewById(R.id.zhu_sao_yi_sao);
        sousuo = (ImageButton) findViewById(R.id.zhu_sousuo);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radio_shouye = (RadioButton) findViewById(R.id.radio_shouye);
        radio_shouye.setChecked(true);
        radio_shouye.setOnClickListener(this);
        radio_fenlei = (RadioButton) findViewById(R.id.radio_fenlei);
        radio_fenlei.setOnClickListener(this);
        radio_meila = (RadioButton) findViewById(R.id.radio_meila);
        radio_meila.setOnClickListener(this);
        radio_gouwu = (RadioButton) findViewById(R.id.radio_gouwu);
        radio_gouwu.setOnClickListener(this);
        radio_wode = (RadioButton) findViewById(R.id.radio_wode);
        radio_wode.setOnClickListener(this);
        shouYe = new ShouYe();
        fenLei = new FenLei();
        meiLa = new MeiLa();
        gouWu = new GouWu();
        geRen = new GeRen();
        list_fragments.add(shouYe);
        list_fragments.add(fenLei);
        list_fragments.add(meiLa);
        list_fragments.add(gouWu);
        list_fragments.add(geRen);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, shouYe,"shouye");
        transaction.add(R.id.frameLayout, fenLei,"fenlei");
        transaction.add(R.id.frameLayout, meiLa,"meila");
        transaction.add(R.id.frameLayout, gouWu,"gouwu");
        transaction.add(R.id.frameLayout, geRen,"geren");
        showAndHide(shouYe,fenLei,meiLa,gouWu,geRen);
        transaction.commit();
    }

    private void showAndHide(Fragment shouYe,Fragment fenLei,Fragment meiLa,Fragment gouWu,Fragment geRen) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(shouYe);
        transaction.hide(fenLei);
        transaction.hide(meiLa);
        transaction.hide(gouWu);
        transaction.hide(geRen);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radio_shouye:
                showAndHide(shouYe,fenLei,meiLa,gouWu,geRen);
                radioGroup.getChildAt(0).setSelected(true);
                break;
            case R.id.radio_fenlei:
                showAndHide(fenLei,shouYe,meiLa,gouWu,geRen);
                radioGroup.getChildAt(1).setSelected(true);
                break;
            case R.id.radio_meila:
                showAndHide(meiLa,shouYe,fenLei,gouWu,geRen);
                radioGroup.getChildAt(1).setSelected(true);
                break;
            case R.id.radio_gouwu:
                showAndHide(gouWu,shouYe,meiLa,fenLei,geRen);
                radioGroup.getChildAt(1).setSelected(true);
                break;
            case R.id.radio_wode:
                showAndHide(geRen,shouYe,meiLa,gouWu,fenLei);
                radioGroup.getChildAt(1).setSelected(true);
                break;
        }
    }
}
