package com.quickly.xqw.module.base;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.quickly.xqw.R;
import com.quickly.xqw.model.LoadingEndBean;
import com.quickly.xqw.utils.RxBus;
import com.quickly.xqw.utils.SettingUtil;
import io.reactivex.Observable;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by Meiji on 2017/6/10.
 */

public abstract class BaseListFragment<T extends IBasePresenter> extends LazyLoadFragment<T> implements IBaseListView<T>, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "BaseListFragment";
    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected MultiTypeAdapter adapter;
    protected Items oldItems = new Items();
    protected boolean canLoadMore = false;
    protected Observable<Integer> observable;

    @Override
    protected int attachLayoutId() {
        Log.i(TAG, "attachLayoutId: ");
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View view) {
        Log.i(TAG, "initView: ");
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(SettingUtil.getInstance().getColor());
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onShowLoading() {
        Log.i(TAG, "onShowLoading: ");
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void onHideLoading() {
        Log.i(TAG, "onHideLoading: ");
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void fetchData() {
        Log.i(TAG, "fetchData: ");
        observable = RxBus.getInstance().register(BaseListFragment.TAG);
        observable.subscribe(integer -> adapter.notifyDataSetChanged());
    }

    @Override
    public void onShowNetError() {
        Log.i(TAG, "onShowNetError: ");
        Toast.makeText(getActivity(), "网络异常！", Toast.LENGTH_SHORT).show();
        getActivity().runOnUiThread(() -> {
            adapter.setItems(new Items());
            adapter.notifyDataSetChanged();
            canLoadMore = false;
        });
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
        // 设置下拉刷新的按钮的颜色
        swipeRefreshLayout.setColorSchemeColors(SettingUtil.getInstance().getColor());
    }

    @Override
    public void onShowNoMore() {
        Log.i(TAG, "onShowNoMore: ");
        getActivity().runOnUiThread(() -> {
            if (oldItems.size() > 0) {
                Items newItems = new Items(oldItems);
                newItems.remove(newItems.size() - 1);
                newItems.add(new LoadingEndBean());
                adapter.setItems(newItems);
                adapter.notifyDataSetChanged();
            } else if (oldItems.size() == 0) {
                oldItems.add(new LoadingEndBean());
                adapter.setItems(oldItems);
                adapter.notifyDataSetChanged();
            }
            canLoadMore = false;
        });
    }

    @Override
    public void onRefresh() {
        Log.i(TAG, "onRefresh: ");
        int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

        Toast.makeText(getActivity(), "当前可见元素为弟："+ firstVisibleItemPosition, Toast.LENGTH_SHORT).show();
        if (firstVisibleItemPosition == 0) {
            presenter.doRefresh();
        }
        recyclerView.scrollToPosition(0);
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        RxBus.getInstance().unregister(BaseListFragment.TAG, observable);
        super.onDestroy();
    }
}