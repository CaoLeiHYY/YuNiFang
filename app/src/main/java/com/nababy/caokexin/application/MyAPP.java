package com.nababy.caokexin.application;

import android.app.Application;

import org.xutils.x;

/**
 * 类的用途
 *
 * @author 曹可新
 * @time Date
 */
public class MyAPP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
