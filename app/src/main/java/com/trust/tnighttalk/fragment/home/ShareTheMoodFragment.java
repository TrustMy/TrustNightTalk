package com.trust.tnighttalk.fragment.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trust.tnighttalk.R;
import com.trust.tnighttalk.activity.home.HomeInfoActivity;
import com.trust.tnighttalk.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Trust on 2018/1/30.
 */

@SuppressLint("ValidFragment")
public class ShareTheMoodFragment extends BaseFragment {
    @BindView(R.id.share_the_mood_recycler)
    RecyclerView shareTheMoodRecycler;
    private int mType;
    private  ShareTheMoodFragmentAdapter shareTheMoodFragmentAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_share_the_mood;
    }

    @Override
    protected void initDate() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        shareTheMoodFragmentAdapter.setMl(list);
        shareTheMoodFragmentAdapter.notifyDataSetChanged();
    }

    public ShareTheMoodFragment(int type) {
        mType = type;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        shareTheMoodFragmentAdapter = new ShareTheMoodFragmentAdapter(context,false,false);
        shareTheMoodRecycler.setLayoutManager(shareTheMoodFragmentAdapter.getLinearLayoutManager(LinearLayoutManager.VERTICAL));
        shareTheMoodRecycler.setAdapter(shareTheMoodFragmentAdapter);
        shareTheMoodFragmentAdapter.setOnClickListener(new ShareTheMoodFragmentAdapter.ShareMoodAdapterOnClickListener() {
            @Override
            public void onClickVideo(Object bean) {
                startActivity(new Intent(context,HomeInfoActivity.class));
            }
        });
    }

    @Override
    protected void requestSuccessCallBack(int code, Object object) {

    }

    @Override
    protected void requestErrorCallBack(int code, Object object) {

    }



}
