package com.snake.implimetations.data.model.api_request;


public class UserProfileRequest {
    private String userName;

    public UserProfileRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
