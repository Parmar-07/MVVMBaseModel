package com.project.mvvmbasemodel.base;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import java.util.ArrayList;

public abstract class GenericHeaderAdapter<T, V extends ViewDataBinding, HV extends ViewDataBinding> extends GenericAdapter<T, V> {

    private static final int ITEM_HEADER_VIEW = 3;

    public GenericHeaderAdapter(Context _context) {
        super(_context);
    }

    protected abstract int getHeaderLayoutId();

    protected abstract void onBindHeaderView(HV binding, T item, int position);

    protected abstract T addNonBindHeaderItem();


    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return ITEM_HEADER_VIEW;
        }
        return ITEM_VIEW;

    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_HEADER_VIEW) {
            return new GenericViewHolder<HV>(DataBindingUtil.inflate(getInflater(), getHeaderLayoutId(), parent, false));
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {

        if (getItemViewType(position) == ITEM_HEADER_VIEW) {
            onBindHeaderView((HV) holder.mBinding, getListItems().get(position), position);
            return;
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public void updateData(ArrayList<T> _listItems) {
        if (_listItems == null) {
            _listItems = new ArrayList<>();
        }
        if (addNonBindHeaderItem() != null) {
            _listItems.add(0, addNonBindHeaderItem());
        }
        super.updateData(_listItems);
    }
}
