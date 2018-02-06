package com.trust.tnighttalk.application;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.trust.calculatelayout.TrustUITool;
import com.trust.tnighttalk.base.BaseActivtiy;
import com.trust.tnighttalk.server.TrustServer;
import com.trust.tnighttalk.tool.TrustLogTool;
import com.trust.tnighttalk.tool.bean.config.ConfigResultBean;
//import com.trust.tnighttalk.tool.okhttp.TrustRequest;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//import static com.trust.tnighttalk.tool.okhttp.TrustRequest.INTENT_SUCCESS;

/**
 * Created by Trust on 2018/1/26.
 */

public class TrustApplication extends Application {
    private static TrustApplication instance;
    private Context context;
    public static TrustApplication getInstance() {
        return instance;
    }

    private TrustServer trustServer;

//    private TrustRequest trustRequest;

    private String serverUrl = "https://www.jianshu.com/p/8818b98c44e2";

    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            trustServer = ((TrustServer.TrustBinder)iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            trustServer = null;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = this;
        init();
    }

    private void init() {
        //初始化 屏幕适配
        TrustUITool.initTrustUITool(context.getApplicationContext());
        TrustUITool.setBenchmark(303,537);

        //初始化网络接口
//        trustRequest = new TrustRequest(resultCallBack,serverUrl,this);

    }



    /**
     * 初始化service
     */
    public void initService(){
        bindService(new Intent(this, TrustApplication.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 解绑service
     */
    private void closeService(){
        unbindService(serviceConnection);
    }

    /**
     * 发送网络请求
     * @param url
     * @param map
     * @param requestType
     * @param requestHeadType
     * @param token
     */
    public void sendRequest(final String url, final Map<String,Object> map , final int requestCode , final int requestType, final int requestHeadType, final String token){
//        Observable.create(new ObservableOnSubscribe<Object>() {
//            @Override
//            public void subscribe(ObservableEmitter<Object> e) throws Exception {
//                trustRequest.request(url,map,requestCode,requestType,requestHeadType,token);
//            }
//        }).subscribeOn(Schedulers.newThread()).subscribe();
    }

    /**
     * 网络请求回掉
     */
    public static  Observer<Object> objectObserver;
//    private TrustRequest.onResultCallBack resultCallBack  = new TrustRequest.onResultCallBack() {
//        @Override
//        public void CallBack(final int code, final int status, final Object msg) {
//            TrustLogTool.d("msg:"+msg.toString());
//
//            Observable.create(new ObservableOnSubscribe<Object>() {
//                @Override
//                public void subscribe(ObservableEmitter<Object> e) throws Exception {
//                    e.onNext(new ConfigResultBean(status,code,msg));
//                }
//            }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(objectObserver);
//
//
//
//        }
//    };

}
