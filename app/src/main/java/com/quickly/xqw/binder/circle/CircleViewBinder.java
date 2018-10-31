package com.quickly.xqw.binder.circle;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.jakewharton.rxbinding2.view.RxView;
import com.quickly.xqw.ImageBrowserActivity;
import com.quickly.xqw.R;
import com.quickly.xqw.XQWApplication;
import com.quickly.xqw.model.circle.ArticleBean;
import com.quickly.xqw.module.circle.profile.PersonalProfileActivity;
import com.quickly.xqw.module.ui.CircleContentActivity;
import com.quickly.xqw.utils.ImageLoader;
import com.quickly.xqw.widget.MultiImageViewLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import jaydenxiao.com.expandabletextview.ExpandableTextView;
import me.drakeet.multitype.ItemViewBinder;

public class CircleViewBinder extends ItemViewBinder<ArticleBean.DataBean.ListBean,CircleViewBinder.ViewHolder> {

    public static final String TAG = "CircleViewBinder";
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.circle_item,parent,false));
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ArticleBean.DataBean.ListBean item) {
        final Context context = holder.itemView.getContext();


        ImageLoader.loadNormal(holder.itemView.getContext(),item.getAvatar(),holder.profile_image);

        holder.nickname.setText(item.getAuthor());
        holder.etv.setText(item.getContent());
        holder.tv_views.setText(item.getViews() + "浏览");
        holder.timer.setText(item.getCreateTime());
        holder.commentNum.setText(String.valueOf(item.getCommentsNumber()));

        /**
         * 设置 预览图片
         */
        if(item.getPictures() != null) {
            holder.multi_image.setList((ArrayList<String>) item.getPictures());
        }

        RxView.clicks(holder.itemView).throttleFirst(2, TimeUnit.SECONDS).subscribe(o -> {
            CircleContentActivity.launch(item);
        });

        RxView.clicks(holder.profile_image).throttleFirst(3, TimeUnit.SECONDS).subscribe(o -> PersonalProfileActivity.launch(item));

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView profile_image;

        private TextView nickname;

        private ExpandableTextView etv;

        private MultiImageViewLayout multi_image;

        private TextView tv_views;

        private TextView timer;

        private TextView commentNum;

        private ImageView zan_icon;

        private ImageView comment_icon;




        ViewHolder(View itemView) {
            super(itemView);

            this.profile_image = itemView.findViewById(R.id.profile_image);
            this.nickname = itemView.findViewById(R.id.nickname);
            this.etv = itemView.findViewById(R.id.etv);
            this.tv_views = itemView.findViewById(R.id.tv_views);
            this.timer = itemView.findViewById(R.id.timer);

            this.zan_icon = itemView.findViewById(R.id.zan_icon);
            this.comment_icon = itemView.findViewById(R.id.comment_icon);

            this.multi_image = itemView.findViewById(R.id.multi_image);
            this.commentNum = itemView.findViewById(R.id.comment_num);

            this.multi_image.setOnItemClickListener(new MultiImageViewLayout.OnItemClickListener() {
                @Override
                public void onItemClick(View view,List<String> urls, int PressImagePosition, float PressX, float PressY) {
                    ImageBrowserActivity.start(XQWApplication.appContext,urls.get(PressImagePosition), (ArrayList<String>) urls);
                }

                @Override
                public void onItemLongClick(View view, int PressImagePosition, float PressX, float PressY) {

                }
            });

        }
    }
}
