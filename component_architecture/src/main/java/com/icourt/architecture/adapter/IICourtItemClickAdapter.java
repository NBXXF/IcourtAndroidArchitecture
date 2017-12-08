package com.icourt.architecture.adapter;

import android.databinding.ViewDataBinding;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.2.2
 * @Description
 * @Company Beijing icourt
 * @date createTime：2017/12/3
 */
public interface IICourtItemClickAdapter<VDB extends ViewDataBinding, T> {

    @Nullable
    @CheckResult
    OnItemClickListener<VDB, T> getItemClickListener();

    void setOnItemClickListener(@Nullable OnItemClickListener<VDB, T> l);

    @Nullable
    @CheckResult
    OnItemLongClickListener<VDB, T> getItemLongClickListener();

    void setOnItemLongClickListener(@Nullable OnItemLongClickListener<VDB, T> l);
}
