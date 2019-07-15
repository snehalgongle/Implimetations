package com.snake.implimetations.di.builder;


import com.snake.implimetations.ui.follower.FollowerActivity;
import com.snake.implimetations.ui.follower.FollowerModule;
import com.snake.implimetations.ui.main.MainActivity;
import com.snake.implimetations.ui.main.MainModule;
import com.snake.implimetations.ui.main.MainViewModel;
import com.snake.implimetations.ui.profile.ProfileActivity;
import com.snake.implimetations.ui.profile.ProfileModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = ProfileModule.class)
    abstract ProfileActivity bindProfileActivity();

    @ContributesAndroidInjector(modules = FollowerModule.class)
    abstract FollowerActivity bindFollowerActivity();
}

