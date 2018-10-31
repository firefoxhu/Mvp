package com.quickly.xqw.binder.welfare;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.jakewharton.rxbinding2.view.RxView;
import com.quickly.xqw.ImageBrowserActivity;
import com.quickly.xqw.R;
import com.quickly.xqw.XQWApplication;
import com.quickly.xqw.utils.ImageLoader;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import me.drakeet.multitype.ItemViewBinder;

public class GirlViewBinder extends ItemViewBinder<String,GirlViewBinder.ViewHolder> {


    @NonNull
    @Override
    protected GirlViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new GirlViewBinder.ViewHolder(inflater.inflate(R.layout.item_girl,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull GirlViewBinder.ViewHolder holder, @NonNull String url) {



        ImageLoader.load(holder.itemView.getContext(),url,holder.profileImage);

        holder.nickname.setText("未处理姓名");

        RxView.clicks(holder.itemView).throttleFirst(2, TimeUnit.SECONDS).subscribe(o -> {
            ArrayList<String> list = new ArrayList<>();
            list.add(url);
            ImageBrowserActivity.start(holder.itemView.getContext(),url,list);
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView profileImage;

        private TextView nickname;


        ViewHolder(View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.image_item);
            nickname = itemView.findViewById(R.id.name_item);

        }


    }

}
