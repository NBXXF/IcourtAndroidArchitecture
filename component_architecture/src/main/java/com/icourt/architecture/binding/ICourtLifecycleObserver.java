package com.icourt.architecture.binding;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.2.2
 * @Description 组件的生命周期
 * @Company Beijing icourt
 * @date createTime：2017/12/6
 */
public interface ICourtLifecycleObserver extends LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onComponentLifecycleAny(LifecycleOwner owner, Lifecycle.Event event);
}
