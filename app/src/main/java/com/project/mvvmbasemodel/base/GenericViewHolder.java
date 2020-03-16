package com.project.mvvmbasemodel.base;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class GenericViewHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public V mBinding;

    public GenericViewHolder(V viewDataBinding) {
        super(viewDataBinding.getRoot());
        mBinding =  viewDataBinding;
    }
}