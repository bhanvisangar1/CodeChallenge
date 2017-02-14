package com.codechallenge.bhanvi.codechallenge.services;

import com.codechallenge.bhanvi.codechallenge.model.RepoInfo;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by bhanvisangar on 2/13/17.
 */

public class GitHubService {

    private static final String END_POINT = "https://api.github.com/";

    private static GithubApi getService() {
        GithubApi githubApi = new Retrofit.Builder()
                .baseUrl(END_POINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(GithubApi.class);
        return githubApi;
    }

    public static Observable<List<RepoInfo>> getAllReposInfo() {
        return GitHubService.getService().getUser().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
