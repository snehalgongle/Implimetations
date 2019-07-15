package com.snake.implimetations.di.module;

import android.app.Application;

import android.content.Context;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.snake.implimetations.AppConstants;
import com.snake.implimetations.R;
import com.snake.implimetations.data.AppDataManager;
import com.snake.implimetations.data.DataManager;
import com.snake.implimetations.data.local.db.AppDatabase;
import com.snake.implimetations.data.local.db.AppDbHelper;
import com.snake.implimetations.data.local.db.IDbHelper;
import com.snake.implimetations.data.local.preference.AppPreferencesHelper;
import com.snake.implimetations.data.local.preference.IPreferencesHelper;
import com.snake.implimetations.data.remote.ApiHeader;
import com.snake.implimetations.data.remote.ApiHelper;
import com.snake.implimetations.data.remote.AppApiHelper;
import com.snake.implimetations.di.ApiInfo;
import com.snake.implimetations.di.DatabaseInfo;
import com.snake.implimetations.di.PreferenceInfo;
import com.snake.implimetations.rx.AppSchedulerProvider;
import com.snake.implimetations.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return /*BuildConfig.API_KEY*/ "";
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    IDbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    IPreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           IPreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                preferencesHelper.getUserName());
    }

}
