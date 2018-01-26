package com.trust.tnighttalk.tool.okhttp;




import com.trust.tnighttalk.tool.TrustLogTool;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Trust on 2017/10/26.
 */

public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        TrustLogTool.d(String.format("发送请求: [%s] %s%n%s",
                request.url(), chain.connection(), request.headers()));


        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        TrustLogTool.d(String.format("接收响应: [%s] %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
