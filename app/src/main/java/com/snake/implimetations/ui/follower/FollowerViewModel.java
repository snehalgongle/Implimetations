package com.snake.implimetations.ui.follower;

import androidx.lifecycle.MutableLiveData;

import com.snake.implimetations.data.DataManager;
import com.snake.implimetations.data.model.api.User;
import com.snake.implimetations.data.model.api_request.UserFollowerRequest;
import com.snake.implimetations.rx.SchedulerProvider;
import com.snake.implimetations.ui.base.BaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FollowerViewModel extends BaseViewModel<IFollowerNavigator> {
    MutableLiveData<List<User>> userFollowerResponse=new MutableLiveData<>();
    MutableLiveData<Throwable> errorResponse=new MutableLiveData<>();

    public FollowerViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void getFollowers(){
        getCompositeDisposable().add(getDataManager().getUserFollowerApiCall(new UserFollowerRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userFollowerResponse::setValue, throwable -> errorResponse.setValue(throwable)));

    }
}
