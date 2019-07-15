package com.snake.implimetations.ui.profile;

import com.snake.implimetations.data.DataManager;
import com.snake.implimetations.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileModule {
    @Provides
    ProfileViewModel provideProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new ProfileViewModel(dataManager, schedulerProvider);
    }
}
