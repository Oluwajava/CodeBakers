package com.andela.codebakers.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andela.codebakers.R;
import com.andela.codebakers.UserProfile;
import com.andela.codebakers.model.GithubUser;
import com.andela.codebakers.presenter.SelectionPresenter;

import com.andela.codebakers.view.SelectionView;
import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayokun on 2/20/2017.
 */

public class GitHubMasterAdapter extends RecyclerView.Adapter<GitHubMasterAdapter.GitHubMasterViewHolder> {
    private static ArrayList<GithubUser> githubUserArrayList = new ArrayList<>();
    private static Context context;

    public GitHubMasterAdapter(ArrayList<GithubUser> githubUserArrayList, Context context){
        this.githubUserArrayList = githubUserArrayList;
        this.context = context;
    }

    @Override
    public GitHubMasterAdapter.GitHubMasterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_card, parent, false);

        GitHubMasterAdapter.GitHubMasterViewHolder connectViewHolder = new GitHubMasterAdapter.GitHubMasterViewHolder(view);

        return connectViewHolder;
    }

    @Override
    public void onBindViewHolder(GitHubMasterViewHolder holder, int position) {
        GithubUser githubUser = githubUserArrayList.get(position);
        if(position > 0)
            Glide.with(context).load(githubUser.getAvaterUrl()).centerCrop().into(holder.artistImage);
        else
            Glide.with(context).load(githubUser.getAvaterUrl()).centerCrop().into(holder.artistImage);

        holder.name.setText(githubUser.getLogin());
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return githubUserArrayList.size();
    }

    public static class GitHubMasterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, SelectionView {
        SelectionPresenter selectionPresenter;
        ImageView artistImage;
        TextView name;
        public GitHubMasterViewHolder(View view){
            super(view);
            artistImage = (ImageView)view.findViewById(R.id.artistImage);
            name = (TextView)view.findViewById(R.id.name);
            selectionPresenter = SelectionPresenter.instaceOfSelectionView(this);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            selectionPresenter.viewUserProfile(position, BakerType.MASTER_BAKER);
        }


        @Override
        public void setUpMasterBakersCardView(List<GithubUser> githubUsers) {

        }

        @Override
        public void setUpOtherBakersListView(List<GithubUser> githubUsers) {

        }

        @Override
        public void gotoUserProfile(GithubUser githubUser) {
            Intent userProfileIntent = new Intent(context, UserProfile.class);
            userProfileIntent.putExtra(Constants.KEYS.GITHUB_USER, githubUser);
            context.startActivity(userProfileIntent);
        }

        @Override
        public void hideProgressBar() {

        }

        @Override
        public void showProgressBar() {

        }


    }
}
