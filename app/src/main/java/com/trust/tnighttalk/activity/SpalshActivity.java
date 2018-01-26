package com.trust.tnighttalk.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.trust.tnighttalk.R;
import com.trust.tnighttalk.base.BaseActivtiy;
import com.trust.tnighttalk.tool.TrustLogTool;

public class SpalshActivity extends BaseActivtiy {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_spalsh;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        trustApplication.initService();

        LinearLayout linearLayout = findViewById(R.id.spalsh_layout);

        linearLayout.setSystemUiVisibility(View.INVISIBLE);

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
