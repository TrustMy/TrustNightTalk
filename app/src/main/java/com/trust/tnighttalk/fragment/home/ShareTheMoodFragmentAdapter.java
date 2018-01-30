package com.trust.tnighttalk.fragment.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.trust.tnighttalk.R;
import com.trust.tnighttalk.base.BaseRecyclerViewHeadFootAdapter;

/**
 * Created by Trust on 2018/1/30.
 */

public class ShareTheMoodFragmentAdapter extends BaseRecyclerViewHeadFootAdapter{
    public ShareTheMoodFragmentAdapter(Context mContext, boolean addHeadView, boolean addFootView) {
        super(mContext, addHeadView, addFootView);
    }

    @Override
    protected Intermediate initItemView(ViewGroup parent, int viewType) {
        View view  = mLayoutInflater.inflate(R.layout.item_share_the_mood,parent,false);
        ShareTheMoodFragmentViewHolder shareTheMoodFragmentViewHolder = new ShareTheMoodFragmentViewHolder(view);
        return shareTheMoodFragmentViewHolder;
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

    class ShareTheMoodFragmentViewHolder extends Intermediate{

        public ShareTheMoodFragmentViewHolder(View itemView) {
            super(itemView);
        }
    }
}
