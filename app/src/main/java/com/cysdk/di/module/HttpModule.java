package com.cysdk.di.module;


import com.cysdk.common.http.util.SSLSocketClient;
import com.cysdk.common.http.util.StringConverterFactory;
import com.cysdk.data.http.ApiService;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @description：
 * @author：bux on 2018/4/2 17:56
 * @email: 471025316@qq.com
 */

@Module
public class HttpModule {

    private Retrofit mRetrofit;

    public ApiService getHttp(){
        //log拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等

        OkHttpClient.Builder okClient = new OkHttpClient.Builder()
                //HeadInterceptor实现了Interceptor，用来往Request Header添加一些业务相关数据，如APP版本，token信息
                // .addInterceptor(new HeadInterceptor())
                //  .addInterceptor(commonParamsInterceptor)
                //.addInterceptor(new LoggingInterceptor())
                .addInterceptor(logging)
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier());

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://www.baidu.com")
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okClient.build())
                .build();

       return mRetrofit.create(ApiService.class);
    }
}
