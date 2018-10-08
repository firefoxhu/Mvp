package com.quickly.xqw.binder.circle;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.quickly.xqw.ImageBrowserActivity;
import com.quickly.xqw.R;
import com.quickly.xqw.XQWApplication;
import com.quickly.xqw.api.HttpUri;
import com.quickly.xqw.api.ICircle;
import com.quickly.xqw.model.circle.ArticleBean;
import com.quickly.xqw.model.circle.CommentBean;
import com.quickly.xqw.module.circle.cotent.CircleContentActivity;
import com.quickly.xqw.utils.RetrofitFactory2;
import com.quickly.xqw.widget.CommentDialogFragment;
import com.quickly.xqw.widget.CommentListTextView;
import com.quickly.xqw.widget.MultiImageViewLayout;
import com.quickly.xqw.widget.PraiseTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import jaydenxiao.com.expandabletextview.ExpandableTextView;
import me.drakeet.multitype.ItemViewBinder;

public class CircleViewBinder extends ItemViewBinder<ArticleBean.DataBean.ListBean,CircleViewBinder.ViewHolder> {

    private static final String TAG = "CircleViewBinder";
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.circle_item,parent,false));
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ArticleBean.DataBean.ListBean item) {
        final Context context = holder.itemView.getContext();
        List<PraiseTextView.PraiseInfo> praiseInfos  = new ArrayList<>();

        Glide.with(context).load(item.getAvatar()).thumbnail(0.1f).into(holder.profile_image);
        holder.nickname.setText(item.getAuthor());
        holder.etv.setText(item.getContent());
        if(item.getPictures() != null)
            holder.multi_image.setList((ArrayList<String>) item.getPictures());
        holder.tv_views.setText(item.getViews() + "浏览");
        holder.timer.setText(item.getCreateTime());

        RxView.clicks(holder.zan_icon).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(o -> {

                    if(holder.praiseTextView.getVisibility() == View.GONE) {
                        holder.praiseTextView.setVisibility(View.VISIBLE);
                    }

                    // 赞后保持状态
                    // 改变按钮状态
                    praiseInfos.add(new PraiseTextView.PraiseInfo ().setId (1115).setNickname ("我也赞").setLogo ("http://lujianchao.com/images/headimg/1.jpg"));
                    holder.praiseTextView.setData(praiseInfos);

                });


        RxView.clicks(holder.comment_icon).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(o ->{
                    Toast.makeText(context, "弹出评论框！", Toast.LENGTH_SHORT).show();
                    //holder.praiseTextView.cancelPraise();

                    CommentDialogFragment commentDialogFragment = new CommentDialogFragment(context);
                    commentDialogFragment.show();
                });

        RxView.clicks(holder.profile_image)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(o -> CircleContentActivity.launch(item));

        RetrofitFactory2.getRetrofit(HttpUri.SERVER_URL).create(ICircle.class).getComment(item.getArticleId())
                .flatMap(new Function<CommentBean, ObservableSource<CommentListTextView.CommentInfo>>() {
                    @Override
                    public ObservableSource<CommentListTextView.CommentInfo> apply(CommentBean commentBean) throws Exception {

                        List<CommentListTextView.CommentInfo> comments = new ArrayList<>();
                        for(CommentBean.DataBean.ListBean comment: commentBean.getData().getList()) {
                            comments.add(new CommentListTextView.CommentInfo()
                                .setID(comment.getCommentId())
                                    .setComment(comment.getContent())
                                    .setNickname(comment.getNickname())
                                    .setTonickname(item.getAuthor())

                            );
                        }

                        return Observable.fromIterable(comments);
                    }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list->{
                    if(list.size() > 0){
                        holder.commentListTextView.setData(list);
                        praiseInfos.add(new PraiseTextView.PraiseInfo ().setId (111).setNickname ("张三").setLogo ("http://lujianchao.com/images/headimg/1.jpg"));
                        praiseInfos.add(new PraiseTextView.PraiseInfo ().setId (1112).setNickname ("张三1").setLogo ("http://lujianchao.com/images/headimg/1.jpg"));
                        praiseInfos.add(new PraiseTextView.PraiseInfo ().setId (1113).setNickname ("张三2").setLogo ("http://lujianchao.com/images/headimg/1.jpg"));
                        praiseInfos.add(new PraiseTextView.PraiseInfo ().setId (1114).setNickname ("张三3").setLogo ("http://lujianchao.com/images/headimg/1.jpg"));
                        holder.praiseTextView.setData(praiseInfos);
                        holder.commentListTextView.setVisibility(View.VISIBLE);
                        holder.praiseTextView.setVisibility(View.VISIBLE);
                    }

                });


    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView profile_image;

        private TextView nickname;

        private ExpandableTextView etv;

        private MultiImageViewLayout multi_image;

        private TextView tv_views;

        private TextView timer;

        private CommentListTextView commentListTextView;

        private PraiseTextView praiseTextView;

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
            this.multi_image.setOnItemClickListener(new MultiImageViewLayout.OnItemClickListener() {
                @Override
                public void onItemClick(View view,List<String> urls, int PressImagePosition, float PressX, float PressY) {
                    ImageBrowserActivity.start(XQWApplication.appContext,urls.get(PressImagePosition), (ArrayList<String>) urls);
                }

                @Override
                public void onItemLongClick(View view, int PressImagePosition, float PressX, float PressY) {

                }
            });

            // 点赞
            this.praiseTextView = itemView.findViewById(R.id.praise_view);
            praiseTextView.setNameTextColor (Color.BLUE);//设置名字字体颜色
            praiseTextView.setIcon (R.drawable.ic_favorite_white_24dp);//设置图标
            praiseTextView.setMiddleStr ("、");//设置分割文本
            praiseTextView.setIconSize (new Rect(0,0,50,50));//设置图标大小，默认与字号匹配
            praiseTextView.setonPraiseListener (new PraiseTextView.onPraiseClickListener () {
                @Override
                public void onClick (final int position, final PraiseTextView.PraiseInfo mPraiseInfo) {
                    Log.i(TAG, "position = [" + position + "], mPraiseInfo = [" + mPraiseInfo + "]"+"\r\n");
                }

                @Override
                public void onOtherClick () {
                    Log.i(TAG, "onOtherClick"+"\r\n");
                }
            });


            // 评论
            this.commentListTextView = itemView.findViewById(R.id.comment_list);
            this.commentListTextView.setMaxlines (6);
            this.commentListTextView.setMoreStr ("查看更多");
            this.commentListTextView.setNameColor (Color.parseColor ("#fe671e"));
            this.commentListTextView.setCommentColor (Color.parseColor ("#242424"));
            this.commentListTextView.setTalkStr ("回复");
            this.commentListTextView.setTalkColor (Color.parseColor ("#242424"));


            this.commentListTextView.setonCommentListener (new CommentListTextView.onCommentListener () {


                @Override
                public void onNickNameClick (final int position, final CommentListTextView.CommentInfo mInfo) {
                    Log.i(TAG, "onNickNameClick  position = [" + position + "], mInfo = [" + mInfo + "]" + "\r\n");
                }

                @Override
                public void onToNickNameClick (final int position, final CommentListTextView.CommentInfo mInfo) {
                    Log.i(TAG, "onToNickNameClick  position = [" + position + "], mInfo = [" + mInfo + "]" + "\r\n");
                }

                @Override
                public void onCommentItemClick (final int position, final CommentListTextView.CommentInfo mInfo) {
                    Log.i(TAG, "onCommentItemClick  position = [" + position + "], mInfo = [" + mInfo + "]" + "\r\n");
                }

                @Override
                public void onOtherClick () {
                    Log.i(TAG, "onOtherClick" + "\r\n");
                }
            });

        }
    }
}
