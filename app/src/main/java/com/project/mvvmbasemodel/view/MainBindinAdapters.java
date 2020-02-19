package com.project.mvvmbasemodel.view;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class MainBindinAdapters {

    @BindingAdapter({"message"})
    public static void welcomeMessage(TextView textView,String message){
        textView.setText(message);
    }

}
