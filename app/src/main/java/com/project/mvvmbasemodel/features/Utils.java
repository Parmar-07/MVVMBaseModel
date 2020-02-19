package com.project.mvvmbasemodel.features;

import android.content.Context;
import android.widget.Toast;

import com.project.mvvmbasemodel.R;
import com.project.mvvmbasemodel.base.BaseActivity;

public class Utils {

    public static void showNoInternetMsg(Context mContext) {
        if (mContext instanceof BaseActivity) {
            ((BaseActivity) mContext).showMessageDialog(mContext.getString(R.string.no_internet_connection),
                    mContext.getString(R.string.no_internet_connection_msg), Utils.DialogRequestCode.NO_INTERNET);
        } else {
            Toast.makeText(mContext, mContext.getString(R.string.no_internet_connection_msg), Toast.LENGTH_SHORT).show();
        }
    }


    public static void showErrorMsg(Context mContext, String msg) {
        if (mContext instanceof BaseActivity) {
            ((BaseActivity) mContext).showMessageDialog(mContext.getString(R.string.error_title), msg, R.drawable.ic_error, Utils.DialogRequestCode.ON_ERROR);
        } else {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }
    }


    public interface DialogRequestCode {

        int NO_INTERNET = 1002;
        int ON_ERROR = 1003;


    }

}
