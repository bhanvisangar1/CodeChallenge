package com.codechallenge.bhanvi.codechallenge.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codechallenge.bhanvi.codechallenge.R;
import com.codechallenge.bhanvi.codechallenge.services.GitHubService;
import com.codechallenge.bhanvi.codechallenge.model.RepoInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by bhanvisangar on 2/11/17.
 */

public class RepoListFragment extends Fragment {
    private static final String TAG = RepoListFragment.class.getName();

    RecyclerView recyclerView;
    RepoAdapter adapter;
    ArrayList<RepoInfo> arrayList = new ArrayList<>();
    public BehaviorSubject<String> urlToOpen = BehaviorSubject.create();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GitHubService.getAllReposInfo().subscribe(observer);
        super.onViewCreated(view, savedInstanceState);
    }

    Observer<List<RepoInfo>> observer = new Observer<List<RepoInfo>>() {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(List<RepoInfo> repoInfos) {
            Log.v(TAG, "on next called");

            for (RepoInfo repoInfo : repoInfos) {
                arrayList.add(repoInfo);
            }
            adapter = new RepoAdapter(arrayList, urlToOpen);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void onError(Throwable e) {
            Log.v(TAG, "on error called");
        }

        @Override
        public void onComplete() {

        }
    };
}