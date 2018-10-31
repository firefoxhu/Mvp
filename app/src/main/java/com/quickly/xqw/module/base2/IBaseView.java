package com.quickly.xqw.module.base2;
import com.uber.autodispose.AutoDisposeConverter;

public interface IBaseView<T> {

    /**
     * 加载成功
     */
    void onShowSuccess();

    /**
     * 显示网络错误
     */
    void onShowNetError();

    /**
     * 设置 presenter
     */
    void setPresenter(T presenter);

    /**
     * 绑定生命周期
     */
    <X> AutoDisposeConverter<X> bindAutoDispose();

}
