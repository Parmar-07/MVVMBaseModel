package com.project.mvvmbasemodel.base;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.mvvmbasemodel.exceptions.AuthorizationException;
import com.project.mvvmbasemodel.exceptions.NoInternetException;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel<N extends BaseNavigator> extends ViewModel {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected N mNavigator;
    protected MutableLiveData<Boolean> dialogVisibility = new MutableLiveData<>();
    protected MutableLiveData<String> dialogMessage = new MutableLiveData<>();


    public void setNavigator(N mNavigator) {
        this.mNavigator = mNavigator;
    }


    protected void checkError(Throwable throwable) {
        String throwableMessage = throwable.getMessage();
        dialogVisibility.setValue(false);
        if (throwable instanceof AuthorizationException) {
            mNavigator.logOut();
        } else if (throwable instanceof NoInternetException) {
            mNavigator.onNoInternetConnection();
        } else if (throwable instanceof Exception) {
            if (throwableMessage != null && throwableMessage.length() > 150) {
                throwableMessage = throwableMessage.substring(0, 150);
            }
            mNavigator.onError("Something went wrong!");
        } else {
            mNavigator.onError(throwableMessage);
        }
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        mNavigator = null;
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
