package com.trust.tnighttalk.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.trust.tnighttalk.R;
import com.trust.tnighttalk.base.BaseActivtiy;
import com.trust.tnighttalk.base.BaseFragment;
import com.trust.tnighttalk.fragment.ExploreFragment;
import com.trust.tnighttalk.fragment.HomeFragment;
import com.trust.tnighttalk.fragment.MainFragment;
import com.trust.tnighttalk.fragment.OtherFragment;
import com.trust.tnighttalk.fragment.UserFragment;
import com.trust.tnighttalk.tool.TrustLogTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.trust.tnighttalk.tool.okhttp.TrustRequest.GET;

public class MainActivity extends BaseActivtiy {

    @BindView(R.id.main_fragmentlayout)
    FrameLayout mainFragmentlayout;
    @BindView(R.id.main_radiogroup)
    RadioGroup mainRadiogroup;
    @BindView(R.id.title)
    TextView title;

    private FragmentTransaction fragmentTransaction;
    private boolean isThis = true;//是否试当前activtiy
    private HomeFragment homeFragment;
    private ExploreFragment exploreFragment;
    private MainFragment mainFragment;
    private OtherFragment otherFragment;
    private UserFragment userFragment;
    private List<BaseFragment> ml = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        sendRequest("", null, 1, GET, null, false);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        homeFragment = new HomeFragment();
        exploreFragment = new ExploreFragment();
        mainFragment = new MainFragment();
        otherFragment = new OtherFragment();
        userFragment = new UserFragment();
        ml.add(homeFragment);
        ml.add(exploreFragment);
        ml.add(mainFragment);
        ml.add(otherFragment);
        ml.add(userFragment);
        setShowFragment(homeFragment);
        title.setText("Home");
        mainRadiogroup.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    protected void initDate(Bundle savedInstanceState) {

    }

    @Override
    protected void requestSuccessCallBack(int code, Object object) {
        TrustLogTool.d("object:" + object.toString());
    }

    @Override
    protected void requestErrorCallBack(int code, Object object) {
        TrustLogTool.d("object:" + object.toString());
    }


    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            BaseFragment baseFragment = null;
            String titleMsg = null;
            switch (i) {
                case R.id.radio_home:
                    baseFragment = homeFragment;
                    titleMsg = "Home";
                    break;
                case R.id.radio_explore:
                    baseFragment = exploreFragment;
                    titleMsg = "explore";
                    break;
                case R.id.radio_main:
                    baseFragment = mainFragment;
                    titleMsg = "main";
                    break;
                case R.id.radio_:
                    baseFragment = otherFragment;
                    titleMsg = "Other";
                    break;
                case R.id.radio_user:
                    baseFragment = userFragment;
                    titleMsg = "User";
                    break;
                default:
                    baseFragment = homeFragment;
                    titleMsg = "Home";
                    break;
            }
            title.setText(titleMsg);
            setShowFragment(baseFragment);
        }
    };

    private void setShowFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.main_fragmentlayout, fragment);
        } else {
            fragmentTransaction.show(fragment);
        }

        for (BaseFragment fragment1 : ml) {
            if (!fragment1.equals(fragment)) {
                if (fragment1.isAdded()) {
                    if (!fragment1.isHidden()) {
                        fragmentTransaction.hide(fragment1);
                    }
                }

            }
        }

        if (!this.isFinishing()) {
            if (isThis) {
                fragmentTransaction.commit();
            } else {
                fragmentTransaction.commitAllowingStateLoss();
            }
        }
    }


    @Override
    protected void onResume() {
        isThis = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isThis = false;
        super.onPause();
    }


}
