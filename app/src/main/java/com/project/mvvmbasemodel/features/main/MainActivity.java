package com.project.mvvmbasemodel.features.main;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.mvvmbasemodel.R;
import com.project.mvvmbasemodel.base.BaseActivity;
import com.project.mvvmbasemodel.base.BaseNavigator;
import com.project.mvvmbasemodel.base.GenericAdapter;
import com.project.mvvmbasemodel.databinding.ActivityMainBinding;
import com.project.mvvmbasemodel.databinding.ListItem1Binding;
import com.project.mvvmbasemodel.databinding.ListItem2Binding;
import com.project.mvvmbasemodel.features.Utils;
import com.project.mvvmbasemodel.features.stepper.StepperActivity;
import com.project.mvvmbasemodel.model.ListItemModel;
import com.project.mvvmbasemodel.view_model.MainVM;

import java.util.ArrayList;

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

    @Override
    public void onSimpleItemList(ArrayList<String> arrayList) {
        bindSimpleListItem(arrayList);
    }

    @Override
    public void onCustomItemList(ArrayList<ListItemModel> arrayList) {
        bindCustomListItem(arrayList);
    }

    @Override
    public void navigateStepper() {
        Utils.switchToActivity(this, StepperActivity.class);
    }

    private void bindSimpleListItem(ArrayList<String> arrayList) {
        GenericAdapter<String, ListItem1Binding> adapter = new GenericAdapter<String, ListItem1Binding>(mContext) {
            @Override
            public int getLayoutId() {
                return R.layout.list_item_1;
            }

            @Override
            public void onBindView(ListItem1Binding binding, String item, int position) {
                binding.setItem(item);
                binding.setViewModel(mViewModel);
            }
        };

        adapter.updateData(arrayList);

        mBinding.recyclerView.setAdapter(adapter);
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    private void bindCustomListItem(ArrayList<ListItemModel> arrayList) {
        GenericAdapter<ListItemModel, ListItem2Binding> adapter = new GenericAdapter<ListItemModel, ListItem2Binding>(mContext) {
            @Override
            public int getLayoutId() {
                return R.layout.list_item_2;
            }

            @Override
            public void onBindView(ListItem2Binding binding, ListItemModel item, int position) {
                binding.setItem(item);
                binding.setViewModel(mViewModel);
            }
        };

        adapter.updateData(arrayList);

        mBinding.recyclerView.setAdapter(adapter);
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }


}
