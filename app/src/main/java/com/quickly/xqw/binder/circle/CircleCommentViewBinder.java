package com.quickly.xqw.binder.circle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.quickly.xqw.R;
import com.quickly.xqw.model.circle.CommentBean;
import com.quickly.xqw.utils.ImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;
import me.drakeet.multitype.ItemViewBinder;

public class CircleCommentViewBinder extends ItemViewBinder<CommentBean.DataBean.ListBean,CircleCommentViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected CircleCommentViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new CircleCommentViewBinder.ViewHolder(inflater.inflate(R.layout.item_comment,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CommentBean.DataBean.ListBean item) {


        ImageLoader.load(holder.itemView.getContext(),item.getAvatar(),holder.profileImage);

        holder.nickname.setText(item.getNickname());

        holder.content.setText(item.getContent());

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView profileImage;

        private TextView nickname;

        private TextView content;

        ViewHolder(View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image);
            nickname = itemView.findViewById(R.id.nickname);
            content = itemView.findViewById(R.id.content);

        }


    }

}
