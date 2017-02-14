package com.codechallenge.bhanvi.codechallenge.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.codechallenge.bhanvi.codechallenge.R;
import com.codechallenge.bhanvi.codechallenge.fragments.RepoListFragment;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RepoListFragment webListFragment = new RepoListFragment();
        webListFragment.urlToOpen.subscribe(urlObserver);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.container, webListFragment);
        fragmentTransaction.commit();

    }

    Observer<String> urlObserver = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(String value) {
            Log.v(TAG, "Url to opne " + value);
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(value));
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Log.v(TAG, "handle error if there is activity to view url");
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.v(TAG, "On Error called");
        }

        @Override
        public void onComplete() {

        }
    };

}
