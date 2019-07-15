package com.snake.implimetations.ui.main;

import androidx.lifecycle.MutableLiveData;

import com.snake.implimetations.data.DataManager;
import com.snake.implimetations.data.model.api.User;
import com.snake.implimetations.data.model.api_request.UserRequest;
import com.snake.implimetations.rx.SchedulerProvider;
import com.snake.implimetations.ui.base.BaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel<IMainNavigator> {
    MutableLiveData<List<User>> userResponse = new MutableLiveData<>();
    MutableLiveData<Throwable> errorResponse = new MutableLiveData<>();

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void getUserListFromServer() {
        getCompositeDisposable().add(getDataManager().getUserApiCall(new UserRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userResponse::setValue, throwable -> errorResponse.setValue(throwable)));

    }
}
