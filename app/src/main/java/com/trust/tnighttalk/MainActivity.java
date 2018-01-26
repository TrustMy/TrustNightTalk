package com.trust.tnighttalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trust.calculatelayout.TrustUITool;
import com.trust.tnighttalk.activity.SpalshActivity;
import com.trust.tnighttalk.base.BaseActivtiy;
import com.trust.tnighttalk.tool.TrustLogTool;

import static com.trust.tnighttalk.tool.okhttp.TrustRequest.GET;

public class MainActivity extends BaseActivtiy {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        sendRequest("",null,1,GET,null,false);
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
