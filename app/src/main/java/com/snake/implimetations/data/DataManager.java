package com.snake.implimetations.data;

import com.snake.implimetations.data.local.db.IDbHelper;
import com.snake.implimetations.data.local.preference.IPreferencesHelper;
import com.snake.implimetations.data.remote.ApiHelper;

public interface DataManager extends IDbHelper, IPreferencesHelper, ApiHelper {

}

