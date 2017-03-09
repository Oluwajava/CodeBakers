package com.andela.codebakers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.andela.codebakers.presenter.UserProfilePresenter;
import com.andela.codebakers.utils.Constants;
import com.andela.codebakers.model.GithubUser;
import com.andela.codebakers.model.User;
import com.andela.codebakers.view.UserProfileView;
import com.bumptech.glide.Glide;

import teaspoon.annotations.OnUi;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Mayokun on 3/8/2017.
 */

public class UserProfile extends AppCompatActivity implements UserProfileView{

    private UserProfilePresenter userProfilePresenter;
    private ImageView userImage;
    private TextView nameTextView, usernameTextView, followingTextView, followersTextView, repositoryTextView, emailTextView, companyTextView, locationTextView;
    private Button viewProfileButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        userProfilePresenter = new UserProfilePresenter(this);
        initializeWidgets();
        populateWidgetsWithData();

        viewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userProfilePresenter.viewUserProfile();
            }
        });

    }

    private void populateWidgetsWithData() {
        if(getIntent().hasExtra(Constants.KEYS.GITHUB_USER)) {
            GithubUser githubUser = (GithubUser) getIntent().getSerializableExtra(Constants.KEYS.GITHUB_USER);
            Glide.with(this).load(githubUser.getAvaterUrl()).centerCrop().into(userImage);
            userProfilePresenter.requestUserProfile(githubUser.getUrl());
        }
    }

    private void initializeWidgets() {
        userImage = (ImageView) findViewById(R.id.userImage);
        nameTextView = (TextView) findViewById(R.id.name);
        usernameTextView = (TextView) findViewById(R.id.username);
        followingTextView = (TextView) findViewById(R.id.following);
        followersTextView = (TextView) findViewById(R.id.follower);
        repositoryTextView = (TextView) findViewById(R.id.repository);
        emailTextView = (TextView) findViewById(R.id.email);
        companyTextView = (TextView) findViewById(R.id.company);
        locationTextView = (TextView) findViewById(R.id.location);
        viewProfileButton = (Button) findViewById(R.id.viewProfile);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @OnUi
    @Override
    public void populateWidgetsWithData(User user) {
        nameTextView.setText(user.getName());
        usernameTextView.setText("@"+user.getUserName());
        followersTextView.setText(user.getFollowers());
        followingTextView.setText(user.getFollowing());
        repositoryTextView.setText(user.getRepository());

        if(user.getEmail() != null) {
            emailTextView.setText(user.getEmail());
        }

        if(user.getCompany() != null) {
            companyTextView.setText(user.getCompany());
        }

        if(user.getLocation() != null) {
            locationTextView.setText(user.getLocation());
        }

    }

    @Override
    public void gotoBrowser(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @OnUi
    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
