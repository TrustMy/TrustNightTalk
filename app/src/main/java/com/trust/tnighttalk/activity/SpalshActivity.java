package com.trust.tnighttalk.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trust.tnighttalk.R;
import com.trust.tnighttalk.base.BaseActivtiy;
import com.trust.tnighttalk.tool.TrustLogTool;

import static com.trust.tnighttalk.tool.okhttp.TrustRequest.GET;

public class SpalshActivity extends BaseActivtiy {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_spalsh;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initDate(Bundle savedInstanceState) {
    }

    @Override
    protected void requestSuccessCallBack(int code, Object object) {
        TrustLogTool.d("object:"+object.toString());
    }

    @Override
    protected void requestErrorCallBack(int code, Object object) {
        TrustLogTool.d("object:"+object.toString());
    }
}
