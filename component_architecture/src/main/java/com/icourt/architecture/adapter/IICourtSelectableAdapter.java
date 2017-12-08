package com.icourt.architecture.adapter;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.2.2
 * @Description
 * @Company Beijing icourt
 * @date createTime：2017/12/1
 */
public interface IICourtSelectableAdapter {
    /**
     * 是否可以选择
     *
     * @return
     */
    boolean isSelectable();

    /**
     * 是否是多选
     *
     * @return
     */
    boolean isMultiSelect();


    /**
     * 是否是单选
     *
     * @return
     */
    boolean isSingleSelect();
}
