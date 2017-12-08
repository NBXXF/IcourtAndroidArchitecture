package com.icourt.architecture.adapter;

import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * Description
 * Company Beijing icourt
 * author  youxuan  E-mail:xuanyouwu@163.com
 * date createTime：2017/10/3
 * version 2.1.0
 */
public interface OnItemLongClickListener<VDB extends ViewDataBinding, T> {

    /**
     * @param adapter
     * @param holder
     * @param itemView
     * @param index    相对于List容器的位置
     * @return
     */
    boolean onItemLongClick(ICourtBindingAdapter<VDB, T> adapter, ICourtBindingViewHolder<VDB, T> holder, View itemView, int index);
}