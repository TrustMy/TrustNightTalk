package com.trust.tnighttalk.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trust.tnighttalk.application.TrustApplication;
import com.trust.tnighttalk.tool.bean.config.ConfigResultBean;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.trust.tnighttalk.tool.okhttp.TrustRequest.INTENT_SUCCESS;

/**
 * Created by Trust on 2018/1/29.
 */

public abstract class BaseFragment  extends Fragment{
    protected static TrustApplication trustApplication;
    protected Context context;
    protected Activity activity;
    private View fragmentView;
    Unbinder unbinder;
    protected abstract int getLayoutId();
    protected abstract void initDate();
    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        this.activity = activity;
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (trustApplication == null) {
            trustApplication = (TrustApplication) activity.getApplication();
        }

        TrustApplication.objectObserver = o;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = LayoutInflater.from(context).inflate(getLayoutId(),null,false);
        unbinder = ButterKnife.bind(this, fragmentView);
        initView(savedInstanceState);
        initDate();
        return fragmentView;
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




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
