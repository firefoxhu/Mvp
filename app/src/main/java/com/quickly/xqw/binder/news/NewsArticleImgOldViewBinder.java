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

public class NewsArticleImgOldViewBinder extends ItemViewBinder<NewsArticleBean.DataBean.ListBean,NewsArticleImgOldViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_news_old,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull NewsArticleBean.DataBean.ListBean item) {

        final Context context = holder.itemView.getContext();


        ImageLoader.load(context,item.getImages().get(0),holder.image);
        holder.title.setText(item.getTitle());
        holder.source.setText(item.getResource());
        holder.views.setText(item.getViews() +"浏览");
        holder.zan.setText(item.getFabulous() + "赞");

        RxView.clicks(holder.itemView).throttleFirst(3, TimeUnit.SECONDS).subscribe(o -> NewsDetailActivity.launch(item));

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        private  TextView title;

        private TextView source;

        private TextView views;

        private TextView zan;

        ViewHolder(View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.image);
            this.title = itemView.findViewById(R.id.title);
            this.source = itemView.findViewById(R.id.source);
            this.views = itemView.findViewById(R.id.views);
            this.zan = itemView.findViewById(R.id.zan);
        }
    }
}
