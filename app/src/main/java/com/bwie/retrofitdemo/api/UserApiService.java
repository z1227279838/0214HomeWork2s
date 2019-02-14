package com.bwie.retrofitdemo.api;

import com.bwie.retrofitdemo.bean.GoodsBean;
import com.bwie.retrofitdemo.bean.UserBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApiService {
    @POST("small/user/v1/register")
    @FormUrlEncoded
    Call<UserBean> register(@FieldMap HashMap<String,String> map);

    @GET("small/commodity/v1/findCommodityByKeyword?&page=1&count=10")
    Call<GoodsBean> goods(@Query("keyword") String keyword);



}
