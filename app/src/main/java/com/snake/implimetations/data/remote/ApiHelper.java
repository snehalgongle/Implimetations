
package com.snake.implimetations.data.remote;


import com.snake.implimetations.data.model.api.User;
import com.snake.implimetations.data.model.api.UserProfile;
import com.snake.implimetations.data.model.api_request.UserFollowerRequest;
import com.snake.implimetations.data.model.api_request.UserProfileRequest;
import com.snake.implimetations.data.model.api_request.UserRequest;

import java.util.List;

import io.reactivex.Single;


public interface ApiHelper {

    Single<List<User>> getUserApiCall(UserRequest request);

    Single<UserProfile> getUserProfilerApiCall(UserProfileRequest request);

    Single<List<User>> getUserFollowerApiCall(UserFollowerRequest request);

    ApiHeader getApiHeader();
}
