package com.project.mvvmbasemodel.view_model;

import androidx.databinding.ObservableBoolean;

import com.project.mvvmbasemodel.base.BaseViewModel;
import com.project.mvvmbasemodel.features.main.MainNavigator;
import com.project.mvvmbasemodel.model.ListItemModel;

import java.util.ArrayList;

public class MainVM extends BaseViewModel<MainNavigator> {
    public ObservableBoolean isListPreview = new ObservableBoolean(false);

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


    public void openListView(boolean isCustomView) {
        isListPreview.set(!isListPreview.get());
        if (isCustomView) {

            ArrayList<ListItemModel> arrayList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                arrayList.add(new ListItemModel("Item " + (i + 1),"SubItem " + (i + 1)));
            }
            mNavigator.onCustomItemList(arrayList);


        } else {
            ArrayList<String> arrayList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                arrayList.add("Item " + (i + 1));
            }

            mNavigator.onSimpleItemList(arrayList);
        }
    }


    public void onSimpleItemClick(String item) {
        mNavigator.toast(item);
    }

    public void onCustomItemClick1(ListItemModel item) {
        mNavigator.toast(item.getItem1());
    }

    public void onCustomItemClick2(ListItemModel item) {
        mNavigator.toast(item.getItem2());
    }


}
