package com.nababy.caokexin.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nababy.caokexin.R;

import cz.msebera.android.httpclient.Header;

public class DengLuctivity extends AppCompatActivity implements View.OnClickListener {

    private EditText login_edit_name;
    private EditText login_edit_pwd;
    private Button login_zhuce;
    private Button login_login;
    private ImageView login_back_normal;
    private String url;
    private String name;
    private String pwd;
    private AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deng_luctivity);
        initView();
    }

    private void initView() {
        client = new AsyncHttpClient();
        login_zhuce = (Button) findViewById(R.id.login_zhuce);
        login_zhuce.setOnClickListener(this);
        login_edit_name = (EditText) findViewById(R.id.login_edit_name);
        login_edit_pwd = (EditText) findViewById(R.id.login_edit_pwd);
        login_login = (Button) findViewById(R.id.login_login);
        login_login.setOnClickListener(this);
        login_back_normal = (ImageView) findViewById(R.id.login_back_normal);
        login_back_normal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_zhuce:
                name = login_edit_name.getText().toString();
                pwd = login_edit_pwd.getText().toString();
                url = "http://169.254.94.62:8080/bullking1/register?name="+name+"&pwd="+pwd;
                client.get(DengLuctivity.this, url, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(DengLuctivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Toast.makeText(DengLuctivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.login_login:
                name = login_edit_name.getText().toString();
                pwd = login_edit_pwd.getText().toString();
                url = "http://169.254.94.62:8080/bullking1/login?name="+name+"&pwd="+pwd;
                client.get(DengLuctivity.this, url, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Toast.makeText(DengLuctivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        boolean flag = true;
                        ZhuActivity.flag = true;
                        editor.putBoolean("flag",flag);
                        editor.commit();
                        Log.e("caokexin------>",responseString);
                    }
                });
                break;
            case R.id.login_back_normal:
                break;
        }
    }
}
