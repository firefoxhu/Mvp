package com.quickly.xqw.binder.news;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.quickly.xqw.R;
import com.quickly.xqw.model.news.NewsArticleBean;
import com.quickly.xqw.module.news.detail.NewsDetailActivity;
import com.quickly.xqw.utils.ImageLoader;

import java.util.concurrent.TimeUnit;

import me.drakeet.multitype.ItemViewBinder;

public class NewsArticleImgViewBinder extends ItemViewBinder<NewsArticleBean.DataBean.ListBean,NewsArticleImgViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_news_article_img,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull NewsArticleBean.DataBean.ListBean item) {
        final Context context = holder.itemView.getContext();

        ImageLoader.load(context,item.getImages().get(0),holder.iv_image);
        ImageLoader.load(context,item.getImages().get(0),holder.iv_media);
        holder.tv_title.setText(item.getTitle());
        holder.tv_extra.setText(item.getResource() + " - " + item.getViews() +"浏览");

        RxView.clicks(holder.itemView)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(o -> NewsDetailActivity.launch(item));


    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_media;

        private TextView tv_extra;

        private ImageView iv_dots;

        private TextView tv_title;

        private ImageView iv_image;

        ViewHolder(View itemView) {
            super(itemView);
            this.iv_media = itemView.findViewById(R.id.iv_media);
            this.tv_extra = itemView.findViewById(R.id.tv_extra);
            this.iv_dots = itemView.findViewById(R.id.iv_dots);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.iv_image = itemView.findViewById(R.id.iv_image);
        }
    }
}
