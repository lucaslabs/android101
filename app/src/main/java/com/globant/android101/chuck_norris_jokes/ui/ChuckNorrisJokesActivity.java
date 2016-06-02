package com.globant.android101.chuck_norris_jokes.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.globant.android101.R;

public class ChuckNorrisJokesActivity extends AppCompatActivity implements JokeListFragment.OnJokeSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chuck_norris_jokes);

        if (savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.container, JokeListFragment.newInstance());
            transaction.commit();
        }
    }

    @Override
    public void onJokeSelected(int postion) {
        // No-op
    }
}
