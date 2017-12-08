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
public interface IICourtHeaderAdapter {
    @IntRange(from = 0)
    int getHeaderCount();

    @Nullable
    @CheckResult
    <VDB extends ViewDataBinding> VDB addHeader(@NonNull VDB vdb);


    void removeHeader(@IntRange(from = 0) int index);


    <VDB extends ViewDataBinding> void removeHeader(@NonNull VDB vdb);


    void removeAllHeader();

    @Nullable
    @CheckResult
    ViewDataBinding getHeader(@IntRange(from = 0) int index);

    @Nullable
    @CheckResult
    <VDB extends ViewDataBinding> VDB addHeader(@LayoutRes int id, @NonNull RecyclerView recyclerView);
}
