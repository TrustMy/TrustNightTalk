package com.trust.tnighttalk.tool.okhttp;




import com.trust.tnighttalk.tool.TrustLogTool;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
        MediaType contentType = null;
        String bodyString = null;
        if (response.body() != null) {
            contentType = response.body().contentType();
            bodyString = response.body().string();
        }
        // 请求响应时间
        double time = (t2 - t1) / 1e6d;
        TrustLogTool.d(String.format("接收返回: [%s] %s%n%s%s",response.request(), time, response.headers(),
                bodyString));
        if (response.body() != null) {
            // 深坑！
            // 打印body后原ResponseBody会被清空，需要重新设置body
            ResponseBody body = ResponseBody.create(contentType, bodyString);
            return response.newBuilder().body(body).build();
        } else {
            return response;
        }
    }
}
