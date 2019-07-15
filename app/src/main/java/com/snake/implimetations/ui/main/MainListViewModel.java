package com.snake.implimetations.ui.main;

import android.app.Application;


import androidx.databinding.ObservableArrayList;

import com.snake.implimetations.data.DataManager;
import com.snake.implimetations.data.model.api.User;
import com.snake.implimetations.rx.SchedulerProvider;
import com.snake.implimetations.ui.base.BaseApplicationViewModel;

public class MainListViewModel extends BaseApplicationViewModel<IMainNavigator> {
    ObservableArrayList<User> usersItemList = new ObservableArrayList<>();

    MainListViewModel(Application application, DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(application,dataManager,schedulerProvider);
    }



}
