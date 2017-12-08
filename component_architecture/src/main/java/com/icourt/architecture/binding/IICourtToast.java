package com.icourt.architecture.binding;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.view.View;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.2.2
 * @Description
 * @Company Beijing icourt
 * @date createTime：2017/12/5
 */
public interface IICourtToast {

    /**
     * Toast提示
     * 缺陷 有的rom 会禁用掉taost 比如huawei rom
     *
     * @param notice
     */
    @UiThread
    void showICourtToast(@NonNull CharSequence notice);

    /**
     * Toast提示
     * 缺陷 有的rom 会禁用掉taost 比如huawei rom
     *
     * @param notice
     */
    @UiThread
    void showICourtToast(@StringRes int notice);


    /**
     * toast是否可用
     *
     * @return
     */
    boolean isToastAvailable();

    /**
     * 顶部的snackBar
     *
     * @param notice
     */
    @UiThread
    void showICourtTopSnackBar(@NonNull CharSequence notice);


    /**
     * 顶部的snackBar
     *
     * @param notice
     */
    @UiThread
    void showICourtTopSnackBar(@StringRes int notice);


    /**
     * 顶部的snackBar
     *
     * @param notice
     * @param view
     */
    @UiThread
    void showICourtTopSnackBar(@NonNull View view, @NonNull CharSequence notice);


    /**
     * 顶部的snackBar
     *
     * @param notice
     * @param view
     */
    @UiThread
    void showICourtTopSnackBar(@NonNull View view, @StringRes int notice);


    /**
     * 底部的snackBar android默认在底部
     *
     * @param notice
     */
    @UiThread
    void showICourtBottomSnackBar(@NonNull CharSequence notice);


    /**
     * 底部的snackBar android默认在底部
     *
     * @param notice
     */
    @UiThread
    void showICourtBottomSnackBar(@StringRes int notice);


    /**
     * 底部的snackBar android默认在底部
     *
     * @param view
     * @param notice
     */
    @UiThread
    void showICourtBottomSnackBar(@NonNull View view, @NonNull CharSequence notice);


    /**
     * 底部的snackBar android默认在底部
     *
     * @param view
     * @param notice
     */
    @UiThread
    void showICourtBottomSnackBar(@NonNull View view, @StringRes int notice);

}
