package com.trust.tnighttalk.base;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.trust.tnighttalk.tool.TrustLogTool;

import java.util.List;

/**
 * Created by Trust on 2017/8/3.
 */

public abstract class BaseRecyclerViewHeadFootAdapter<T> extends RecyclerView.Adapter <BaseRecyclerViewHeadFootAdapter.ViewHolder>{
    protected List<T> ml;//数据源
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    private boolean  addHeadView= false,addFootView = false;
    private TextView textView;
    public void setMl(List<T> ml) {
        TrustLogTool.d("ml.size():"+ml.size());
        this.ml = ml;
        changeData();
    }


    private final int HEAD = 0,FOOT = 1,OTHER = -1;
    private int headViewCount = 1;
    private int footViewCount = 1;


    public LinearLayoutManager getLinearLayoutManager(int type){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(type);
        return linearLayoutManager;
    }

    public GridLayoutManager getGridLayoutManager(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
        return gridLayoutManager;
    }

    /**
     * 刷新数据源的时候
     */
    protected void changeData(){};

    public List<T> getMl() {
        return ml;
    }

    public BaseRecyclerViewHeadFootAdapter(Context mContext, boolean addHeadView, boolean addFootView) {
        this.mContext = mContext;
        this.addFootView =addFootView;
        this.addHeadView = addHeadView;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BaseRecyclerViewHeadFootAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TrustLogTool.d("onCreateViewHolder");
        switch (viewType) {
            case HEAD:
                final HeadViewHolder headViewHolder = initItemHeadView(parent,viewType);
                if (headViewHolder != null) {
                    addHeadView = true;
                    headViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mItemOnClickListener != null) {
                                mItemOnClickListener.itemHeadOnClickListener();
                            }
                        }
                    });
                }
                return headViewHolder;
            case FOOT:
                final FoortViewHolder foortViewHolder = initItemFootView(parent,viewType);
                if (foortViewHolder != null) {
                    textView = foortViewHolder.footView;
                    addFootView = true;
                    foortViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mItemOnClickListener != null) {
                                mItemOnClickListener.itemFootOnClickListener();
                            }
                            if (foorViewRefreshListener != null) {
                                foorViewRefreshListener.footRefreshCallBack();
                            }
                        }
                    });
                }
                return foortViewHolder;
            default:
                final Intermediate viewHolder = initItemView(parent,viewType);
                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = viewHolder.getAdapterPosition();
                        ItemOnClick(pos);
                        if (mItemOnClickListener != null) {
                            mItemOnClickListener.itemOnClickListener(view,pos,ml.get(pos));
                        }

                    }
                });
                return viewHolder;
        }
    }



    protected abstract Intermediate initItemView(ViewGroup parent, int viewType);
    protected abstract HeadViewHolder initItemHeadView(ViewGroup parent, int viewType);
    protected abstract FoortViewHolder initItemFootView(ViewGroup parent, int viewType);


    private boolean isHead(int position){
        return headViewCount!=0&&position<headViewCount;
    }

    public boolean isFootView(int position){
        if (addHeadView) {
            return footViewCount!=0&&position>=((ml!=null?ml.size():0)+headViewCount);

        }else{
            return footViewCount!=0&&position>=(ml!=null?ml.size():0);
        }

    }

    @Override
    public int getItemViewType(int position) {
        TrustLogTool.d("getItemViewType");
        if(addHeadView &&isHead(position)){
            return HEAD;
        }

        if(addFootView&&isFootView(position)){
            return FOOT;
        }
        return OTHER;
    }

    @Override
    public int getItemCount() {
        if(addHeadView &&  addFootView){
            return ml!=null?(ml.size()+headViewCount+footViewCount):(headViewCount+footViewCount);
        }else if(!addHeadView && addFootView){
            return ml!=null?(ml.size()+footViewCount):(footViewCount);
        }else if(addHeadView && !addFootView){
            return  ml!=null?(ml.size()+headViewCount):(headViewCount);
        }else{
            return ml!=null?ml.size():0;
        }
    }


    protected class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }

    protected class Intermediate extends ViewHolder{
        public Intermediate(View itemView) {
            super(itemView);
        }
    }


    protected class HeadViewHolder extends ViewHolder{

        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    protected  class FoortViewHolder extends ViewHolder{
        TextView footView;
        public FoortViewHolder(View itemView) {
            super(itemView);
//            footView = (TextView) itemView.findViewById(R.id.item_recycler_foot_tv);
        }
    }

    //点击item
    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener){
        mItemOnClickListener = itemOnClickListener;
    }

    public interface ItemOnClickListener{
        void itemOnClickListener(View v, int pos, Object msg);
        void itemHeadOnClickListener();
        void itemFootOnClickListener();
    }

    public ItemOnClickListener mItemOnClickListener;


    protected void ItemOnClick(int pos){}

    @Override
    public void onBindViewHolder(BaseRecyclerViewHeadFootAdapter.ViewHolder holder, int position) {
        if (holder instanceof BaseRecyclerViewHeadFootAdapter.Intermediate) {
            showItem(holder,position);
        }
    }

    protected abstract void showItem(RecyclerView.ViewHolder holder, int position);


    public interface onFoorViewRefreshListener{ void footRefreshCallBack();}
    onFoorViewRefreshListener foorViewRefreshListener;

    public void setOnFoorViewRefreshListener(onFoorViewRefreshListener foorViewRefreshListener){
        this.foorViewRefreshListener = foorViewRefreshListener;
    }

    public void setFootViewText(String msg){
        if (textView != null) {
            textView.setText(msg);
        }
    }
}
