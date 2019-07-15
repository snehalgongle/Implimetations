package com.snake.implimetations.ui.profile;

import androidx.lifecycle.MutableLiveData;

import com.snake.implimetations.data.DataManager;
import com.snake.implimetations.data.model.api.User;
import com.snake.implimetations.data.model.api.UserProfile;
import com.snake.implimetations.data.model.api_request.UserProfileRequest;
import com.snake.implimetations.data.model.api_request.UserRequest;
import com.snake.implimetations.rx.SchedulerProvider;
import com.snake.implimetations.ui.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel extends BaseViewModel<IProfileNavigator> {
    MutableLiveData<UserProfile> userProfileResponse=new MutableLiveData<>();
    MutableLiveData<Throwable> errorResponse=new MutableLiveData<>();

    public ProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void getUserProfile(int id,int position,String userName){
        getCompositeDisposable().add(getDataManager().getUserProfilerApiCall(new UserProfileRequest(userName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userProfileResponse::setValue, throwable -> errorResponse.setValue(throwable)));

    }
}
