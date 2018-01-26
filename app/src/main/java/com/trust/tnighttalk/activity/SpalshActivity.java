package com.trust.tnighttalk.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.trust.tnighttalk.MainActivity;
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
        trustApplication.initService();
        LinearLayout linearLayout = findViewById(R.id.spalsh_layout);
        Animation animation= AnimationUtils.loadAnimation(this, R.anim.alpha);
        linearLayout.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {}   //在动画开始时使用

            @Override
            public void onAnimationRepeat(Animation arg0) {}  //在动画重复时使用

            @Override
            public void onAnimationEnd(Animation arg0) {
                startActivity(new Intent(SpalshActivity.this, MainActivity.class));
                finish();
            }
        });
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
