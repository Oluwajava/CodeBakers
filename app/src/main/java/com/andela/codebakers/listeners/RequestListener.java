package com.andela.codebakers.listeners;

/**
 * Created by Mayokun on 3/6/2017.
 */

public interface RequestListener {

    void onRequestFailed();

    void onRequestSucceed(String response);
}
