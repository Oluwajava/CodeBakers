package com.andela.codebakers.view;

import com.andela.codebakers.model.User;

/**
 * Created by Mayokun on 3/9/2017.
 */

public interface UserProfileView {

    void populateWidgetsWithData(User user);
    void gotoBrowser(String url);
    void hideProgressBar();
    void showProgressBar();
}
