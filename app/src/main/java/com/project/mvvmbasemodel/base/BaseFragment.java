package com.project.mvvmbasemodel.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


/**
 * Created by Ganesh on 7/7/17.
 */

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment implements UICallbacks {

    protected Context mContext;
    protected T mBinding;
    protected V mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutID(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getContext();
        mBinding.setLifecycleOwner(this);
        if (isActivityViewModel()) {
            mViewModel = (V) ViewModelProviders.of(getActivity()).get(getViewModel());
        } else {
            mViewModel = (V) ViewModelProviders.of(this).get(getViewModel());
            mViewModel.setNavigator(getNavigatorReference());
        }
        if (!isActivityViewModel()) {
            createDialog();
        }
        onBinding();
    }

    public void setTitle(String title) {
        getBaseActivity().setTitle(title);
    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    protected void showMessageDialog(String dialogTitle, String message, int requestCode) {
        getBaseActivity().showMessageDialog(dialogTitle, message, requestCode);
    }

    protected void showConfirmationDialog(String dialogTitle, String message, int requestCode) {
        getBaseActivity().showConfirmationDialog(dialogTitle, message, requestCode);
    }

    /**
     * Replace fragment with animation
     *
     * @param fragment
     * @param isAddToStack
     * @param container
     */
    protected void replaceFragmentWithAnimation(Fragment fragment, boolean isAddToStack,boolean isReplace, int container) {
        getBaseActivity().replaceFragmentWithAnimation(fragment, isAddToStack, isReplace,container);
    }

    protected void replaceFragmentWithAnimation(Fragment parentFragment, Fragment fragment, boolean isAddToStack,boolean isReplace, int container) {
        getBaseActivity().replaceFragmentWithAnimation(parentFragment, fragment, isAddToStack,isReplace, container);
    }

    private void createDialog() {
        mViewModel.dialogMessage.observe(this, msg -> {
            getBaseActivity().setLoadingDialogMsg(String.valueOf(msg));
        });

        mViewModel.dialogVisibility.observe(this, (showDialog) -> {
            getBaseActivity().setLoadingDialogVisibility((Boolean) showDialog);
        });

    }


    /**
     * call when custom dialog positive button clicked
     *
     * @param requestCode
     */
    protected void onConfirmationDialogPositiveButton(int requestCode) {

    }

    protected abstract boolean isActivityViewModel();
}
