package com.project.mvvmbasemodel.features.main;

import com.project.mvvmbasemodel.base.BaseNavigator;
import com.project.mvvmbasemodel.model.ListItemModel;

import java.util.ArrayList;

public interface MainNavigator  extends BaseNavigator {

    void popUpWelcomeMessage();

    void onSimpleItemList(ArrayList<String> arrayList);

    void onCustomItemList(ArrayList<ListItemModel> arrayList);
}
