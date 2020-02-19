package com.project.mvvmbasemodel.base;

public interface BaseNavigator {

    void onError(String errorMessage);

    void onNoInternetConnection();

    void logOut();
}