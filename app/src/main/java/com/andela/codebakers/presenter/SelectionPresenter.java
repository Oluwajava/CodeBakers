package com.andela.codebakers.presenter;

import android.util.Log;

import com.andela.codebakers.httpclient.RequestInformation;
import com.andela.codebakers.listeners.RequestListener;
import com.andela.codebakers.utils.BakerType;
import com.andela.codebakers.model.GithubResponse;
import com.andela.codebakers.model.GithubUser;
import com.andela.codebakers.utils.JsonUtil;
import com.andela.codebakers.view.SelectionView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayokun on 3/6/2017.
 */

public class SelectionPresenter implements RequestListener{

    private static SelectionView selectionView;
    private static List<GithubUser> bakersList;
    private static final int LIST_OF_MASTER_BAKER = 10;

    private SelectionPresenter(SelectionView selectionView) {
        this.selectionView = selectionView;
    }


    public static SelectionPresenter instaceOfSelectionView(SelectionView view) {
        return new SelectionPresenter(view);
    }

    public void makeCallToRetrieveUsers() {
        RequestInformation requestInformation = new RequestInformation(this);
        selectionView.showProgressBar();
        requestInformation.getGithubUsers("https://api.github.com/search/users?q=location:lagos&sort=stars");

    }

    @Override
    public void onRequestFailed() {
        selectionView.hideProgressBar();
        Log.d("Test", "request failed");
    }

    @Override
    public void onRequestSucceed(String response) {
        selectionView.hideProgressBar();
        Log.d("Test", response);
        GithubResponse githubResponse = JsonUtil.fromJson(response, GithubResponse.class);

        List<GithubUser> githubUsers = githubResponse.getItems();

        bakersList = githubUsers;
        populateMasterBakerList();
        populateOtherBakerList();

    }

    public void populateMasterBakerList(){
        selectionView.setUpMasterBakersCardView(new ArrayList<GithubUser>(bakersList.subList(0, LIST_OF_MASTER_BAKER)));

    }

    public void populateOtherBakerList() {
        selectionView.setUpOtherBakersListView(new ArrayList<GithubUser>(bakersList.subList(LIST_OF_MASTER_BAKER+1, bakersList.size())));
    }
    public void viewUserProfile(int position, BakerType bakerType) {

        List<GithubUser> githubUsers = null;

        if(bakerType == BakerType.MASTER_BAKER){
            githubUsers = bakersList.subList(0, LIST_OF_MASTER_BAKER);
        }else if(bakerType == BakerType.OTHER_BAKER) {
            githubUsers = bakersList.subList(LIST_OF_MASTER_BAKER + 1, bakersList.size());
        }

        selectionView.gotoUserProfile(githubUsers.get(position));
    }


}
