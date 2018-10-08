package com.quickly.xqw.binder.news;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.quickly.xqw.R;
import com.quickly.xqw.adapter.GlideImageLoader;
import com.quickly.xqw.model.news.BannerBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import me.drakeet.multitype.ItemViewBinder;

public class BannerViewBinder extends ItemViewBinder<BannerBean,BannerViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_banner_content,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull BannerBean item) {
        holder.setUrls(item);
    }
    static class ViewHolder extends RecyclerView.ViewHolder {

        private Banner mBanner;

        ViewHolder(View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.banner);
            mBanner.setOnBannerListener(position -> Toast.makeText(itemView.getContext(), "点击了banner："+position, Toast.LENGTH_SHORT).show());
        }

        private void  setUrls(BannerBean bannerBean) {
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            mBanner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            mBanner.setImages(bannerBean.getUrls());
            //设置banner动画效果
            mBanner.setBannerAnimation(Transformer.DepthPage);
            //设置标题集合（当banner样式有显示title时）
            mBanner.setBannerTitles(bannerBean.getUrls());

            //设置自动轮播，默认为true
            mBanner.isAutoPlay(true);
            //设置轮播时间
            mBanner.setDelayTime(3000);
            //设置指示器位置（当banner模式中有指示器时）
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            mBanner.start();
        }
    }

}
