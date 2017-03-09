package com.andela.codebakers.view;

import com.andela.codebakers.model.GithubUser;

import java.util.List;

/**
 * Created by Mayokun on 3/6/2017.
 */

public interface SelectionView {

    void setUpMasterBakersCardView(List<GithubUser> githubUsers);
    void setUpOtherBakersListView(List<GithubUser> githubUsers);
    void gotoUserProfile(GithubUser githubUser);
    void hideProgressBar();
    void showProgressBar();
}
