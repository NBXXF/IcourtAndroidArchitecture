package com.icourt.architecture.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.icourt.architecture.binding.BaseActionHelper;
import com.icourt.architecture.binding.IICourtProgressHUD;
import com.icourt.architecture.binding.IICourtToast;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.2.2
 * @Description
 * @Company Beijing icourt
 * @date createTime：2017/12/5
 */
public abstract class ICourtBaseAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> implements IICourtToast, IICourtProgressHUD {

    final BaseActionHelper baseActionHelper = new BaseActionHelper();
    @Nullable
    protected RecyclerView recyclerView;

    @CallSuper
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        baseActionHelper.attachContext(recyclerView.getContext());
        super.onAttachedToRecyclerView(recyclerView);
    }

    @CallSuper
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = null;
        baseActionHelper.detachedContext();
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void showICourtLoadingDialog(@Nullable String notice) {
        baseActionHelper.showICourtLoadingDialog(notice);
    }

    @Override
    public void dismissICourtLoadingDialog() {
        baseActionHelper.dismissICourtLoadingDialog();
    }

    @Override
    public void dismissICourtLoadingDialogWithSuccess(String notice) {
        baseActionHelper.dismissICourtLoadingDialogWithSuccess(notice);
    }

    @Override
    public void dismissICourtLoadingDialogWithFail(String notice) {
        baseActionHelper.dismissICourtLoadingDialogWithFail(notice);
    }

    @Override
    public boolean isShowICourtLoading() {
        return baseActionHelper.isShowICourtLoading();
    }

    @Override
    public void showICourtToast(@NonNull CharSequence notice) {
        baseActionHelper.showICourtToast(notice);
    }

    @Override
    public void showICourtToast(@StringRes int notice) {
        baseActionHelper.showICourtToast(notice);
    }

    @Override
    public boolean isToastAvailable() {
        return baseActionHelper.isToastAvailable();
    }

    @Override
    public void showICourtTopSnackBar(@NonNull CharSequence notice) {
        baseActionHelper.showICourtTopSnackBar(notice);
    }

    @Override
    public void showICourtTopSnackBar(int notice) {
        baseActionHelper.showICourtTopSnackBar(notice);
    }

    @Override
    public void showICourtTopSnackBar(@NonNull View view, @NonNull CharSequence notice) {
        baseActionHelper.showICourtTopSnackBar(view, notice);
    }

    @Override
    public void showICourtTopSnackBar(@NonNull View view, int notice) {
        baseActionHelper.showICourtTopSnackBar(view, notice);
    }

    @Override
    public void showICourtBottomSnackBar(@NonNull CharSequence notice) {
        baseActionHelper.showICourtBottomSnackBar(notice);
    }

    @Override
    public void showICourtBottomSnackBar(int notice) {
        baseActionHelper.showICourtBottomSnackBar(notice);
    }

    @Override
    public void showICourtBottomSnackBar(@NonNull View view, @NonNull CharSequence notice) {
        baseActionHelper.showICourtBottomSnackBar(view, notice);
    }

    @Override
    public void showICourtBottomSnackBar(@NonNull View view, int notice) {
        baseActionHelper.showICourtBottomSnackBar(view, notice);
    }
}
