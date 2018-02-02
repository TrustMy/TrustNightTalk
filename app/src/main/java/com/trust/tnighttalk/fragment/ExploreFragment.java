package com.trust.tnighttalk.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trust.tnighttalk.R;
import com.trust.tnighttalk.base.BaseFragment;
import com.trust.tnighttalk.fragment.explore.ExploreFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Trust on 2018/1/29.
 */

public class ExploreFragment extends BaseFragment {
    @BindView(R.id.fragment_explore_recyclerview)
    RecyclerView fragmentExploreRecyclerview;

    private ExploreFragmentAdapter exploreFragmentAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_explore;
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        exploreFragmentAdapter = new ExploreFragmentAdapter(context,true,false);
        fragmentExploreRecyclerview.setLayoutManager(exploreFragmentAdapter.getLinearLayoutManager(LinearLayoutManager
        .VERTICAL));
        fragmentExploreRecyclerview.setAdapter(exploreFragmentAdapter);
    }

    @Override
    protected void requestSuccessCallBack(int code, Object object) {

    }

    @Override
    protected void requestErrorCallBack(int code, Object object) {

    }

}
