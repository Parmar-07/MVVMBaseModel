package com.project.mvvmbasemodel.base;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.project.mvvmbasemodel.R;
import com.project.mvvmbasemodel.databinding.ToolbarBinding;
import com.project.mvvmbasemodel.features.Utils;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity implements BaseNavigator {

    protected T mBinding;
    protected V mViewModel;
    protected Context mContext;
    private LoadingDialog loadingDialog;
    private CustomDialog customDialog;
    private ToolbarBinding toolbarBinding;

    protected abstract int getLayoutID();

    protected abstract void onBinding();

    protected abstract Class getViewModel();

    protected abstract BaseNavigator getNavigatorReference();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        //overridePendingTransitionEnter();
        mBinding = DataBindingUtil.setContentView(BaseActivity.this, getLayoutID());
        mBinding.setLifecycleOwner(this);
        mViewModel = (V) ViewModelProviders.of(BaseActivity.this).get(getViewModel());
        mViewModel.setNavigator(getNavigatorReference());
        createDialog();
        createAlertDialog();
        onBinding();
    }


    /**
     * Init the custom alert dialog instance
     */
    private void createAlertDialog() {
        customDialog = new CustomDialog(mContext);
    }

    /**
     * show custom alert dialog with listener
     *
     * @param dialogTitle
     * @param message
     * @param requestCode
     */
    protected void showConfirmationDialog(String dialogTitle, String message, int requestCode) {
        customDialog.setTitle(dialogTitle);
        customDialog.setMessage(message);
        customDialog.setNegativeButton(true);
        customDialog.setNegativeButtonText(getString(R.string.btn_negative_custom_dialog));
        customDialog.setPositiveButtonText(getString(R.string.btn_positive_custom_dialog));
        customDialog.setCloseIconVisibility(0);
        customDialog.setCustomDialogListener(new CustomDialog.CustomDialogListener() {
            @Override
            public void onPositiveButtonClick() {
                onConfirmationDialogPositiveButton(requestCode);
            }

            @Override
            public void onNegativeButtonClick() {
                onConfirmationDialogNegativeButton(requestCode);
            }
        });
        customDialog.show();
    }

    protected void showConfirmationDialog(String dialogTitle, String message, String btnPositive, String btnNegative, int requestCode) {
        showConfirmationDialog(dialogTitle, message, btnPositive, btnNegative, 0, requestCode);
    }

    protected void showConfirmationDialog(String dialogTitle, String message, String btnPositive, String btnNegative, int iconId, int requestCode) {
        customDialog.setTitle(dialogTitle);
        customDialog.setMessage(message);
        customDialog.setNegativeButton(true);
        customDialog.setNegativeButtonText(btnNegative);
        customDialog.setPositiveButtonText(btnPositive);
        customDialog.setCloseIconVisibility(iconId);
        customDialog.setCustomDialogListener(new CustomDialog.CustomDialogListener() {
            @Override
            public void onPositiveButtonClick() {
                onConfirmationDialogPositiveButton(requestCode);
            }

            @Override
            public void onNegativeButtonClick() {
                onConfirmationDialogNegativeButton(requestCode);
            }
        });
        customDialog.show();
    }


    public void showMessageDialog(String dialogTitle, String message, int requestCode) {
        showMessageDialog(dialogTitle, message, 0, requestCode);
    }

    public void showMessageDialog(String dialogTitle, String message, int iconId, int requestCode) {
        showMessageDialog(dialogTitle, message, "", "", iconId, requestCode);
    }

    public void showMessageDialog(String dialogTitle, String message, String line1, String line2, int iconId, int requestCode) {
        customDialog.setTitle(dialogTitle);
        customDialog.setMessage(message);
        customDialog.setLine1(line1);
        customDialog.setLine2(line2);
        customDialog.setNegativeButton(false);
        customDialog.setNegativeButtonText(getString(R.string.btn_negative_custom_dialog));
        customDialog.setPositiveButtonText(TextUtils.isEmpty(line1) ? "OK" : "CLOSE");
        customDialog.setCloseIconVisibility(iconId);
        customDialog.setCustomDialogListener(new CustomDialog.CustomDialogListener() {
            @Override
            public void onPositiveButtonClick() {

                onConfirmationDialogPositiveButton(requestCode);

            }

            @Override
            public void onNegativeButtonClick() {
                onConfirmationDialogNegativeButton(requestCode);
            }
        });
        customDialog.show();
    }

    /**
     * create instance of loading dialog
     */
    private void createDialog() {
        loadingDialog = new LoadingDialog(BaseActivity.this);
        loadingDialog.setCancelable(false);

        mViewModel.dialogMessage.observe(BaseActivity.this, msg -> {
            if (loadingDialog != null) {
                loadingDialog.setDialogMessage(String.valueOf(msg));
            }
        });

        mViewModel.dialogVisibility.observe(this, (showDialog) -> {

            if ((Boolean) showDialog) {
                if (loadingDialog != null)
                    loadingDialog.showDialog();
            } else {
                if (loadingDialog != null && loadingDialog.isShowing())
                    loadingDialog.dismissDialog();
            }
        });


    }

    public void setLoadingDialogMsg(String msg) {
        mViewModel.dialogMessage.setValue(msg);
    }

    public void setLoadingDialogVisibility(boolean visibility) {
        mViewModel.dialogVisibility.setValue(visibility);
    }

    /**
     * set toolbar
     *
     * @param toolbarBinding
     * @param showBackArrow
     * @param title
     */
    protected void setToolbar(ToolbarBinding toolbarBinding, boolean showBackArrow, String title) {

        this.toolbarBinding = toolbarBinding;
        setToolbar(toolbarBinding.toolbar, showBackArrow);
        setTitle(title);

    }

    protected void setToolbar(Toolbar toolbar, boolean showBackArrow) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showBackArrow);
        getSupportActionBar().setDisplayShowHomeEnabled(showBackArrow);
        getSupportActionBar().setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.back_arrow));

    }

    public void setTitle(String title) {
        if (toolbarBinding != null) {
            toolbarBinding.txtToolbarTitle.setText(title);
        }
    }

    /**
     * Replace fragment with animation
     *
     * @param fragment
     * @param isAddToStack
     * @param container
     */
    protected void replaceFragmentWithAnimation(Fragment fragment, boolean isAddToStack, boolean isReplace, int container) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //   transaction.setCustomAnimations(R.anim.anim_slide_in_from_right_enter, R.anim.anim_slide_in_from_right_exit, R.anim.anim_slide_in_from_left_enter, R.anim.anim_slide_in_from_left_exit);
        if (isReplace) {
            transaction.replace(container, fragment);
        } else {
            transaction.add(container, fragment);
        }
        if (isAddToStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
    }

    /**
     * Replace fragment with animation
     *
     * @param fragment
     * @param isAddToStack
     * @param container
     */
    protected void replaceFragmentWithAnimation(Fragment parentFragment, Fragment fragment, boolean isAddToStack, boolean isReplace, int container) {
        FragmentTransaction transaction = parentFragment.getChildFragmentManager().beginTransaction();
        // transaction.setCustomAnimations(R.anim.anim_slide_in_from_right_enter, R.anim.anim_slide_in_from_right_exit, R.anim.anim_slide_in_from_left_enter, R.anim.anim_slide_in_from_left_exit);
        if (isReplace) {
            transaction.replace(container, fragment);
        } else {
            transaction.add(container, fragment);
        }


        if (isAddToStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
    }


    protected void onConfirmationDialogPositiveButton(int requestCode) {

    }

    /**
     * call when custom dialog negative clicked
     *
     * @param requestCode
     */
    protected void onConfirmationDialogNegativeButton(int requestCode) {


    }


    @Override
    public void onError(String errorMessage) {
        Utils.showErrorMsg(mContext, errorMessage);
    }

    @Override
    public void onNoInternetConnection() {
        Utils.showNoInternetMsg(mContext);

    }

    @Override
    public void logOut() {

    }

}
