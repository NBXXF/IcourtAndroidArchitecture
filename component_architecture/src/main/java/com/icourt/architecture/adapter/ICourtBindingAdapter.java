package com.icourt.architecture.adapter;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.2.2
 * @Description
 * @Company Beijing icourt
 * @date createTime：2017/12/2
 */
public abstract class ICourtBindingAdapter<VDB extends ViewDataBinding, T>
        extends ICourtBaseAdapter<ICourtBindingViewHolder<VDB, T>>
        implements
        IICourtItemClickAdapter<VDB, T>,
        IICourtHeaderAdapter, IICourtFooterAdapter {
    private static final int HEADER_VIEW_TYPE = -10000;
    private static final int FOOTER_VIEW_TYPE = -20000;
    final SparseArray<ViewDataBinding> headerSparseArray = new SparseArray<>();
    final SparseArray<ViewDataBinding> footerSparseArray = new SparseArray<>();

    private List<T> dataList = new ArrayList<T>();

    public List<T> getData() {
        return dataList;
    }

    public int getDataSize() {
        return getData().size();
    }

    public boolean isDataEmpty() {
        return getDataSize() <= 0;
    }


    OnItemClickListener<VDB, T> onItemClickListener;
    OnItemLongClickListener<VDB, T> onItemLongClickListener;

    public ICourtBindingAdapter(@NonNull List<T> data) {
        this.dataList = data == null ? new ArrayList<T>() : data;
    }

    public ICourtBindingAdapter() {
        this(new ArrayList<T>());
    }

    @SuppressLint("Range")
    private boolean isFooter(int viewType) {
        return viewType >= FOOTER_VIEW_TYPE && viewType < (FOOTER_VIEW_TYPE + getFooterCount());
    }

    @SuppressLint("Range")
    private boolean isHeader(int viewType) {
        return viewType >= HEADER_VIEW_TYPE && viewType < (HEADER_VIEW_TYPE + getHeaderCount());
    }

    /**
     * @param isRefresh 是否下拉刷新
     * @param datas
     * @return
     */
    public boolean bindData(boolean isRefresh, @NonNull List<T> datas) {
        if (isRefresh) {//下拉刷新
            getData().clear();
            if (checkList(datas)) {
                getData().addAll(datas);
            }
            notifyDataSetChanged();
            return true;
        } else {
            //上拉加载 不能为空,并且不包含
            if (checkList(datas)
                    && !getData().contains(datas)) {
                getData().addAll(datas);
                notifyDataSetChanged();
                return true;
            }
        }
        return false;
    }

    public void clearData() {
        getData().clear();
        notifyDataSetChanged();
    }

    /**
     * 检查index是否有效
     *
     * @param index
     * @return true 有效 false 无效
     */
    private boolean checkIndex(@IntRange(from = 0) int index) {
        return index >= 0 && index < dataList.size();
    }

    /**
     * 检查add index是否有效
     *
     * @param addIndex
     * @return true 有效 false 无效
     */
    private boolean checkAddIndex(@IntRange(from = 0) int addIndex) {
        return addIndex >= 0 && addIndex <= dataList.size();
    }

    /**
     * 检查list是否有效
     *
     * @param datas
     * @return true 不空
     */
    private boolean checkList(List<? extends T> datas) {
        return datas != null && !datas.isEmpty();
    }

    /**
     * 检查对象是否有效
     *
     * @return true 不空
     */
    private boolean checkItem(T t) {
        return t != null;
    }

    /**
     * @param index 相对于List的位置
     * @return
     */
    public T getItem(@IntRange(from = 0) int index) {
        if (checkIndex(index)) {
            return getData().get(index);
        }
        return null;
    }

    /**
     * 获取角标
     *
     * @param t
     * @return
     */
    public int getIndex(@NonNull T t) {
        return getData().indexOf(t);
    }

    /**
     * @param index 相对于List的位置
     * @param t
     * @return
     */
    public final boolean addItem(@IntRange(from = 0) int index, @Nullable T t) {
        if (checkAddIndex(index)
                && checkItem(t)
                && !getData().contains(t)) {
            getData().add(index, t);
            int internalPosition = index + getHeaderCount();
            notifyItemInserted(internalPosition);
            return true;
        }
        return false;
    }

    public final boolean addItems(@IntRange(from = 0) int index, @NonNull List<? extends T> datas) {
        if (checkList(datas)
                && checkAddIndex(index)
                && !getData().containsAll(datas)) {
            if (getData().addAll(index, datas)) {
                int internalPosition = index + getHeaderCount();
                notifyItemRangeInserted(internalPosition, datas.size());
                return true;
            }
        }
        return false;
    }

    public final boolean addItems(@NonNull List<? extends T> datas) {
        if (checkList(datas)
                && !getData().containsAll(datas)) {
            int internalPosition = getData().size() + getHeaderCount();
            if (getData().addAll(datas)) {
                notifyItemRangeInserted(internalPosition, datas.size());
                return true;
            }
        }
        return false;
    }

    public final boolean addItem(@NonNull T t) {
        if (checkItem(t)
                && !getData().contains(t)) {
            int internalPosition = getData().size() + getHeaderCount();
            if (getData().add(t)) {
                notifyItemInserted(internalPosition);
            }
        }
        return false;
    }

    /**
     * 更新item
     *
     * @param t
     * @return
     */
    public final boolean updateItem(@NonNull T t) {
        if (checkItem(t)) {
            int index = getIndex(t);
            if (index >= 0) {
                getData().set(index, t);
                int internalPosition = index + getHeaderCount();
                notifyItemChanged(internalPosition);
                return true;
            }
        }
        return false;
    }

    /**
     * @param index 相对于List的位置
     * @return
     */
    public final boolean removeItem(@IntRange(from = 0) int index) {
        if (checkIndex(index)) {
            getData().remove(index);
            int internalPosition = index + getHeaderCount();
            notifyItemRemoved(internalPosition);
            return true;
        }
        return false;
    }


    /**
     * 移除item
     *
     * @param t
     * @return
     */
    public final boolean removeItem(@NonNull T t) {
        return removeItem(getIndex(t));
    }


    /**
     * 创建binding
     *
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    protected VDB onCreateBinding(ViewGroup viewGroup, int viewType) throws Exception {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        Class<VDB> bindingClass = (Class<VDB>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Method method = bindingClass.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
        return (VDB) method.invoke(null, from, viewGroup, false);
    }


    /**
     * 初始化item
     *
     * @param holder
     * @param t
     * @param index  相对于List的位置
     */
    public abstract void onBindHolder(ICourtBindingViewHolder<VDB, T> holder, @Nullable T t, int index);

    @Override
    public final ICourtBindingViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (isHeader(viewType)) {
            int whichHeader = Math.abs(viewType - HEADER_VIEW_TYPE);
            return new ICourtBindingViewHolder(this, getHeader(whichHeader), false);
        } else if (isFooter(viewType)) {
            int whichFooter = Math.abs(viewType - FOOTER_VIEW_TYPE);
            return new ICourtBindingViewHolder(this, getFooter(whichFooter), false);
        } else {
            VDB vdb = null;
            try {
                vdb = onCreateBinding(viewGroup, viewType);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ICourtBindingViewHolder<VDB, T>(this, vdb, true);
        }
    }

    @Override
    public final void onBindViewHolder(ICourtBindingViewHolder<VDB, T> holder, int position) {
        if (position >= getHeaderCount() && position < getHeaderCount() + getData().size()) {
            int index = position - getHeaderCount();
            onBindHolder(holder, getItem(index), index);
        }
    }


    /**
     * 每条布局的type
     *
     * @param index 相对于List的位置
     * @return
     */
    public int getViewType(int index) {
        return 0;
    }

    @Override
    public final int getItemViewType(int position) {
        if (position < getHeaderCount()) {
            return HEADER_VIEW_TYPE + position;
        } else if (position < (getHeaderCount() + getData().size())) {
            return getViewType(position - getHeaderCount());
        } else {
            return FOOTER_VIEW_TYPE + position - getHeaderCount() - getData().size();
        }
    }

    @Override
    public int getItemCount() {
        return getHeaderCount() + getData().size() + getFooterCount();
    }


    @Nullable
    @Override
    public OnItemClickListener<VDB, T> getItemClickListener() {
        return onItemClickListener;
    }

    @Override
    public void setOnItemClickListener(@Nullable OnItemClickListener<VDB, T> l) {
        this.onItemClickListener = l;
    }

    @Nullable
    @Override
    public OnItemLongClickListener<VDB, T> getItemLongClickListener() {
        return onItemLongClickListener;
    }

    @Override
    public void setOnItemLongClickListener(@Nullable OnItemLongClickListener<VDB, T> l) {
        this.onItemLongClickListener = l;
    }


    @Override
    public int getHeaderCount() {
        return headerSparseArray.size();
    }

    @Nullable
    @Override
    public <VDB extends ViewDataBinding> VDB addHeader(@NonNull VDB vdb) {
        if (vdb != null) {
            headerSparseArray.put(getHeaderCount(), vdb);
        }
        return vdb;
    }

    @Override
    public void removeHeader(int index) {
        headerSparseArray.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public <VDB extends ViewDataBinding> void removeHeader(@NonNull VDB vdb) {
        if (vdb != null) {
            int index = headerSparseArray.indexOfValue(vdb);
            if (index >= 0) {
                headerSparseArray.removeAt(index);
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public void removeAllHeader() {
        if (headerSparseArray.size() > 0) {
            headerSparseArray.clear();
            notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public ViewDataBinding getHeader(int index) {
        return headerSparseArray.get(index);
    }

    @Nullable
    @Override
    public <VDB extends ViewDataBinding> VDB addHeader(int id, @NonNull RecyclerView recyclerView) {
        VDB vdb = DataBindingUtil.inflate(LayoutInflater.from(recyclerView.getContext()), id, recyclerView, false);
        return addHeader(vdb);
    }


    @Override
    public int getFooterCount() {
        return footerSparseArray.size();
    }

    @Nullable
    @Override
    public <VDB extends ViewDataBinding> VDB addFooter(@NonNull VDB vdb) {
        if (vdb != null) {
            footerSparseArray.put(getFooterCount(), vdb);
        }
        return vdb;
    }

    @Override
    public void removeFooter(int index) {
        footerSparseArray.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public <VDB extends ViewDataBinding> void removeFooter(@NonNull VDB vdb) {
        if (vdb != null) {
            int index = footerSparseArray.indexOfValue(vdb);
            if (index >= 0) {
                footerSparseArray.removeAt(index);
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public void removeAllFooter() {
        if (footerSparseArray.size() > 0) {
            footerSparseArray.clear();
            notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public ViewDataBinding getFooter(int index) {
        return footerSparseArray.get(index);
    }

    @Nullable
    @Override
    public <VDB extends ViewDataBinding> VDB addFooter(int id, @NonNull RecyclerView recyclerView) {
        VDB vdb = DataBindingUtil.inflate(LayoutInflater.from(recyclerView.getContext()), id, recyclerView, false);
        return addFooter(vdb);
    }
}
