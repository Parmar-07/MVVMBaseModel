package com.project.mvvmbasemodel.data.repo;

import com.project.mvvmbasemodel.data.local.LocalDataProvider;
import com.project.mvvmbasemodel.data.network.NetworkDataProvider;

public interface DataRepo extends LocalDataProvider, NetworkDataProvider {
}
