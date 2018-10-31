package com.quickly.xqw.module.base2;

import java.util.List;

public interface IBaseListView<T> extends IBaseView<T>{

    /**
     * 设置适配器
     */
    void onSetAdapter(List<?> list);

}
