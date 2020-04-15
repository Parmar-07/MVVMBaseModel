package com.project.mvvmbasemodel.features;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.project.mvvmbasemodel.R;
import com.project.mvvmbasemodel.base.BaseActivity;

public class Utils {

    public static void switchToActivity(Context mContext,Class clazz) {
        mContext.startActivity(new Intent(mContext,clazz));
    }


    public static void showNoInternetMsg(Context mContext) {
        if (mContext instanceof BaseActivity) {
            ((BaseActivity) mContext).showMessageDialog(mContext.getString(R.string.no_internet_connection),
                    mContext.getString(R.string.no_internet_connection_msg), Utils.DialogRequestCode.NO_INTERNET);
        } else {
            showToast(mContext,mContext.getString(R.string.no_internet_connection_msg));
        }
    }


    public static void showErrorMsg(Context mContext, String msg) {
        if (mContext instanceof BaseActivity) {
            ((BaseActivity) mContext).showMessageDialog(mContext.getString(R.string.error_title), msg, R.drawable.ic_error, Utils.DialogRequestCode.ON_ERROR);
        } else {
            showToast(mContext,msg);
        }
    }


    public static void showToast(Context mContext, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public interface DialogRequestCode {

        int NO_INTERNET = 1002;
        int ON_ERROR = 1003;


    }

}
