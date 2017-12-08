package com.icourt.architecture.activity;

import android.content.Context;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.icourt.architecture.CallQueuePresenter;
import com.icourt.architecture.IContextCallQueue;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.2.2
 * @Description
 * @Company Beijing icourt
 * @date createTime：2017/12/3
 */
public class ICourtHttpActivity extends RxAppCompatActivity implements IContextCallQueue {
    final CallQueuePresenter callQueuePresenter = new CallQueuePresenter();


    public ICourtHttpActivity getActivity() {
        return this;
    }

    public Context getContext() {
        return this;
    }

    @Override
    public <T> Call<T> callEnqueue(@NonNull Call<T> call, Callback<T> callback) {
        if (isDestroyOrFinishing()) {
            return call;
        }
        return callQueuePresenter.callEnqueue(call, callback);
    }

    @Override
    public void cancelAllCall() {
        callQueuePresenter.cancelAllCall();
    }

    @Override
    public <T> void cancelCall(@NonNull Call<T> call) {
        callQueuePresenter.cancelCall(call);
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        cancelAllCall();
        super.onDestroy();
    }

    /**
     * activity是否销毁或者即将销毁
     *
     * @return
     */
    protected final boolean isDestroyOrFinishing() {
        boolean destroyed = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 &&
                isDestroyed();
        return destroyed || isFinishing();
    }
}
