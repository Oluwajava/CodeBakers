package com.andela.codebakers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.andela.codebakers.presenter.SelectionPresenter;

import com.andela.codebakers.utils.BakerType;
import com.andela.codebakers.utils.Constants;
import com.andela.codebakers.utils.GitHubMasterAdapter;
import com.andela.codebakers.model.GithubUser;
import com.andela.codebakers.utils.GithubUserListAdapter;
import com.andela.codebakers.view.SelectionView;


import java.util.ArrayList;
import java.util.List;

import teaspoon.annotations.OnUi;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Mayokun on 2/20/2017.
 */
public class SelectionActivity extends AppCompatActivity implements SelectionView {

    private RecyclerView selectionRecyclerView;
    private RecyclerView.Adapter selectionAdapter;
    private RecyclerView.LayoutManager selectionLayoutManager;
    private ListView artistListView;
    private SelectionPresenter selectionPresenter;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_activity);

        selectionPresenter = SelectionPresenter.instaceOfSelectionView(this);

        widgetInitialization();
        requestGithubUsersFromServer();

        artistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectionPresenter.viewUserProfile(i, BakerType.OTHER_BAKER);
            }
        });

    }

    private void widgetInitialization() {
        selectionRecyclerView = (RecyclerView) findViewById(R.id.selectionRecyclerView);
        artistListView = (ListView) findViewById(R.id.artistListView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        linearLayout = (LinearLayout) findViewById(R.id.layout);
    }

    private void requestGithubUsersFromServer() {
        selectionPresenter.makeCallToRetrieveUsers();
    }

    @Override
    @OnUi
    public void setUpMasterBakersCardView(List<GithubUser> githubUsers) {
        selectionLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        selectionRecyclerView.setLayoutManager(selectionLayoutManager);
        selectionAdapter = new GitHubMasterAdapter((ArrayList<GithubUser>) githubUsers, this);
        selectionRecyclerView.setAdapter(selectionAdapter);
    }

    @Override
    @OnUi
    public void setUpOtherBakersListView(List<GithubUser> githubUsers) {
        final GithubUserListAdapter artistListAdapter = new GithubUserListAdapter(this, R.layout.artist_list, githubUsers);
        artistListView.setAdapter(artistListAdapter);
    }

    @Override
    public void gotoUserProfile(GithubUser githubUser) {
        Intent userProfileIntent = new Intent(SelectionActivity.this, UserProfile.class);
        userProfileIntent.putExtra(Constants.KEYS.GITHUB_USER, githubUser);
        startActivity(userProfileIntent);
    }

    @OnUi
    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
