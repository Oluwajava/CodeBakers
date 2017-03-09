package com.andela.codebakers.httpclient;

import com.andela.codebakers.listeners.RequestListener;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Mayokun on 3/6/2017.
 */

public class RequestInformation implements Callback {

    private OkHttpClient client;
    private RequestListener requestListener;
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public RequestInformation(RequestListener requestListener) {
        client = new OkHttpClient();
        this.requestListener = requestListener;
    }

    public void getGithubUsers(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request)
                .enqueue(this);
    }
    @Override
    public void onFailure(Request request, IOException e) {
        requestListener.onRequestFailed();
    }

    @Override
    public void onResponse(Response response) throws IOException {
        requestListener.onRequestSucceed(response.body().string());
    }
}
