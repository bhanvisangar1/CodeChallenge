package com.codechallenge.bhanvi.codechallenge.services;

import com.codechallenge.bhanvi.codechallenge.model.RepoInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by bhanvisangar on 2/12/17.
 */

public interface GithubApi {
    @GET("/users/ocramius/repos")
    Observable<List<RepoInfo>> getUser();
}

