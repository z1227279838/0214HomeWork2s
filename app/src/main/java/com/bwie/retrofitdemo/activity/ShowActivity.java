package com.bwie.retrofitdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.retrofitdemo.R;
import com.bwie.retrofitdemo.adapter.MyAdaper;
import com.bwie.retrofitdemo.api.ApiService;
import com.bwie.retrofitdemo.api.UserApiService;
import com.bwie.retrofitdemo.bean.GoodsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.edit_sou)
    EditText editSou;
    @BindView(R.id.text_sou)
    TextView textSou;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ShowActivity.this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @OnClick(R.id.text_sou)
    public void onViewClicked() {
        String sou = editSou.getText().toString();
        if (TextUtils.isEmpty(sou)){
            Toast.makeText(this, "请输入你要搜索的内容", Toast.LENGTH_SHORT).show();
        }else{
            //创建retrofit管理器
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            //实现接口
            UserApiService userApiService = retrofit.create(UserApiService.class);
            userApiService.goods(sou).enqueue(new Callback<GoodsBean>() {
                @Override
                public void onResponse(Call<GoodsBean> call, Response<GoodsBean> response) {
                    GoodsBean goodsBean = response.body();
                    List<GoodsBean.ResultBean> goodsBeanResult = goodsBean.getResult();
                    //创建适配器
                    MyAdaper adaper = new MyAdaper(ShowActivity.this,goodsBeanResult);
                    recyclerView.setAdapter(adaper);

                }

                @Override
                public void onFailure(Call<GoodsBean> call, Throwable t) {

                }
            });
        }
    }
}
