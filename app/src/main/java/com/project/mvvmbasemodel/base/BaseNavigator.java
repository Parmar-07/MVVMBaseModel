package com.project.mvvmbasemodel.base;

public interface BaseNavigator {

    void toast(String message);
    void onError(String errorMessage);

    void onNoInternetConnection();

    void logOut();
}