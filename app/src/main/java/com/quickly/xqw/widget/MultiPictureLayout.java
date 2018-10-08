package com.quickly.xqw.widget;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.quickly.xqw.ImageBrowserActivity;
import com.quickly.xqw.XQWApplication;
import com.quickly.xqw.module.news.content.NewsContentActivity;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MultiPictureLayout extends LinearLayout{

    private ArrayList<String> mUrls;

    private Context mContext;

    //  图片最多排列3行 每行最多3张 最多有9张
    private LinearLayout rowLayout01,rowLayout02,rowLayout03;
    private LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    private List<ImageView> mImageViewList = new ArrayList<>();




    public MultiPictureLayout(Context context) {
        super(context);
        mContext = context;
    }

    public MultiPictureLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }


    public void setList(ArrayList<String> list) {

        if (list == null) {
            throw new IllegalArgumentException("imageList is null...");
        }

        mUrls = list;

        this.initView();
    }


    private void initView() {

        if(mUrls.size() == 0){
            return;
        }

        this.setOrientation(VERTICAL);
        this.removeViews();
        this.mImageViewList.clear();

        if(mUrls.size() < 3){
            rowLayout01 =new LinearLayout(mContext);
            rowLayout01.setLayoutParams(layoutParams);
            rowLayout01.setOrientation(HORIZONTAL);
        }

        if(mUrls.size()  >= 3 && mUrls.size() < 6){
            rowLayout01 =new LinearLayout(mContext);
            rowLayout02 =new LinearLayout(mContext);
            rowLayout01.setOrientation(HORIZONTAL);
            rowLayout02.setOrientation(HORIZONTAL);
            rowLayout01.setLayoutParams(layoutParams);
            rowLayout02.setLayoutParams(layoutParams);
        }

        if(mUrls.size() >= 6 && mUrls.size() < 9){
            rowLayout01 =new LinearLayout(mContext);
            rowLayout02 =new LinearLayout(mContext);
            rowLayout03 =new LinearLayout(mContext);
            rowLayout01.setOrientation(HORIZONTAL);
            rowLayout02.setOrientation(HORIZONTAL);
            rowLayout03.setOrientation(HORIZONTAL);
            rowLayout01.setLayoutParams(layoutParams);
            rowLayout02.setLayoutParams(layoutParams);
            rowLayout03.setLayoutParams(layoutParams);
        }



        for(int i=0;i<mUrls.size();i++){
            LayoutParams tvLayoutParams = null;
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            switch (mUrls.size()){
                case 1:
                    tvLayoutParams = new LayoutParams(0, DensityUtil.dp2px(160), 1.0f);
                    break;
                case 2:
                    tvLayoutParams = new LayoutParams(0, DensityUtil.dp2px(140), 1.0f);
                    if(i == 1)
                        tvLayoutParams.leftMargin = DensityUtil.dp2px(10);
                    break;
                case 3:
                    tvLayoutParams = new LayoutParams(0, DensityUtil.dp2px(120), 1.0f);
                    if(i >= 1)
                        tvLayoutParams.leftMargin = DensityUtil.dp2px(5);
                    break;
                case 4: case 5:case 6:
                    tvLayoutParams = new LayoutParams(0, DensityUtil.dp2px(100), 1.0f);
                    tvLayoutParams.topMargin = DensityUtil.dp2px(2);
                    if((i >= 1 && i <= 2) || (i >= 4 && i <= 5 ))
                        tvLayoutParams.leftMargin = DensityUtil.dp2px(5);
                    break;
                case 7: case 8: case 9:
                    tvLayoutParams = new LayoutParams(0,DensityUtil.dp2px(80), 1.0f);
                    tvLayoutParams.topMargin = DensityUtil.dp2px(2);
                    if((i >= 1 && i <= 2) || (i >= 4 && i <= 5 ) || (i >= 7 && i <= 8))
                        tvLayoutParams.leftMargin = DensityUtil.dp2px(5);
                    break;
            }
            imageView.setLayoutParams(tvLayoutParams);
            imageView.setId(i);
            mImageViewList.add(imageView);
        }

        if(mUrls.size() != mImageViewList.size()){
            throw new IllegalArgumentException("系统内部处理错误！");
        }
        for (int i=0;i<mUrls.size();i++){
            Glide.with(mContext).load(mUrls.get(i)).thumbnail(0.1f).into(mImageViewList.get(i));
            if(i<3){
                rowLayout01.addView(mImageViewList.get(i));
            }else if(i<6){
                rowLayout02.addView(mImageViewList.get(i));
            }else if(i<9){
                rowLayout03.addView(mImageViewList.get(i));
            }

            int finalI = i;
            RxView.clicks(mImageViewList.get(i)).throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(o ->ImageBrowserActivity.start(XQWApplication.appContext, mUrls.get(finalI), mUrls));
        }

        if(rowLayout01 != null)
            this.addView(rowLayout01);

        if(rowLayout02 != null)
            this.addView(rowLayout02);
        if(rowLayout03 != null)
            this.addView(rowLayout03);

    }


    private void removeViews(){
        for (int i = 0; i < this.getChildCount(); i++) {
            if (this.getChildAt(i) instanceof ViewGroup) {
                ((ViewGroup) getChildAt(i)).removeAllViews();
            }
        }
        this.removeAllViews();
    }
}
