package com.icourt.architecture.binding;

import android.support.annotation.Nullable;

/**
 * Description  加载对话框定义
 * Company Beijing icourt
 * author  youxuan  E-mail:xuanyouwu@163.com
 * date createTime：17/2/4
 * version
 */

public interface IICourtProgressHUD {

    /**
     * 展示加载对话框
     *
     * @param notice
     */
    void showICourtLoadingDialog(@Nullable String notice);

    /**
     * 结束展示对话框
     */
    void dismissICourtLoadingDialog();

    /**
     * 加载成功的提示
     *
     * @param notice
     */
    void dismissICourtLoadingDialogWithSuccess(String notice);

    /**
     * 加载失败的提示
     *
     * @param notice
     */
    void dismissICourtLoadingDialogWithFail(String notice);

    /**
     * 是否正在展示加载对话框
     *
     * @return
     */
    boolean isShowICourtLoading();
}
