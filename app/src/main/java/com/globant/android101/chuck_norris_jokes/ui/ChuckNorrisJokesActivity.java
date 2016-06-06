package com.globant.android101.chuck_norris_jokes.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.globant.android101.R;
import com.globant.android101.chuck_norris_jokes.api.Joke;

public class ChuckNorrisJokesActivity extends AppCompatActivity implements JokeListFragment.OnJokeSelectedListener {


    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chuck_norris_jokes);

        if (savedInstanceState == null) {
            replaceFragment(JokeListFragment.newInstance(), false /* add to backstack */);
        }
    }

    @Override
    public void onJokeSelected(Joke joke) {
        replaceFragment(JokeDetailFragment.newInstance(joke), true /* add to backstack */);
    }

    private void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
