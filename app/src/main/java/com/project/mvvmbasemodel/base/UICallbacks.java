package com.project.mvvmbasemodel.base;

public interface UICallbacks {

    int getLayoutID();

    void onBinding();

    Class getViewModel();

    BaseNavigator getNavigatorReference();

    String getScreenName();

}