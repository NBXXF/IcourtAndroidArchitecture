package com.icourt.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icourt.api.impl.IRetrofit;
import com.icourt.json.StringNullAdapter;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xuanyouwu
 * @email xuanyouwu@163.com
 * @time 2016-06-02 14:26
 */
public class BaseClient implements IRetrofit {

    private Retrofit mRetrofit;

    /**
     * 自定义gson
     *
     * @return
     */
    protected Gson createGson() {
        return new GsonBuilder()
                .setLenient()// json宽松
                .enableComplexMapKeySerialization()//支持Map的key为复杂对象的形式
                .setPrettyPrinting()// 调教格式
                .disableHtmlEscaping() //默认是GSON把HTML 转义的
                .registerTypeAdapter(String.class, new StringNullAdapter())//将空字符串转换成""
                .create();
    }

    @Override
    public void attachBaseUrl(OkHttpClient client, String baseUrl) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                //.addConverterFactory(ProtoConverterFactory.create())//适合数据同步
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .client(client)
                .build();
    }

    @Override
    public <T> T createService(Class<T> clz) {
        return mRetrofit.create(clz);
    }

    @Override
    public void destory() {
        mRetrofit = null;
    }
}
