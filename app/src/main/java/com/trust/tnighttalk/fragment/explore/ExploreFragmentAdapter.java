package com.trust.tnighttalk.fragment.explore;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.trust.tnighttalk.R;
import com.trust.tnighttalk.base.BaseRecyclerViewHeadFootAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trust on 2018/2/1.
 */

public class ExploreFragmentAdapter extends BaseRecyclerViewHeadFootAdapter {

    public ExploreFragmentAdapter(Context mContext, boolean addHeadView, boolean addFootView) {
        super(mContext, addHeadView, addFootView);
    }

    @Override
    protected Intermediate initItemView(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected HeadViewHolder initItemHeadView(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_explore_head,parent,false);
        HeadViewHodler headViewHodler = new HeadViewHodler(view);
        return headViewHodler;
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


    class HeadViewHodler extends HeadViewHolder{
        RecyclerView recyclerView;
        public HeadViewHodler(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.item_explore_head_recyclerview);
            HeadViewHodlerAdapter headViewHodlerAdapter = new HeadViewHodlerAdapter(mContext,false,false);
            recyclerView.setLayoutManager(headViewHodlerAdapter.getLinearLayoutManager(LinearLayoutManager.HORIZONTAL));
            recyclerView.setAdapter(headViewHodlerAdapter);
            List<Integer> ml = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                ml.add(i);
            }
            headViewHodlerAdapter.setMl(ml);
            headViewHodlerAdapter.notifyDataSetChanged();
        }
    }




    public class HeadViewHodlerAdapter extends BaseRecyclerViewHeadFootAdapter{

        public HeadViewHodlerAdapter(Context mContext, boolean addHeadView, boolean addFootView) {
            super(mContext, addHeadView, addFootView);
        }

        @Override
        protected Intermediate initItemView(ViewGroup parent, int viewType) {
            View v = mLayoutInflater.inflate(R.layout.item_explore_head_recycer,parent,false);
            HeadViewHodlerAdapterViewHodler adapterViewHodler = new HeadViewHodlerAdapterViewHodler(v);
            return adapterViewHodler;
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



        public class HeadViewHodlerAdapterViewHodler extends Intermediate{

            public HeadViewHodlerAdapterViewHodler(View itemView) {
                super(itemView);
            }
        }


    }
}
