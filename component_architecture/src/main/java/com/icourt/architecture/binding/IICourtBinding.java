package com.icourt.architecture.binding;

import android.databinding.ViewDataBinding;

import java.lang.reflect.InvocationTargetException;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.2.2
 * @Description
 * @Company Beijing icourt
 * @date createTime：2017/12/4
 */
public interface IICourtBinding<T extends ViewDataBinding> {

    /**
     * @return 获取binding对象
     */
    T getBinding();

    /**
     * 创建binding
     *
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    T onCreateBinding() throws Exception;

    /**
     * 销毁binding
     */
    void onDestroyBinding();

}
