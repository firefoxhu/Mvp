package com.quickly.xqw.module.base2;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.quickly.xqw.R;
import com.quickly.xqw.R2;
import com.quickly.xqw.utils.DiffCallback;
import com.roger.catloadinglibrary.CatLoadingView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import java.util.List;
import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public abstract class BaseSmartListFragment<T extends IBasePresenter> extends CommonFragment<T> implements IBaseListView<T>,OnRefreshLoadMoreListener {


    @BindView(R2.id.smart_refresh_layout)
    protected SmartRefreshLayout mSmartRefreshLayout;

    @BindView(R2.id.recycler_view)
    protected RecyclerView mRecyclerView;

    protected Toolbar mToolbar;

    protected TextView mTitle;


    private Items mItems;

    protected MultiTypeAdapter mMultiTypeAdapter;

//    private CatLoadingView mCatLoadingView = new CatLoadingView();

    /**
     * 设置recycler 的布局管理器
     * @return
     */
    protected abstract RecyclerView.LayoutManager getLayoutManager();


    /**
     * 是否启用下拉刷新
     * @return
     */
    protected abstract boolean enableRefresh();

    /**
     * 设置标题
     */
    protected abstract String getTitle();

    protected abstract void registerAdapter();


    @Override
    protected int attachLayoutId() {
        return  hasToolbar() ? R.layout.base_smart_list_fragment : R.layout.base_smart_list_no_toolbar_fragment;
    }

    @Override
    protected void initView(View view) {

        if(hasToolbar()){
            mToolbar  = view.findViewById(R.id.toolbar);
            mTitle = view.findViewById(R.id.toolbar_title);
            mTitle.setText(getTitle());
            this.initToolBar(mToolbar,false,"");
        }

        // 设置recycler 布局管理器
        mRecyclerView.setLayoutManager(getLayoutManager());

        mSmartRefreshLayout.setEnableRefresh(enableRefresh());
        mSmartRefreshLayout.setEnableAutoLoadMore(true);

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(this);


    }

    @Override
    protected void initData() {
        mItems = new Items();
        mMultiTypeAdapter = new MultiTypeAdapter(mItems);
        this.registerAdapter();
        mRecyclerView.setAdapter(mMultiTypeAdapter);

//        mCatLoadingView.show(getChildFragmentManager(), "正在加载中...");
//        mCatLoadingView.setCanceledOnTouchOutside(false);

        mSmartRefreshLayout.autoRefresh();
    }


    @Override
    public void onSetAdapter(List<?> list) {
        Items newItems  = new Items(list);
        DiffCallback.create(mItems,newItems,mMultiTypeAdapter);
        mItems.clear();
        mItems.addAll(newItems);

        // 停止滑动
        mRecyclerView.stopScroll();
    }

    @Override
    public void onShowSuccess() {
//        if(mCatLoadingView !=null && !mCatLoadingView.isHidden()) {
//            mCatLoadingView.dismiss();
//            mCatLoadingView = null;
//        }
    }


    private int reloadTimes = 0;

    @Override
    public void onShowNetError() {

        Toast.makeText(mContext, "加载失败了？", Toast.LENGTH_SHORT).show();

//        if(mCatLoadingView != null) {
//            mCatLoadingView.dismiss();
//            mCatLoadingView = null;
//        }

        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadMore();

//        if(reloadTimes > 3) {
//            Toast.makeText(mContext, "彻底加载失败！", Toast.LENGTH_SHORT).show();
//            if(mCatLoadingView != null && !mCatLoadingView.isHidden()){
//                mCatLoadingView.dismiss();
//            }
//            mCatLoadingView = null;
//            return;
//        }
//        Toast.makeText(mContext, "正在重新加载中...", Toast.LENGTH_SHORT).show();
//        ++reloadTimes;
//        // 从新加载
//        if(mCatLoadingView == null){
//            mCatLoadingView = new CatLoadingView();
//        }
//
//        mSmartRefreshLayout.finishRefresh();
//        mSmartRefreshLayout.autoRefresh();
//        mCatLoadingView.setText("尝试第【"+reloadTimes+"】重新加载...");

    }
}
