package com.icourt.api;

/**
 * @author xuanyouwu
 * @email xuanyouwu@163.com
 * @time 2016-06-02 14:26
 */
public interface apiConfig {
    /**
     * 接口请求超时时间
     */
    int SOCKET_TIME_OUT = 5 * 60_000;

    /**
     * 写入时间
     */
    int SOCKET_WRITE_TIME_OUT = 5 * 60_000;

    /**
     * 接口响应超时时间
     */
    int SOCKET_RESPONSE_TIME_OUT = 5 * 60_000;
}
