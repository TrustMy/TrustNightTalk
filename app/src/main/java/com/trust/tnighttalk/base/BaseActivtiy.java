package com.trust.tnighttalk.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.trust.tnighttalk.application.TrustApplication;
import com.trust.tnighttalk.tool.bean.config.ConfigResultBean;

import java.util.Map;

import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.trust.tnighttalk.tool.okhttp.TrustRequest.INTENT_SUCCESS;

/**
 * Created by Trust on 2018/1/26.
 */

public abstract class BaseActivtiy  extends AppCompatActivity {
    protected static TrustApplication trustApplication;
    protected Context mContext;
    protected abstract int getLayoutId();
    protected abstract void init(Bundle savedInstanceState);
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract void initDate(Bundle savedInstanceState);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        ButterKnife.bind(this);
        if (trustApplication == null) {
            trustApplication = (TrustApplication) getApplication();

        }
        mContext =this;
        TrustApplication.objectObserver = o;
        init(savedInstanceState);
        initView(savedInstanceState);
        initDate(savedInstanceState);

    }


    protected void sendRequest(final String url, final Map<String,Object> map, final int requestCode , final int requestType, final String token , final boolean isShowDialog){
        trustApplication.sendRequest(url,map,requestCode,requestType,100,token);
    }

    protected abstract void requestSuccessCallBack(int code, Object object);
    protected abstract void requestErrorCallBack(int code, Object object);

    protected Observer<Object> o = new Observer<Object>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Object o) {
            ConfigResultBean configResultBean = (ConfigResultBean) o;
            if (configResultBean.getStatus() == INTENT_SUCCESS) {
                requestSuccessCallBack(configResultBean.getCode(),configResultBean.getObject());
            }else{
                requestErrorCallBack(configResultBean.getCode(),configResultBean.getObject());
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };
}
