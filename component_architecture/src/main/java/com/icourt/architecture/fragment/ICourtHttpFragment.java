package com.icourt.architecture.fragment;

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
public class ICourtHttpFragment extends RxFragment implements IContextCallQueue {
    final CallQueuePresenter callQueuePresenter = new CallQueuePresenter();


    @Override
    public <T> Call<T> callEnqueue(@NonNull Call<T> call, Callback<T> callback) {
        if (isDetached()) {
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
    public void onDetach() {
        cancelAllCall();
        super.onDetach();
    }

}
