package com.quickly.xqw.binder.video;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.quickly.xqw.R;
import com.quickly.xqw.model.video.VideoResponseBean;
import com.quickly.xqw.utils.ImageLoader;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import me.drakeet.multitype.ItemViewBinder;

public class VideoViewBinder extends ItemViewBinder<VideoResponseBean.V9LG4CHORBean,VideoViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected VideoViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new VideoViewBinder.ViewHolder(inflater.inflate(R.layout.item_video,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull VideoViewBinder.ViewHolder holder, @NonNull VideoResponseBean.V9LG4CHORBean item) {

        holder.jzvdStd.setUp(item.getMp4_url(), item.getTitle(),Jzvd.SCREEN_WINDOW_NORMAL);
        ImageLoader.loadNormal(holder.itemView.getContext(),item.getCover(),holder.jzvdStd.thumbImageView);

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        JzvdStd jzvdStd;

        ViewHolder(View itemView) {
            super(itemView);
            jzvdStd = itemView.findViewById(R.id.video_player);


        }


    }

}
