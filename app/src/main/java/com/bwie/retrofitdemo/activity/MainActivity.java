package com.bwie.retrofitdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.retrofitdemo.R;
import com.bwie.retrofitdemo.api.ApiService;
import com.bwie.retrofitdemo.api.UserApiService;
import com.bwie.retrofitdemo.bean.UserBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_yanzheng)
    EditText etYanzheng;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_regist)
    Button btnRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_regist)
    public void onViewClicked() {
        String phone = etPhone.getText().toString();
        String pwd = etPwd.getText().toString();
        if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(pwd)){
            Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
        }else{
            //创建retrofit管理器
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            //实现接口
            UserApiService userApiService = retrofit.create(UserApiService.class);
            HashMap<String,String> map = new HashMap<>();
            map.put("phone",phone);
            map.put("pwd",pwd);
            //请求
            userApiService.register(map).enqueue(new Callback<UserBean>() {
                @Override
                public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                    UserBean userBean = response.body();
                    if (userBean.getStatus().equals("0000")){
                        Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                        startActivity(intent);
                    }

                }

                @Override
                public void onFailure(Call<UserBean> call, Throwable t) {

                }
            });
        }
    }
}
