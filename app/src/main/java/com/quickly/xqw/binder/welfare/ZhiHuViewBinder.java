package com.quickly.xqw.binder.welfare;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.quickly.xqw.R;
import com.quickly.xqw.model.welfare.ZhiHuBean;
import me.drakeet.multitype.ItemViewBinder;

public class ZhiHuViewBinder extends ItemViewBinder<ZhiHuBean,ZhiHuViewBinder.ViewHolder> {


    @NonNull
    @Override
    protected ZhiHuViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ZhiHuViewBinder.ViewHolder(inflater.inflate(R.layout.item_zhihu,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ZhiHuViewBinder.ViewHolder holder, @NonNull ZhiHuBean bean) {

        Glide.with(holder.itemView.getContext()).load(bean.getImage()).into(holder.image);

        holder.title.setText(bean.getTitle());

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        private TextView title;


        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv);
            title = itemView.findViewById(R.id.title);
        }


    }

}
