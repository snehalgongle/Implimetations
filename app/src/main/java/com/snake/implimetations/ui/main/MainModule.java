package com.snake.implimetations.ui.main;


import android.app.Application;

import com.snake.implimetations.data.DataManager;
import com.snake.implimetations.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new MainViewModel(dataManager, schedulerProvider);
    }

    @Provides
    MainListViewModel provideMainListViewModel(Application application, DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new MainListViewModel(application,dataManager, schedulerProvider);
    }
}
