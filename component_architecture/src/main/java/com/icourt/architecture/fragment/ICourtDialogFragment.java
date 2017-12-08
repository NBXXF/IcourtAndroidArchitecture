package com.icourt.architecture.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icourt.architecture.binding.BaseActionHelper;
import com.icourt.architecture.binding.IICourtBinding;
import com.icourt.architecture.binding.IICourtProgressHUD;
import com.icourt.architecture.binding.IICourtToast;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.2.2
 * @Description
 * @Company Beijing icourt
 * @date createTime：2017/12/2
 */

public abstract class ICourtDialogFragment<T extends ViewDataBinding>
        extends ICourtHttpDialogFragment
        implements IICourtBinding<T>, IICourtToast, IICourtProgressHUD {

    private static final String TAG = ICourtDialogFragment.class.getSimpleName();
    private T binding;
    private BaseActionHelper baseActionHelper;


    /**
     * @return 获取binding对象
     */
    @Override
    public final T getBinding() {
        return binding;
    }


    @Override
    public T onCreateBinding() throws Exception {
        Class<T> bindingClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Method method = bindingClass.getMethod("inflate", LayoutInflater.class);
        return (T) method.invoke(null, getLayoutInflater());
    }

    /**
     * 初始化布局
     *
     * @param binding
     */
    protected abstract void initView(@NonNull T binding);


    /**
     * 获取数据  onrseume getData(true,false,false)
     *
     * @param isRefresh     是否刷新
     * @param isFromGesture 是否下拉／上拉加载
     * @param isFirstTime   是否是第一次调用
     */
    protected abstract void getData(boolean isRefresh, boolean isFromGesture, boolean isFirstTime);


    /**
     * 获取数据
     *
     * @param isRefresh
     * @param isFromGesture
     */
    protected final void getData(boolean isRefresh, boolean isFromGesture) {
        getData(isRefresh, isFromGesture, false);
    }

    /**
     * 获取数据
     *
     * @param isRefresh
     */
    protected final void getData(boolean isRefresh) {
        getData(isRefresh, false, false);
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            baseActionHelper = new BaseActionHelper(getContext());
            try {
                binding = onCreateBinding();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (binding == null) {
                Log.e(TAG, "page:" + getClass().getName() + " onCreateBinding null**********************");
                return super.onCreateView(getLayoutInflater(), container, savedInstanceState);
            }
            initView(binding);
            getData(true, false, true);
        } else {
            if (binding.getRoot() != null) {
                ViewGroup parent = (ViewGroup) binding.getRoot().getParent();
                if (parent != null) {
                    parent.removeView(binding.getRoot());
                }
            }
        }
        return binding.getRoot();
    }

    @Override
    public void onDestroyBinding() {
        if (binding != null) {
            binding.unbind();
        }
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        baseActionHelper.detachedContext();
        onDestroyBinding();
        super.onDestroyView();
    }


    @Override
    public void showICourtLoadingDialog(@Nullable String notice) {
        if (isDetached()) {
            return;
        }
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
