package com.trust.tnighttalk.tool.retrofit;

import android.content.Context;

import com.trust.tnighttalk.BuildConfig;
import com.trust.tnighttalk.tool.TrustLogTool;
import com.trust.tnighttalk.tool.kttest.Test;
import com.trust.tnighttalk.tool.okhttp.LoggingInterceptor;
import com.trust.tnighttalk.tool.okhttp.ssl.TrustAllCerts;

import org.json.JSONObject;

import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Trust on 2018/2/5.
 */

public class RetrofitTest {
    Retrofit retrofit;

    public RetrofitTest(Context context) {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://58.246.120.86/tomcat/")
                .client(initOk(context.getApplicationContext()))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient initOk(Context context){
        OkHttpClient okHttpClient =null;
        try {
            okHttpClient = new OkHttpClient.Builder()
                    .sslSocketFactory(new TrustAllCerts().createSSLSocketFactory(),new TrustAllCerts())
                    .hostnameVerifier(new TrustAllCerts.TrustAllHostnameVerifier())
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .addNetworkInterceptor(new LoggingInterceptor())
                    .build();

        }catch (Exception e){
            e.printStackTrace();
        }

        return okHttpClient;
    }


    public void sendTest(){
        requestInterface requestInterface = retrofit.create(com.trust.tnighttalk.tool.retrofit.requestInterface.class);
        Map<String,Integer> map = new WeakHashMap<>();
        map.put("appType",1);
        Call<Test> test = requestInterface.Test(map);
        test.enqueue(new Callback<Test>() {
            @Override
            public void onResponse(Call<Test> call, Response<Test> response) {
                    TrustLogTool.d("onResponse:"+response.body().getInfo());

            }

            @Override
            public void onFailure(Call<Test> call, Throwable t) {
                TrustLogTool.d("onFailure call:"+call.toString());
                TrustLogTool.d("onFailure t:"+t.toString());
            }
        });


    }

    public void sendLogin(String url){
        requestInterface requestInterface = retrofit.create(com.trust.tnighttalk.tool.retrofit.requestInterface.class);
        Call<Test> login = requestInterface.login(url);

        login.enqueue(new Callback<Test>() {
            @Override
            public void onResponse(Call<Test> call, Response<Test> response) {
                    TrustLogTool.d("onResponse:"+response.toString());

            }

            @Override
            public void onFailure(Call<Test> call, Throwable t) {
                TrustLogTool.d("onFailure t:"+t.toString());
                t.printStackTrace();
            }
        });
    }


    public void sendRetrofit(){
        requestInterface requestInterface = retrofit.create(requestInterface.class);
        Map<String,Integer> map = new WeakHashMap<>();
        map.put("appType",1);
        requestInterface.retrofitTest(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Test>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Test test) {
                        TrustLogTool.d("test:"+test.getInfo());
                    }

                    @Override
                    public void onError(Throwable e) {
                        TrustLogTool.d("onError:"+e.toString());
                    }

                    @Override
                    public void onComplete() {
                        TrustLogTool.d("onComplete:");
                    }
                });
    }


    public void sendSenDiLogin(){
        requestInterface requestInterface = retrofit.create(requestInterface.class);
        Map<String,Object> map = new WeakHashMap<>();
        map.put("userName","13892929789");
        map.put("password","123456");
        map.put("userType",1);
        map.put("imsi","123123123");
        map.put("appVersion","1.0.0");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new JSONObject(map).toString());
        requestInterface.SendLogin(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Test>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<Test> test) {
                        TrustLogTool.d("test:"+test.headers().toString());
                        TrustLogTool.d("test:"+test.body().getInfo());
                    }

                    @Override
                    public void onError(Throwable e) {
                        TrustLogTool.d("onError:"+e.toString());
                    }

                    @Override
                    public void onComplete() {
                        TrustLogTool.d("onComplete:");
                    }
                });
    }





    /**
     * https  添加证书
     * @param context
     * @return
     */
    public  SSLSocketFactory getSSLSocketFactory(Context context)  {

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
