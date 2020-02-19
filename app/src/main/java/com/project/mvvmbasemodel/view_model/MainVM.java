package com.project.mvvmbasemodel.view_model;

import com.project.mvvmbasemodel.base.BaseViewModel;
import com.project.mvvmbasemodel.features.main.MainNavigator;

public class MainVM extends BaseViewModel<MainNavigator> {

    public void welcomeToBaseMVVM() {
        mNavigator.popUpWelcomeMessage();
    }

    public void openDialog(boolean isForError) {
        if (isForError) {
            mNavigator.onError("Error Message!");
        } else {
            mNavigator.onNoInternetConnection();
        }
    }


}
