package com.project.mvvmbasemodel.base;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatDialog;

import com.project.mvvmbasemodel.databinding.DialogCustomBinding;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class CustomDialog extends AppCompatDialog {

    private Context mContext;
    private DialogCustomBinding dialogCustomBinding;
    private CustomDialogListener customDialogListener;

    public CustomDialog(Context context) {
        super(context);
        mContext = context;
        dialogCustomBinding = DialogCustomBinding.inflate(LayoutInflater.from(mContext));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(dialogCustomBinding.getRoot());
        getWindow().setLayout(MATCH_PARENT, WRAP_CONTENT);
        setCancelable(false);


        dialogCustomBinding.txtNo.setOnClickListener(view -> {
            dismiss();
            if (customDialogListener != null) {
                customDialogListener.onNegativeButtonClick();
            }
        });
        dialogCustomBinding.txtYes.setOnClickListener(v -> {
            dismiss();
            if (customDialogListener != null) {
                customDialogListener.onPositiveButtonClick();
            }

        });

    }

    public void setNegativeButton(boolean isVisible) {
        if (isVisible) {
            dialogCustomBinding.txtNo.setVisibility(View.VISIBLE);
        } else {
            dialogCustomBinding.txtNo.setVisibility(View.GONE);
        }
    }

    public void setCloseIconVisibility(int iconResId) {
        if (iconResId == 0) {
            dialogCustomBinding.imgIcon.setVisibility(View.GONE);
        } else {
            dialogCustomBinding.imgIcon.setVisibility(View.VISIBLE);
            dialogCustomBinding.imgIcon.setImageResource(iconResId);
        }
    }

    public void setNegativeButtonText(String buttonText) {
        dialogCustomBinding.txtNo.setText(buttonText);
    }

    public void setPositiveButtonText(String buttonText) {
        dialogCustomBinding.txtYes.setText(buttonText);
    }


    public void setTitle(String dialogTitle) {
        setLine1("");
        setLine2("");
        if (TextUtils.isEmpty(dialogTitle)) {
            dialogCustomBinding.title.setVisibility(View.GONE);
        } else {
            dialogCustomBinding.title.setVisibility(View.VISIBLE);
            dialogCustomBinding.title.setText(dialogTitle);
        }

    }


    public void setMessage(String message) {
        setLine1("");
        setLine2("");
        if (TextUtils.isEmpty(message)) {
            dialogCustomBinding.message.setVisibility(View.GONE);
        } else {
            dialogCustomBinding.message.setVisibility(View.VISIBLE);
            dialogCustomBinding.message.setText(message);
        }
    }

    public void setCustomDialogListener(CustomDialogListener customDialogListener) {
        this.customDialogListener = customDialogListener;
    }

    public void setLine1(String line1) {
        if (TextUtils.isEmpty(line1)) {
            dialogCustomBinding.lblSubLine1.setVisibility(View.GONE);
        } else {
            dialogCustomBinding.lblSubLine1.setVisibility(View.VISIBLE);
            dialogCustomBinding.lblSubLine1.setText(line1);
        }
    }

    public void setLine2(String line2) {
        if (TextUtils.isEmpty(line2)) {
            dialogCustomBinding.lblSubLine2.setVisibility(View.GONE);
        } else {
            dialogCustomBinding.lblSubLine2.setVisibility(View.VISIBLE);
            dialogCustomBinding.lblSubLine2.setText(line2);
        }
    }

    public interface CustomDialogListener {
        void onPositiveButtonClick();

        void onNegativeButtonClick();
    }
}
