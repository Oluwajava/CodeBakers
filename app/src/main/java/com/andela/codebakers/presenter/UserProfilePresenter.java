package com.andela.codebakers.presenter;

import android.util.Log;

import com.andela.codebakers.httpclient.RequestInformation;
import com.andela.codebakers.listeners.RequestListener;
import com.andela.codebakers.utils.JsonUtil;
import com.andela.codebakers.model.User;
import com.andela.codebakers.view.UserProfileView;

/**
 * Created by Mayokun on 3/9/2017.
 */

public class UserProfilePresenter implements RequestListener{

    private UserProfileView userProfileView;
    private static User user;
    private static String url;
    public UserProfilePresenter(UserProfileView userProfileView) {
        this.userProfileView = userProfileView;
    }

    public void requestUserProfile(String url) {
        this.url = url;
        RequestInformation requestInformation = new RequestInformation(this);
        userProfileView.showProgressBar();
        requestInformation.getGithubUsers(url);

    }

    @Override
    public void onRequestFailed() {
        userProfileView.hideProgressBar();
        Log.d("Test", "failed");
    }

    @Override
    public void onRequestSucceed(String response) {
        userProfileView.hideProgressBar();
        user = JsonUtil.fromJson(response, User.class);
        userProfileView.populateWidgetsWithData(user);
    }

    public void viewUserProfile() {
        userProfileView.gotoBrowser(user.getHtmlUrl());
    }
}
