package com.quickly.xqw.module.base2;

public interface IBasePresenter{

    /**
     * 请求数据
     * @param args
     */
    void doInitData(String... args);

    /**
     * 加载成功
     */
    void doShowSuccess();

    /**
     * 加载失败
     */
    void doShowError();

}
