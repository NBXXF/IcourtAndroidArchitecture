package com.icourt.api;

import android.annotation.SuppressLint;
import android.content.Context;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author xuanyouwu
 * @email xuanyouwu@163.com
 * @time 2016-06-02 14:26
 */
public abstract class SimpleClient extends BaseClient implements Interceptor {

    protected void attachBaseUrl(Context context, String baseUrl, HttpLoggingInterceptor httpLoggingInterceptor) {
        //okhttp3 cookie 持久化
        // ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //builder.retryOnConnectionFailure(true);
        //builder.cookieJar(cookieJar);
        builder.addInterceptor(this);
        builder.addInterceptor(httpLoggingInterceptor);
        builder.writeTimeout(apiConfig.SOCKET_WRITE_TIME_OUT, TimeUnit.MILLISECONDS);
        builder.connectTimeout(apiConfig.SOCKET_TIME_OUT, TimeUnit.MILLISECONDS);
        builder.readTimeout(apiConfig.SOCKET_RESPONSE_TIME_OUT, TimeUnit.MILLISECONDS);
        builder.sslSocketFactory(createSSLSocketFactory(), new TrustAllManager());
        builder.hostnameVerifier(new TrustAllHostnameVerifier());

        //正式证书
        /*int[] certificates = {R.raw.myssl};//cer文件
        String hosts[] = {HConst.BASE_DEBUG_URL, HConst.BASE_PREVIEW_URL, HConst.BASE_RELEASE_URL, HConst.BASE_RELEASE_SHARE_URL};
        builder.socketFactory(HttpsFactroy.getSSLSocketFactory(context, certificates));
        builder.hostnameVerifier(HttpsFactroy.getHostnameVerifier(hosts));*/

        super.attachBaseUrl(builder.build(), baseUrl);
    }


    /**
     * 默认信任所有的证书
     * 最好加上证书认证，主流App都有自己的证书
     *
     * @return
     */
    @SuppressLint("TrulyRandom")
    private static SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return sSLSocketFactory;
    }


    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


    @Override
    public final void attachBaseUrl(OkHttpClient client, String baseUrl) {
        super.attachBaseUrl(client, baseUrl);
    }
}
