package com.andela.codebakers.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mayokun on 3/6/2017.
 */

public class GithubResponse implements Serializable{

    @SerializedName("total_count")
    private String totalCount;

    @SerializedName("incomplete_results")
    private String incompleteResults;

    @SerializedName("items")
    private List<GithubUser> items;

    public GithubResponse(String totalCount, String incompleteResults, List<GithubUser> items) {
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.items = items;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(String incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<GithubUser> getItems() {
        return items;
    }

    public void setItems(List<GithubUser> items) {
        this.items = items;
    }
}
