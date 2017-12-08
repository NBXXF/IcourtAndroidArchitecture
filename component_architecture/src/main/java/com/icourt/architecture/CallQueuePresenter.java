package com.icourt.architecture;

import android.support.annotation.NonNull;

import com.icourt.api.RequestUtils;

import java.lang.ref.WeakReference;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.2.2
 * @Description
 * @Company Beijing icourt
 * @date createTime：2017/12/3
 */
public class CallQueuePresenter implements IContextCallQueue {
    Queue<WeakReference<Call>> contextCallQueue = new ConcurrentLinkedQueue<>();

    @Override
    public <T> Call<T> callEnqueue(@NonNull Call<T> call, Callback<T> callback) {
        if (call != null) {
            contextCallQueue.offer(new WeakReference<Call>(call));
            return RequestUtils.callEnqueue(call, callback);
        }
        return call;
    }

    @Override
    public void cancelAllCall() {
        while (contextCallQueue.peek() != null) {
            WeakReference<Call> poll = contextCallQueue.poll();
            if (poll != null) {
                RequestUtils.cancelCall(poll.get());
            }
        }
    }

    @Override
    public <T> void cancelCall(@NonNull Call<T> call) {
        for (WeakReference<Call> poll : contextCallQueue) {
            if (poll != null && call == poll.get()) {
                contextCallQueue.remove(poll);
                break;
            }
        }
        RequestUtils.cancelCall(call);
    }
}
