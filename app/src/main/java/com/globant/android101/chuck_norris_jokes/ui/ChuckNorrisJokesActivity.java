package com.globant.android101.chuck_norris_jokes.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.globant.android101.R;
import com.globant.android101.chuck_norris_jokes.api.model.Joke;

/**
 * Third sample of Android 101 app.
 * The idea is to showcase:
 * 1. "One Activity to rule them (Fragments) all".
 * 2. Back stack.
 * 3. Master / Detail flow.
 *
 * @author Android team.
 */
public class ChuckNorrisJokesActivity extends AppCompatActivity implements JokeListFragment.OnJokeSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chuck_norris_jokes);

        if (savedInstanceState == null) {
            replaceFragment(JokeListFragment.newInstance(), false /* add to back stack */);
        }
    }

    @Override
    public void onJokeSelected(Joke joke) {
        replaceFragment(JokeDetailFragment.newInstance(joke), true /* add to backs tack */);
    }

    private void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null /* optional name for this back stack state */);
        }
        transaction.commit();
    }
}
