package com.icourt.api;

import android.text.TextUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Description
 * Company Beijing icourt
 * author  youxuan  E-mail:xuanyouwu@163.com
 * date createTime：2017/4/10
 * version 1.0.0
 */
public class RequestUtils {

    /**
     * 构建文本请求体
     *
     * @param text
     * @return
     */
    public static RequestBody createTextBody(String text) {
        return RequestBody
                .create(MediaType.parse("text/plain"),
                        TextUtils.isEmpty(text) ? "" : text);
    }

    /**
     * 构建图片请求体
     *
     * @param file
     * @return
     */
    public static RequestBody createImgBody(File file) {
        if (file != null && file.exists()) {
            return RequestBody.create(MediaType.parse("image/*"), file);
        }
        return null;
    }

    /**
     * 构建媒体流请求体
     *
     * @param file
     * @return
     */
    public static RequestBody createStreamBody(File file) {
        if (file != null && file.exists()) {
            return RequestBody.create(MediaType.parse("application/octet-stream"), file);
        }
        return null;
    }

    /**
     * 构建媒体流请求体
     *
     * @param bytes
     * @return
     */
    public static RequestBody createStreamBody(byte[] bytes) {
        return RequestBody.create(MediaType.parse("application/octet-stream"), bytes);
    }

    /**
     * 构建媒体流请求key
     *
     * @param file
     * @return
     */
    public static String createStreamKey(File file) {
        if (file != null && file.exists()) {
            return "file\"; filename=\"" + file.getName();
        }
        return "file\"; filename=\"";
    }

    /**
     * 构建媒体流请求key
     *
     * @param fileName
     * @return
     */
    public static String createStreamKey(String fileName) {
        return "file\"; filename=\"" + fileName + "\"";
    }

    /**
     * 构建表单请求体
     *
     * @param file
     * @return
     */
    public static RequestBody createFormBody(File file) {
        if (file != null && file.exists()) {
            return RequestBody.create(MediaType.parse("multipart/form-data"), file);
        }
        return null;
    }

    /**
     * 构建json请求体
     *
     * @param json
     * @return
     */
    public static RequestBody createJsonBody(String json) {
        return RequestBody
                .create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        TextUtils.isEmpty(json) ? "" : json);
    }

    /**
     * 执行call请求
     *
     * @param call
     * @param callback
     * @param <T>
     * @return
     */
    public static <T> Call<T> callEnqueue(Call<T> call, Callback<T> callback) {
        if (call != null && !call.isExecuted()) {
            call.enqueue(callback);
        }
        return call;
    }

    /**
     * 执行call请求
     *
     * @param call
     * @param <T>
     * @return
     */
    public static <T> Call<T> cancelCall(Call<T> call) {
        if (call != null) {
            try {
                call.cancel();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return call;
    }
}
