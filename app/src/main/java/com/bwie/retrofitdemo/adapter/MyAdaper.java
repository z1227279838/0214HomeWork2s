package com.bwie.retrofitdemo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.retrofitdemo.R;
import com.bwie.retrofitdemo.bean.GoodsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdaper extends RecyclerView.Adapter<MyAdaper.ViewHolder> {

    private Context context;
    private List<GoodsBean.ResultBean> goodsBeanResult;

    public MyAdaper(Context context, List<GoodsBean.ResultBean> goodsBeanResult) {
        this.context = context;
        this.goodsBeanResult = goodsBeanResult;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.goods_item_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Uri uri = Uri.parse(goodsBeanResult.get(i).getMasterPic());
        viewHolder.simpleView.setImageURI(uri);
        viewHolder.textPrice.setText("￥:"+goodsBeanResult.get(i).getPrice());
        viewHolder.textTitle.setText(goodsBeanResult.get(i).getCommodityName());

    }

    @Override
    public int getItemCount() {
        return goodsBeanResult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.simple_view)
        SimpleDraweeView simpleView;
        @BindView(R.id.text_title)
        TextView textTitle;
        @BindView(R.id.text_price)
        TextView textPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
