package com.icourt.api.impl;


import okhttp3.OkHttpClient;

/**
 * @author xuanyouwu
 * @email xuanyouwu@163.com
 * @time 2016-06-02 14:26
 */
public interface IRetrofit {

    void attachBaseUrl(OkHttpClient client, String baseUrl);

    <T> T createService(Class<T> clz);

    void destory();

}
