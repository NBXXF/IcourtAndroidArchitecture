package com.icourt.architecture.adapter;

import android.databinding.ViewDataBinding;
import android.support.annotation.CheckResult;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.2.2
 * @Description
 * @Company Beijing icourt
 * @date createTime：2017/12/3
 */
public interface IICourtFooterAdapter {
    @IntRange(from = 0)
    int getFooterCount();

    @Nullable
    @CheckResult
    <VDB extends ViewDataBinding> VDB addFooter(@NonNull VDB vdb);


    void removeFooter(@IntRange(from = 0) int index);


    <VDB extends ViewDataBinding> void removeFooter(@NonNull VDB vdb);


    void removeAllFooter();

    @Nullable
    @CheckResult
    ViewDataBinding getFooter(@IntRange(from = 0) int index);

    @Nullable
    @CheckResult
    <VDB extends ViewDataBinding> VDB addFooter(@LayoutRes int id, @NonNull RecyclerView recyclerView);
}
