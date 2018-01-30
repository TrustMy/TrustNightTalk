package com.trust.tnighttalk.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trust.tnighttalk.R;
import com.trust.tnighttalk.base.BaseFragment;
import com.trust.tnighttalk.fragment.home.ShareTheMoodFragment;
import com.trust.tnighttalk.tool.TrustLogTool;
import com.trust.tnighttalk.tool.view.TrustTabBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Trust on 2018/1/29.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.fragment_home_navigation_bar)
    LinearLayout fragmentHomeNavigationBar;
    @BindView(R.id.cursor_btn)
    ImageView cursorBtn;

    private FragmentTransaction fragmentTransaction;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private ShareTheMoodFragment mHotFragment,mAttentionFragemnt,mAroundFragment;
    private boolean isThis = true;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        TrustTabBar trustTabBar = new TrustTabBar(context, fragmentHomeNavigationBar,
                trustTabOnClickBarListener,cursorBtn);
        trustTabBar.addNavigationBarText("Hot");
        trustTabBar.addNavigationBarText("Attention");
        trustTabBar.addNavigationBarText("Around");
        trustTabBar.setIndicatorX();

        mHotFragment = new ShareTheMoodFragment(0);
        mAttentionFragemnt = new ShareTheMoodFragment(1);
        mAroundFragment = new ShareTheMoodFragment(2);
        mFragments.add(mHotFragment);
        mFragments.add(mAttentionFragemnt);
        mFragments.add(mAroundFragment);
        setShowFragment(mHotFragment);
    }

    @Override
    protected void requestSuccessCallBack(int code, Object object) {

    }

    @Override
    protected void requestErrorCallBack(int code, Object object) {

    }

    private void setShowFragment(BaseFragment fragment){
        fragmentTransaction = getChildFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.home_framLayout,fragment);
        }else{
            fragmentTransaction.show(fragment);
        }

        for (BaseFragment fragment1 : mFragments) {
            if (!fragment1 .equals(fragment) ) {
                if (fragment1.isAdded()) {
                    if (!fragment1.isHidden()) {
                        fragmentTransaction.hide(fragment1);
                    }
                }

            }
        }

        if (!activity.isFinishing()) {
            if (isThis) {
                fragmentTransaction.commit();
            } else {
                fragmentTransaction.commitAllowingStateLoss();
            }
        }
    }



    public TrustTabBar.TrustTabOnClickBarListener trustTabOnClickBarListener =  new TrustTabBar.TrustTabOnClickBarListener() {
        @Override
        public void onClickFirst() {
            setShowFragment(mHotFragment);
        }

        @Override
        public void onClickSecond() {
            setShowFragment(mAttentionFragemnt);
        }

        @Override
        public void onClickThird() {
            setShowFragment(mAroundFragment);
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        isThis = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isThis = false;
    }
}
