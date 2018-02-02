package com.trust.tnighttalk.fragment.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.trust.calculatelayout.TrustTelescopicLinerLayout;
import com.trust.calculatelayout.TrustTelescopicRelativeLayout;
import com.trust.tnighttalk.R;
import com.trust.tnighttalk.base.BaseRecyclerViewHeadFootAdapter;
import com.trust.tnighttalk.tool.TrustLogTool;

/**
 * Created by Trust on 2018/1/30.
 */

public class ShareTheMoodFragmentAdapter extends BaseRecyclerViewHeadFootAdapter{
    private ShareMoodAdapterOnClickListener shareMoodAdapterOnClickListener;
    public ShareTheMoodFragmentAdapter(Context mContext, boolean addHeadView, boolean addFootView) {
        super(mContext, addHeadView, addFootView);
    }

    @Override
    protected Intermediate initItemView(ViewGroup parent, int viewType) {
        View view  = mLayoutInflater.inflate(R.layout.item_share_the_mood,parent,false);
        final ShareTheMoodFragmentViewHolder shareTheMoodFragmentViewHolder = new ShareTheMoodFragmentViewHolder(view);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = shareTheMoodFragmentViewHolder.getAdapterPosition();
                switch (view.getId()) {
                    case R.id.share_mood_video_img:
                        shareMoodAdapterOnClickListener.onClickVideo(ml.get(pos));
                        break;
                }
            }
        };

        shareTheMoodFragmentViewHolder.videoImg.setOnClickListener(onClickListener);
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

    public void setOnClickListener(ShareMoodAdapterOnClickListener shareMoodAdapterOnClickListener){
        this.shareMoodAdapterOnClickListener =shareMoodAdapterOnClickListener;
    }

    class ShareTheMoodFragmentViewHolder extends Intermediate{
        TrustTelescopicRelativeLayout videoLayout;
        TrustTelescopicLinerLayout photoLayout;
        ImageView userLogoImg,attentionStatusImg,videoImg;
        TextView userNameTv ,userMsgTimeTv;
        public ShareTheMoodFragmentViewHolder(View itemView) {
            super(itemView);
            videoLayout = itemView.findViewById(R.id.share_mood_video_layout);
            photoLayout = itemView.findViewById(R.id.share_mood_photo_layout);
            userLogoImg = itemView.findViewById(R.id.item_share_mood_user_logo_img);
            userNameTv = itemView.findViewById(R.id.item_share_mood_user_name_tv);
            userMsgTimeTv = itemView.findViewById(R.id.item_share_mood_msg_time_tv);
            attentionStatusImg = itemView.findViewById(R.id.item_share_mood_attention_status_img);
            videoImg = itemView.findViewById(R.id.share_mood_video_img);

        }
    }

    public interface ShareMoodAdapterOnClickListener{ void onClickVideo(Object bean);}



}
