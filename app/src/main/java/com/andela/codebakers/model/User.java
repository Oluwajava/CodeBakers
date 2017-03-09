package com.andela.codebakers.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mayokun on 3/9/2017.
 */

public class User implements Serializable{

    @SerializedName("name")
    private String name;

    @SerializedName("login")
    private String userName;

    @SerializedName("company")
    private String company;

    @SerializedName("location")
    private String location;

    @SerializedName("email")
    private String email;

    @SerializedName("public_repos")
    private String repository;

    @SerializedName("followers")
    private String followers;

    @SerializedName("following")
    private String following;

    @SerializedName("html_url")
    private String htmlUrl;

    public User(String name, String userName, String company, String location, String email, String repository, String followers, String following, String htmlUrl) {
        this.name = name;
        this.userName = userName;
        this.company = company;
        this.location = location;
        this.email = email;
        this.repository = repository;
        this.followers = followers;
        this.following = following;
        this.htmlUrl = htmlUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}
