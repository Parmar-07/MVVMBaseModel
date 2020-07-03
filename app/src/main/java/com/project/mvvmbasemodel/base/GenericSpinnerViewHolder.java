package com.project.mvvmbasemodel.base;

import android.view.View;

import androidx.databinding.ViewDataBinding;

public class GenericSpinnerViewHolder<V extends ViewDataBinding> {

    public V mBinding;
    public View view;

    public GenericSpinnerViewHolder(V viewDataBinding) {
        view = viewDataBinding.getRoot();
        mBinding =  viewDataBinding;
    }
}