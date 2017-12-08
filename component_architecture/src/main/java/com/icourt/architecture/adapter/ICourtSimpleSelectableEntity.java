package com.icourt.architecture.adapter;

import com.google.gson.annotations.Expose;

/**
 * Description  带选中属性的item实体
 * Company Beijing icourt
 * author  youxuan  E-mail:xuanyouwu@163.com
 * date createTime：2017/10/5
 * version 2.1.0
 */
public class ICourtSimpleSelectableEntity implements IICourtSelectableEntity {

    /**
     * 本地用
     */
    @Expose(serialize = false, deserialize = false)
    private transient boolean isAndroidLocal2017ItemSelected;

    @Override
    public boolean isItemSelected() {
        return isAndroidLocal2017ItemSelected;
    }

    @Override
    public void setItemSelect(boolean b) {
        isAndroidLocal2017ItemSelected = b;
    }

    @Override
    public void toggleItemSelect() {
        isAndroidLocal2017ItemSelected = !isAndroidLocal2017ItemSelected;
    }
}
