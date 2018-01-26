package com.trust.tnighttalk.tool.okhttp;


import android.content.Context;


import com.trust.tnighttalk.tool.TrustLogTool;
import com.trust.tnighttalk.tool.TrustTool;
import com.trust.tnighttalk.tool.okhttp.ssl.TrustAllCerts;

import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;




/**
 * Created by Trust on 2017/8/7.
 */

public class TrustRequest implements Serializable {
    private final String ERROR_NO_JSON = "服务器返回得不是json！";
    public  static final int INTENT_ERROR = 0,INTENT_SUCCESS = 1;
    public  static final int GET = 0x0001,POST = 0x0002,PUT = 0x0003,
            GET_NO_PARAMETER_NNAME = 0x0004, DELETE = 0x0005;//GET_NO_PARAMETER_Name  GET请求 参数加名称直接 放参数
    public  final int HeaderNull = 0;
    public  final String TokenNull = null;
    public  final boolean addHeader = true,noAdd = false,addToken = true;
    public  final int HeaderJson = 0x00001,HeaderUrlencoded = 0x00002;
    private OkHttpClient okHttpClient ;
    private Request.Builder builder;
    private MediaType mediaType;
    private String serverUrl;
    private TrustExceptionTool trustExceptionTool;
    public interface onResultCallBack <T>{
        void CallBack(int code, int status, T msg);
    }

    public onResultCallBack resultCallBack;

    public TrustRequest(onResultCallBack resultCallBack , String serverUrl , Context context) {

//            this.okHttpClient = new OkHttpClient.Builder()
//                    .sslSocketFactory(getSSLSocketFactory(context.getApplicationContext(),"sc.crt"),new TrustAllCerts())
//                    .hostnameVerifier(new TrustAllCerts.TrustAllHostnameVerifier())
//                    .connectTimeout(15, TimeUnit.SECONDS)
//                    .readTimeout(15, TimeUnit.SECONDS)
//                    .addNetworkInterceptor(new LoggingInterceptor())
//                    .build();
        this.okHttpClient = new OkHttpClient.Builder()
                .hostnameVerifier(new TrustAllCerts.TrustAllHostnameVerifier())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(new LoggingInterceptor())
                .build();

        this.resultCallBack = resultCallBack;
        this.serverUrl = serverUrl;
        trustExceptionTool = new TrustExceptionTool();
    }

    /**
     * 网络请求  并拆分
     * @param url
     * @param map  数据
     * @param requestCode  请求tag
     * @param requestType  请求类型
     * @param requestHeader 如果添加请求头 设置请求头种类 如果没有HeaderNull
     * @param token 如果添加token 设置token 如何没有TokenNull
     */
    public void request(String url , Map<String , Object> map , int requestCode, int requestType, int requestHeader , String token){
        builder = new Request.Builder();
        String msg = null;
        String urls = serverUrl+url;
        Request request = null;
        switch (requestHeader){
            case HeaderJson:
                if (map != null) {
                    msg = new JSONObject(map).toString();
                }
                break;
            case HeaderUrlencoded:
                StringBuffer sb = null;
                for (Map.Entry<String, Object> entry : map.entrySet()){
                    if(sb == null){
                        sb = new StringBuffer();
                        sb.append(entry.getKey()+"="+entry.getValue());
                    }else{
                        sb.append("&"+entry.getKey()+"="+entry.getValue());
                    }
                }
                msg = sb.toString();
                break;
        }

        if (msg != null) {
            TrustLogTool.d("Request 发送的json:"+msg);
        }
            if(requestType == GET || requestType == GET_NO_PARAMETER_NNAME){
                if (token != null) {
                    builder.addHeader("Token",token);

                }
                if(map != null){
                    StringBuffer sb = null;
                    for (Map.Entry<String, Object> entry : map.entrySet()){
                        if(sb == null){
                            sb = new StringBuffer();

                            if (requestType == GET_NO_PARAMETER_NNAME) {
                                if (entry.getValue() instanceof String){
                                    sb.append(entry.getValue());
                                }else{
                                    sb.append("?"+entry.getKey()+"="+entry.getValue());
                                }
                            }else{
                                if (entry.getValue() instanceof String){
                                    sb.append("?"+entry.getKey()+"="+entry.getValue()+"");
                                }else{
                                    sb.append("?"+entry.getKey()+"="+entry.getValue());
                                }
                            }
                        }else{
                            if (entry.getValue() instanceof String){
                                sb.append("&"+entry.getKey()+"="+entry.getValue()+"");
                            }else{
                                sb.append("&"+entry.getKey()+"="+entry.getValue());
                            }

                        }
                    }

                    TrustLogTool.d("get usrl :"+urls+ sb.toString());
                    request =  builder.get().url(urls+ sb.toString()).build();
                }else{
                    request =  builder.get().url(urls).build();
                }
            }else  if(requestType == PUT && map == null){
                mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "");
                builder.addHeader("token",token);
                request = builder.put(body).url(urls).build();
            }
            else if(requestType == DELETE){
                mediaType = MediaType.parse("application/json");
                builder.addHeader("token",token);
                StringBuffer sb = null;
                for (Map.Entry<String, Object> entry : map.entrySet()){
                    if (sb == null) {
                        sb = new StringBuffer();
                    }

                    if (entry.getValue() instanceof String){
                        sb.append(entry.getValue());
                    }else{
                        sb.append("?"+entry.getKey()+"="+entry.getValue());
                    }

                }
                request = builder.url(urls+sb).delete().build();
            }
            else {
                if (msg != null) {
                    if (requestHeader != HeaderNull){
                        request =  returnRequest(urls,requestType, requestHeader,msg,token);
                    }else{
                        FormBody.Builder builder = new FormBody.Builder();
                        for (Map.Entry<String, Object> entry : map.entrySet()){
                            builder.add(entry.getKey(),entry.getValue()+"");
                        }
                        FormBody body = builder.build();
                        request  = new Request.Builder().url(urls).post(body).build();
                    }
                }else{
                    mediaType = MediaType.parse("application/json");
                    RequestBody body = RequestBody.create(mediaType, "");
                    builder.addHeader("token",token);
                    request = builder.post(body).url(urls).build();
                }
            }
            if (request != null) {
                executeResponse(request,requestCode);
                TrustLogTool.d("url:"+request.url().toString());
            }

    }

    /**
     * 判断添加header 后判断是否添加token
     * @param url

     * @param requestHeader
     * @param msg
     * @return
     */

    private Request returnRequest(String url, int requestType, int requestHeader , String msg , String token) {
        Request request = null;
        RequestBody body = null;
        builder = new Request.Builder();
        body = returnBody(requestHeader,msg);

        if (body != null) {

            builder.url(url);
            if(token!= null){
               builder.addHeader("Token", token);
            }
            if (requestType == POST){
                builder.post(body);
            }else if (requestType == PUT){
                builder.put(body);
            }

            request = builder.build();
            TrustLogTool.d("request.headers().toString():"+request.headers().toString());
        }
        return request;
    }

    /**
     * 为body 添加header
     * @param requestHeader
     * @param requestMessage
     * @return
     */
    private RequestBody returnBody(int requestHeader, String requestMessage){
        RequestBody body = null;
        switch (requestHeader){
            case HeaderJson:
                mediaType = MediaType.parse("application/json");
                break;
            case HeaderUrlencoded:
                mediaType = MediaType.parse("application/x-www-form-urlencoded");
                break;
        }
        body = RequestBody.create(mediaType, requestMessage);
        return body;
    }


    public void setOnResultCallBack(onResultCallBack onResultCallBack){
        this.resultCallBack = onResultCallBack;
    }


    /**
     * 请求回调
     * @param request
     * @param type
     */
    public void executeResponse(final Request request , final int type) {
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                TrustLogTool.e("onFailure:"+e.toString());
                String s = trustExceptionTool.checkException(e.getClass().getSimpleName());

                resultCallBack.CallBack(type, INTENT_ERROR,s!=null?s:e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                TrustLogTool.d("onResponse:"+response.toString());
                String json = null;
                if(response.code() == 200){
                    json = response.body().string();
                    TrustLogTool.d("json:"+json);
                    if (TrustTool.checkIsJson(json)) {
                        if(json != null ){
                            resultCallBack.CallBack(type, INTENT_SUCCESS,json);
                        }else{
                            resultCallBack.CallBack(type, INTENT_ERROR,ERROR_NO_JSON);
                        }
                    }else{
                        resultCallBack.CallBack(type, INTENT_ERROR,ERROR_NO_JSON);
                    }
                }else{
                    resultCallBack.CallBack(type, INTENT_ERROR,response.code()+"");
                }
            }
        });
    }


    /**
     * https  添加证书
     * @param context
     * @param name
     * @return
     */
    public static SSLSocketFactory getSSLSocketFactory(Context context, String name)  {

        try
        {
            /*
            InputStream certificates = context.getAssets().open(name);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;

            for (InputStream certificate : certificates)
            {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias,
                        certificateFactory.generateCertificate(certificate));

                try
                {
                    if (certificate != null){
                        certificate.close();}
                } catch (IOException e)
                {
                }
            }

*/

            //初始化keystore
            KeyStore clientKeyStore = KeyStore.getInstance("BKS");
            clientKeyStore.load(context.getAssets().open("cacerts_sy.bks"), "changeit".toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory
                    .getDefaultAlgorithm());

            keyManagerFactory.init(clientKeyStore, "000000".toCharArray());

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(clientKeyStore);

//
//            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers()
//                    , new SecureRandom());


            sslContext.init
                    (null,trustManagerFactory.getTrustManagers(),new SecureRandom()
                            //第一个参数是验证服务器返回 第二个参数 是请求的时候带着 服务器的证书让服务器验证的
                    );

            return sslContext.getSocketFactory();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }


}
