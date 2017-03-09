package com.andela.codebakers.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andela.codebakers.R;
import com.andela.codebakers.model.GithubUser;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Mayokun on 2/20/2017.
 */

public class GithubUserListAdapter extends ArrayAdapter<GithubUser> {

    private List<GithubUser> items;
    private Context context;


    public GithubUserListAdapter(Context ctx, int txtViewResourceId, List<GithubUser> objects) {
        super(ctx, txtViewResourceId, objects);
        this.items = objects;
        this.context = ctx;
    }

    @Override
    public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
        return getCustomView(position, cnvtView, prnt);
    }

    @Override
    public View getView(int pos, View cnvtView, ViewGroup prnt) {
        return getCustomView(pos, cnvtView, prnt);
    }


    public View getCustomView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.artist_list, parent, false);
        GithubUser githubUser = items.get(position);
        TextView nameOfGithubUser;
        ImageView githubUserImage;

        nameOfGithubUser = (TextView) view.findViewById(R.id.nameOfArtist);
        githubUserImage = (CircleImageView) view.findViewById(R.id.artistImage);


        if (githubUser != null) {
            nameOfGithubUser.setText(githubUser.getLogin());
            Glide.with(context).load(githubUser.getAvaterUrl()).centerCrop().into(githubUserImage);
        }


        return view;

    }


}
