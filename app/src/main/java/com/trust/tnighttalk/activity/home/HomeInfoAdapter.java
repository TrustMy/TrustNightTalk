package com.trust.tnighttalk.activity.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.trust.tnighttalk.R;
import com.trust.tnighttalk.base.BaseRecyclerViewHeadFootAdapter;

/**
 * Created by Trust on 2018/1/31.
 */

public class HomeInfoAdapter extends BaseRecyclerViewHeadFootAdapter {

    public HomeInfoAdapter(Context mContext, boolean addHeadView, boolean addFootView) {
        super(mContext, addHeadView, addFootView);
    }

    @Override
    protected Intermediate initItemView(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_home_info,parent,false);
        HomeInfoViewHodler homeInfoViewHodler = new HomeInfoViewHodler(view);
        return homeInfoViewHodler;
    }

    @Override
    protected HeadViewHolder initItemHeadView(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected FoortViewHolder initItemFootView(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void showItem(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    class HomeInfoViewHodler extends Intermediate{

        public HomeInfoViewHodler(View itemView) {
            super(itemView);
        }
    }
}
