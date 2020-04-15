package com.project.mvvmbasemodel.features.stepper;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.mvvmbasemodel.R;
import com.project.mvvmbasemodel.base.BaseActivity;
import com.project.mvvmbasemodel.base.BaseNavigator;
import com.project.mvvmbasemodel.base.GenericAdapter;
import com.project.mvvmbasemodel.databinding.ActivityMainBinding;
import com.project.mvvmbasemodel.databinding.ActivityStepperBinding;
import com.project.mvvmbasemodel.databinding.ListItem1Binding;
import com.project.mvvmbasemodel.databinding.ListItem2Binding;
import com.project.mvvmbasemodel.features.main.MainNavigator;
import com.project.mvvmbasemodel.model.ListItemModel;
import com.project.mvvmbasemodel.view_model.MainVM;
import com.project.mvvmbasemodel.view_model.StepperVM;

import java.util.ArrayList;

public class StepperActivity extends BaseActivity<ActivityStepperBinding, StepperVM> implements StepperNavigator {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_stepper;
    }

    @Override
    protected void onBinding() {
        mBinding.setViewModel(mViewModel);
    }


    @Override
    protected Class getViewModel() {
        return StepperVM.class;
    }

    @Override
    protected StepperNavigator getNavigatorReference() {
        return this;
    }


}
