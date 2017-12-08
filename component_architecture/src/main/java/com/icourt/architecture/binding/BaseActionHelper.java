package com.icourt.architecture.binding;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Toast;

import com.icourt.architecture.util.SnackbarUtils;
import com.icourt.architecture.util.ToastUtils;
import com.icourt.loading.AlphaLoading;

import java.util.concurrent.TimeUnit;

/**
 * Description  网络,对话框
 * Company Beijing icourt
 * author  youxuan  E-mail:xuanyouwu@163.com
 * date createTime：2017/10/7
 * version 2.1.0
 */
public class BaseActionHelper implements IICourtProgressHUD, IICourtToast {

    private Context actionContext;
    private Toast toast;

    @Nullable
    protected Context getActionContext() {
        return actionContext;
    }

    public BaseActionHelper() {
    }

    public BaseActionHelper(@NonNull Context context) {
        attachContext(context);
    }

    @UiThread
    public void attachContext(@NonNull Context context) {
        this.actionContext = context;
    }

    @UiThread
    public void detachedContext() {
        this.actionContext = null;
        dismissICourtLoadingDialog();
    }

    private AlphaLoading progressHUD;

    /**
     * 获取 菊花加载对话框
     *
     * @return
     */
    @NonNull
    protected final AlphaLoading getICourtProgressHUD() {
        if (progressHUD == null) {
            progressHUD = new AlphaLoading.Builder(actionContext)
                    .cancelable(false)                           // 是否可以手动取消(点击空白区域或返回键)
                    .resultDuration(TimeUnit.SECONDS.toMillis(1))                       // ok/fail持续时间(milliseconds)
                    .create();
        }
        return progressHUD;
    }


    /***
     *  展示加载对话框
     * @param notice
     */
    @UiThread
    @Override
    public void showICourtLoadingDialog(@Nullable String notice) {
        AlphaLoading currSVProgressHUD = getICourtProgressHUD();
        currSVProgressHUD.setMessage(notice);
        if (currSVProgressHUD.isFree()) {
            currSVProgressHUD.show();
        }
    }

    /**
     * 取消加载对话框
     */
    @UiThread
    @Override
    public void dismissICourtLoadingDialog() {
        if (isShowICourtLoading()) {
            progressHUD.dismissImmediately();
        }
    }

    @Override
    public void dismissICourtLoadingDialogWithSuccess(String notice) {
        if (isShowICourtLoading()) {
            progressHUD.dismissOk(notice);
        }
    }

    @Override
    public void dismissICourtLoadingDialogWithFail(String notice) {
        if (isShowICourtLoading()) {
            progressHUD.dismissFail(notice);
        }
    }

    /**
     * 加载对话框是否展示中
     *
     * @return
     */
    @Override
    public boolean isShowICourtLoading() {
        return progressHUD != null && !progressHUD.isFree();
    }

    @Override
    public void showICourtToast(@NonNull CharSequence notice) {
        if (toast == null) {
            toast = ToastUtils.showToast(actionContext, notice);
        } else {
            toast.setText(notice);
            toast.show();
        }
    }

    @Override
    public void showICourtToast(int notice) {
        if (toast == null) {
            toast = ToastUtils.showToast(actionContext, notice);
        } else {
            toast.setText(notice);
            toast.show();
        }
    }

    @Override
    public boolean isToastAvailable() {
        return ToastUtils.isToastAvailable(actionContext);
    }

    @Override
    public void showICourtTopSnackBar(@NonNull CharSequence notice) {
        if (getActionContext() instanceof Activity) {
            SnackbarUtils.showTopSnackBar((Activity) getActionContext(), notice);
        }
    }

    @Override
    public void showICourtTopSnackBar(int notice) {
        if (getActionContext() instanceof Activity) {
            SnackbarUtils.showTopSnackBar((Activity) getActionContext(), notice);
        }
    }

    @Override
    public void showICourtTopSnackBar(@NonNull View view, @NonNull CharSequence notice) {
        SnackbarUtils.showTopSnackBar(view, notice);
    }

    @Override
    public void showICourtTopSnackBar(@NonNull View view, int notice) {
        SnackbarUtils.showTopSnackBar(view, notice);
    }

    @Override
    public void showICourtBottomSnackBar(@NonNull CharSequence notice) {
        if (getActionContext() instanceof Activity) {
            SnackbarUtils.showBottomSnack((Activity) getActionContext(), notice);
        }
    }

    @Override
    public void showICourtBottomSnackBar(@StringRes int notice) {
        if (getActionContext() instanceof Activity) {
            SnackbarUtils.showBottomSnack((Activity) getActionContext(), notice);
        }
    }

    @Override
    public void showICourtBottomSnackBar(@NonNull View view, @NonNull CharSequence notice) {
        SnackbarUtils.showBottomSnack(view, notice);
    }

    @Override
    public void showICourtBottomSnackBar(@NonNull View view, @StringRes int notice) {
        SnackbarUtils.showBottomSnack(view, notice);
    }
}
