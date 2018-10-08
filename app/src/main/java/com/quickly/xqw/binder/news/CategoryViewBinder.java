package com.quickly.xqw.binder.news;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.pixelcan.inkpageindicator.InkPageIndicator;
import com.quickly.xqw.R;
import com.quickly.xqw.adapter.CategoryAdapter;
import com.quickly.xqw.adapter.CategoryView;
import com.quickly.xqw.api.HttpUri;
import com.quickly.xqw.api.ICategoryApi;
import com.quickly.xqw.model.news.CategoryBean;
import com.quickly.xqw.utils.RetrofitFactory;

import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.multitype.ItemViewBinder;

public class CategoryViewBinder extends ItemViewBinder<CategoryBean.DataBean.ListBean,CategoryViewBinder.ViewHolder> {


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_category_content,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CategoryBean.DataBean.ListBean item) {
        RetrofitFactory.getRetrofit(HttpUri.SERVER_IP).create(ICategoryApi.class).getNewsCategory()
                .map(data->data.getData().getList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resp->holder.setCategoryData(resp));

    }

   static class ViewHolder extends RecyclerView.ViewHolder {

        private ViewPager mViewPager;

        private InkPageIndicator mInkPageIndicator;


        ViewHolder(View itemView) {
            super(itemView);
            this.mViewPager =itemView.findViewById(R.id.view_pager);
            this.mInkPageIndicator = itemView.findViewById(R.id.indicator);


        }

        private void setCategoryData(List<CategoryBean.DataBean.ListBean> data){
            mViewPager.setAdapter(new CategoryAdapter(
                    Arrays.asList(
                            new CategoryView(itemView.getContext(),data.subList(0,8)),
                            new CategoryView(itemView.getContext(),data.subList(8,data.size())
                            )
                    )
            ));
            mInkPageIndicator.setViewPager(mViewPager);
        }
    }
}
