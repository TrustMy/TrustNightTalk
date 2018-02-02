package com.trust.tnighttalk.activity.home;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import com.trust.tnighttalk.R;
import com.trust.tnighttalk.base.BaseActivtiy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeInfoActivity extends BaseActivtiy {


    @BindView(R.id.home_info_recyclerview)
    RecyclerView homeInfoRecyclerview;

    private HomeInfoAdapter homeInfoAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_info_coor;
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

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        homeInfoAdapter = new HomeInfoAdapter(mContext,false,false);
        homeInfoRecyclerview.setLayoutManager(homeInfoAdapter.getLinearLayoutManager(LinearLayoutManager.VERTICAL));
        homeInfoRecyclerview.setAdapter(homeInfoAdapter);
    }

    @Override
    protected void initDate(Bundle savedInstanceState) {
        List<Integer> ml  = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ml.add(i);
        }
        homeInfoAdapter.setMl(ml);
        homeInfoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void requestSuccessCallBack(int code, Object object) {

    }

    @Override
    protected void requestErrorCallBack(int code, Object object) {

    }


}
