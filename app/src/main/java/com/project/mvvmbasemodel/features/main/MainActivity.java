package com.project.mvvmbasemodel.features.main;

import com.project.mvvmbasemodel.R;
import com.project.mvvmbasemodel.base.BaseActivity;
import com.project.mvvmbasemodel.base.BaseNavigator;
import com.project.mvvmbasemodel.databinding.ActivityMainBinding;
import com.project.mvvmbasemodel.view_model.MainVM;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainVM> implements MainNavigator {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onBinding() {
        mBinding.setViewModel(mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.welcomeToBaseMVVM();
    }

    @Override
    protected Class getViewModel() {
        return MainVM.class;
    }

    @Override
    protected BaseNavigator getNavigatorReference() {
        return this;
    }


    @Override
    public void popUpWelcomeMessage() {
        showMessageDialog("Welcome", "MVVVM-Base Model", 123);
    }
}
