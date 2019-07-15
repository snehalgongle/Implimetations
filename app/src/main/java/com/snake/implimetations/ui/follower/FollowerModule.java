package com.snake.implimetations.ui.follower;

import com.snake.implimetations.data.DataManager;
import com.snake.implimetations.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class FollowerModule {
    @Provides
    FollowerViewModel provideFollowerViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new FollowerViewModel(dataManager, schedulerProvider);
    }
}
