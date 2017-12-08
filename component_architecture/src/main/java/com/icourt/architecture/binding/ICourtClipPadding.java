package com.icourt.architecture.binding;

import android.support.annotation.Nullable;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.2.2
 * @Description
 * @Company Beijing icourt
 * @date createTime：2017/12/7
 */
public interface ICourtClipPadding {

    /**
     * @return
     */
    boolean clipToPadding();

    /**
     * px
     * 左上右下
     *
     * @return
     */
    @Nullable
    int[] paddings();

}
