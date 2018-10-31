package com.quickly.xqw.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.quickly.xqw.R;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Meiji on 2017/5/31.
 */
@GlideModule
public class ImageLoader extends AppGlideModule {

    private static RequestOptions options = new RequestOptions().override(Target.SIZE_ORIGINAL)
            .diskCacheStrategy(DiskCacheStrategy.ALL);

    public static void loadCenterCrop(Context context, String url, ImageView view, int defaultResId) {
        if (SettingUtil.getInstance().getIsNoPhotoMode() && NetWorkUtil.isMobileConnected(context)) {
            view.setImageResource(defaultResId);
        } else {
            Glide.with(context)
                    .load(url)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().centerCrop())
                    .into(view);
        }
    }

    /**
     * 带加载异常图片
     */
    public static void loadCenterCrop(Context context, String url, ImageView view, int defaultResId, int errorResId) {
        if (SettingUtil.getInstance().getIsNoPhotoMode() && NetWorkUtil.isMobileConnected(context)) {
            view.setImageResource(defaultResId);
        } else {
            Glide.with(context)
                    .load(url)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().centerCrop().error(errorResId))
                    .into(view);
        }
    }

    /**
     * 带监听处理
     */
    public static void loadCenterCrop(Context context, String url, ImageView view, RequestListener listener) {
        Glide.with(context)
                .load(url)
                .transition(withCrossFade())
                .apply(new RequestOptions().centerCrop())
                .listener(listener)
                .into(view);
    }

    public static void loadNormal(Context context, String url, ImageView view) {
        Glide.with(context).load(url).into(view);
    }

    public static void load(Context context, String url, ImageView view) {
        Glide.with(context).load(url).thumbnail(0.1f).transition(withCrossFade()).apply(options).into(view);
    }

    /**
     * 显示本地虚化图片
     *
     * @param context   context
     * @param resId      resId
     * @param imageView 要显示的imageview
     */
    public static void displayBlurImg(Context context,int resId, ImageView imageView) {
        // "23":模糊度；"4":图片缩放4倍后再进行模糊
        Glide.with(context)
                .load(resId)
                .apply(
                        new RequestOptions()
                                .centerCrop().
                                error(R.drawable.stackblur_default).placeholder(R.drawable.stackblur_default)
                                .optionalTransform(new BlurTransformation(context, 1, 4)
                                )
                ).transition(withCrossFade(300))
                .into(imageView);
    }
}
