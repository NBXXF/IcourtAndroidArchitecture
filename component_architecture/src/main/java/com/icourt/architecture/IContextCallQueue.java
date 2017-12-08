package com.icourt.architecture;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Description  call请求队列 单个页面 eg.activity fragment
 * Company Beijing icourt
 * author  youxuan  E-mail:xuanyouwu@163.com
 * date createTime：2017/8/30
 * version 2.1.0
 */
public interface IContextCallQueue {

    /**
     * 执行call请求
     *
     * @param call
     * @param callback
     * @param <T>
     * @return
     */
    <T> Call<T> callEnqueue(@NonNull Call<T> call, Callback<T> callback);

    /**
     * 暂停所有请求
     */
    void cancelAllCall();

    /**
     * 暂停单个请求
     *
     * @param call
     * @param <T>
     */
    <T> void cancelCall(@NonNull Call<T> call);
}
