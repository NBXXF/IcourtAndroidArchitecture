package com.icourt.architecture.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.2.2
 * @Description
 * @Company Beijing icourt
 * @date createTime：2017/12/3
 */
public class ICourtBindingViewHolder<VDB extends ViewDataBinding, T> extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {

    private VDB binding;
    private ICourtBindingAdapter<VDB, T> adapter;

    public ICourtBindingAdapter<VDB, T> getAdapter() {
        return adapter;
    }

    public VDB getBinding() {
        return binding;
    }

    public ICourtBindingViewHolder(ICourtBindingAdapter<VDB, T> adapter, VDB vdb, boolean bindItemClick) {
        super(vdb.getRoot());
        this.adapter = adapter;
        this.binding = vdb;
        if (bindItemClick) {
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
    }

    /**
     * 是否是根布局点击
     *
     * @param v
     * @return
     */
    public final boolean isRootClick(View v) {
        return itemView == v;
    }

    @Override
    public void onClick(View v) {
        if (isRootClick(v) && adapter != null
                && adapter.getItemClickListener() != null) {
            adapter.getItemClickListener().onItemClick(adapter, this, v, 0);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (isRootClick(v) && adapter != null
                && adapter.getItemLongClickListener() != null) {
            return adapter.getItemLongClickListener().onItemLongClick(adapter, this, v, 0);
        }
        return false;
    }
}
