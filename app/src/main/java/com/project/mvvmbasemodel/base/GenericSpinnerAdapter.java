package com.project.mvvmbasemodel.base;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericSpinnerAdapter<T, V extends ViewDataBinding> extends BaseAdapter implements AdapterView.OnItemSelectedListener, View.OnTouchListener {

    private List<T> listItems;
    private LayoutInflater mInflater;

    public GenericSpinnerAdapter(Spinner spinner) {
        this.listItems = new ArrayList<>();
        mInflater = LayoutInflater.from(spinner.getContext());
        spinner.setAdapter(this);
        spinner.setOnItemSelectedListener(this);
        spinner.setOnTouchListener(this);
    }

    public void updateData(ArrayList<T> _listItems) {
        if (_listItems == null) {
            _listItems = new ArrayList<>();
        }
        listItems = _listItems;
        notifyDataSetChanged();
    }


    public abstract int getLayoutId();

    protected abstract void onItemSelected(T data, V binding);

    public abstract void onBindView(V binding, T item, int position);

    public abstract void onBindDropDownView(V binding, T item, int position);

    protected boolean onValidate(){
        return false;
    };


    public void clearAll() {
        listItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        GenericSpinnerViewHolder<V> viewHolder;
        if (convertView == null) {
            V bindings = DataBindingUtil.inflate(mInflater, getLayoutId(), parent, false);
            viewHolder = new GenericSpinnerViewHolder<V>(bindings);
            viewHolder.view = bindings.getRoot();
            viewHolder.view.setTag(viewHolder);
        } else {
            viewHolder = (GenericSpinnerViewHolder<V>) convertView.getTag();
        }

        if (listItems.size()>0){
            onBindView(viewHolder.mBinding, listItems.get(position), position);
        }
        return viewHolder.view;


    }

    @SuppressWarnings("unchecked")
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        GenericSpinnerViewHolder<V> viewHolder;
        if (convertView == null) {
            V bindings = DataBindingUtil.inflate(mInflater, getLayoutId(), parent, false);
            viewHolder = new GenericSpinnerViewHolder<V>(bindings);
            viewHolder.view = bindings.getRoot();
            viewHolder.view.setTag(viewHolder);
        } else {
            viewHolder = (GenericSpinnerViewHolder<V>) convertView.getTag();
        }

        onBindDropDownView(viewHolder.mBinding, listItems.get(position), position);


        return super.getDropDownView(position, viewHolder.mBinding.getRoot(), parent);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (listItems.size()>0){
            V binding = DataBindingUtil.findBinding(view);
            onItemSelected(listItems.get(position),binding);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return onValidate();
    }


}
