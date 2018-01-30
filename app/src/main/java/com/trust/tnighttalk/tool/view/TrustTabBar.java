package com.trust.tnighttalk.tool.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.AppBarLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trust on 2018/1/30.
 */

public class TrustTabBar {
    //所有按钮的宽度
    private int [] mWidthArgs;
    //所有按钮计划
    private TextView[] mBtnArgs;
    //标志指示标签的横坐标
    float mCursorX = 0;
    //保存全部控件
    private List<View> mViewList = new ArrayList<>();
    //属性动画
    private ObjectAnimator anim;
    //代码设置 布局ID
    @android.support.annotation.IdRes
    final int TOO_FIRST = 0,TOO_SECOND = 1,TOO_THIRD = 2;

    private int[] ViewIds = new int[]{TOO_FIRST,TOO_SECOND,TOO_THIRD};

    private Context mContext;
    private LinearLayout mLinearLayout;
    private TrustTabOnClickBarListener mTrustTabOnClickBarListener;
    private View cursorBtn;

    public TrustTabBar(Context context,LinearLayout linearLayout ,TrustTabOnClickBarListener trustTabOnClickBarListener,
            View cursorBtn) {
        this.cursorBtn =cursorBtn;
        this.mContext = context;
        this.mLinearLayout = linearLayout;
        this.mTrustTabOnClickBarListener = trustTabOnClickBarListener;
    }

    //设置指示器显示位置
    public void setIndicatorX(){
        cursorBtn.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)cursorBtn.getLayoutParams();
                //减去边距*2，以对齐标题栏文字
//                lp.width = (mHome.getWidth()-mHome.getPaddingLeft()*2)/3;代码设置指示器宽
                cursorBtn.setLayoutParams(lp);
                cursorBtn.setX(cursorBtn.getWidth()*2);//控制初始化时指示器得位置
            }
        });
        ((TextView)(mViewList.get(0))).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        ((TextView)(mViewList.get(0))).setTextColor(Color.parseColor("#000000"));
        for (int i = 0; i < mViewList.size(); i++) {
            mViewList.get(i).setId(ViewIds[i]);
            mViewList.get(i).setOnClickListener(onClickListener);
        }
    }

    private void reTextColor(){
        if (mBtnArgs != null) {
            for (TextView mBtnArg : mBtnArgs) {
                mBtnArg.setTextColor(Color.parseColor("#c4c4c4"));
                mBtnArg.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//取消加粗
            }
        }

    }


    public TextView addNavigationBarText(String msg) {
        TextView titles = new TextView(mContext);
        titles.setText(msg);
//        titles.setId(id);
        titles.setGravity(Gravity.CENTER);
        titles.setTextSize(18);
        LinearLayout.LayoutParams layoutParams = new AppBarLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1.0f);
        titles.setLayoutParams(layoutParams);
        mLinearLayout.addView(titles);
        mViewList.add(titles);
        return titles;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mTrustTabOnClickBarListener != null) {
                reTextColor();
                switch (view.getId()) {
                    case TOO_FIRST:
                        cursorAnim(TOO_FIRST);
                        mTrustTabOnClickBarListener.onClickFirst();
                        break;
                    case TOO_SECOND:
                        cursorAnim(TOO_SECOND);
                        mTrustTabOnClickBarListener.onClickSecond();
                        break;
                    case TOO_THIRD:
                        cursorAnim(TOO_THIRD);
                        mTrustTabOnClickBarListener.onClickThird();
                        break;
                }
            }

        }
    };

    public interface TrustTabOnClickBarListener {void onClickFirst();
        void onClickSecond();
        void onClickThird();};



    //指示器的跳转，传入当前所处的页面的下标
    public void cursorAnim(int curItem){
        if (mWidthArgs == null) {
            mBtnArgs = new TextView[mViewList.size()];
            mWidthArgs = new int[mViewList.size()];
            for (int i = 0; i < mViewList.size(); i++) {
                mWidthArgs[i] = mViewList.get(i).getWidth();
                mBtnArgs[i] = (TextView) mViewList.get(i);
            }
        }
        mBtnArgs[curItem].setTextColor(Color.parseColor("#000000"));
        mBtnArgs[curItem].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//字条加粗

        //每次调用，就将指示器的横坐标设置为0，即开始的位置
        mCursorX = 0;
        //再根据当前的curItem来设置指示器的宽度
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)cursorBtn.getLayoutParams();
        //减去边距*2，以对齐标题栏文字
//        lp.width = (mWidthArgs[curItem]-mBtnArgs[0].getPaddingLeft()*2)/3;//宽度
        cursorBtn.setLayoutParams(lp);
        //循环获取当前页之前的所有页面的宽度
        for(int i=0; i<curItem; i++){
            mCursorX = mCursorX + mBtnArgs[i].getWidth();
        }
        //再加上当前页面的左边距，即为指示器当前应处的位置
//        cursor.setX(cursorX+btnArgs[curItem].getPaddingLeft());
        // X轴方向上的坐标
        float translationX = cursorBtn.getTranslationX();
        anim  = ObjectAnimator.ofFloat(cursorBtn,"translationX",translationX,mCursorX+mBtnArgs[curItem].getWidth()/2  - cursorBtn.getWidth()/2);
        anim.setDuration(500);
        anim.start();
    }

}
