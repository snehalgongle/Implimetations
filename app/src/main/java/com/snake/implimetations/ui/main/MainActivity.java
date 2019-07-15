package com.snake.implimetations.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.UrlQuerySanitizer;
import android.os.BaseBundle;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;


import com.snake.implimetations.AppUtils;
import com.snake.implimetations.BR;
import com.snake.implimetations.R;
import com.snake.implimetations.adapter.BaseRecyclerAdapter;
import com.snake.implimetations.adapter.OnDataBindCallback;
import com.snake.implimetations.data.model.api.User;
import com.snake.implimetations.databinding.ActivityMainBinding;
import com.snake.implimetations.databinding.UserListItemBinding;
import com.snake.implimetations.ui.base.BaseActivity;
import com.snake.implimetations.ui.profile.ProfileActivity;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.snake.implimetations.AppConstants.POSITION;
import static com.snake.implimetations.AppConstants.USER_ID;
import static com.snake.implimetations.AppConstants.USER_NAME;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements IMainNavigator, OnDataBindCallback<UserListItemBinding>, AdapterView.OnItemSelectedListener {

    @Inject
    MainViewModel mainViewModel;

    @Inject
    MainListViewModel mainListViewModel;

    ActivityMainBinding activityMainBinding;
    BaseRecyclerAdapter<User, UserListItemBinding> adapter;
    int pageNumber = 1;

    @Override
    public int getBindingVariable() {
        return BR.mainViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        return mainViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = getViewDataBinding();
        mainViewModel.setNavigator(this);
        setUpRecycleView();
        if (isNetworkConnected()) {
            mainViewModel.getUserListFromServer();
        }
        observerResponse();
        getViewDataBinding().getRoot();
    }

    private void setUpRecycleView() {

        getViewDataBinding().recycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseRecyclerAdapter<>(R.layout.user_list_item,
                com.snake.implimetations.BR.users, mainListViewModel.usersItemList,
                null,
                this);
        getViewDataBinding().recycleView.setAdapter(adapter);

        adapter.setScrolllistener(getViewDataBinding().recycleView);
        adapter.setOnLoadMoreListener(() -> {
            //Log.d("loadMore","loadmore Called()");
//            if (!TextUtils.isEmpty(nextUrl)) {
//                UrlQuerySanitizer sanitzer = new UrlQuerySanitizer(nextUrl);
//                pageNumber = sanitzer.getValue("page");
//            } else {
//                showToast(R.string.nomoreusers);
//            }

        });
    }

    private void observerResponse() {
        mainViewModel.userResponse.observe(this, user -> {
            Log.d(this.getClass().getSimpleName(), "observerResponse: " + user.get(0).getLogin());
        });
        mainViewModel.errorResponse.observe(this, this::handleApiError);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(View view, int position, UserListItemBinding userListItemBinding) {
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, position);
        bundle.putInt(USER_ID, mainListViewModel.usersItemList.get(position).getId());
        bundle.putString(USER_NAME, mainListViewModel.usersItemList.get(position).getLogin());
        goTo(ProfileActivity.class, bundle);
    }

    @Override
    public void onItemLongClick(View view, int position, UserListItemBinding userListItemBinding) {

    }

    @Override
    public void onDataBind(UserListItemBinding userListItemBinding, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {

    }


    @Override
    public void onClickView(View var1) {

    }

    @Override
    public void goTo(@NotNull Class<?> clazz, Bundle mExtras) {
        AppUtils.NavigatTo(this, clazz, mExtras);
        finish();
    }
}
