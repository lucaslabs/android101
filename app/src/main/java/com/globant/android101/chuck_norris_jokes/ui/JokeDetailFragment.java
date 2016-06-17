package com.globant.android101.chuck_norris_jokes.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.globant.android101.R;
import com.globant.android101.chuck_norris_jokes.api.model.Joke;

/**
 * Fragment that shows the selected Chuck Norris joke.
 *
 * @author Android team.
 */
public class JokeDetailFragment extends Fragment {

    private static final String KEY_JOKE = "joke";

    private Joke selectedJoke;

    public JokeDetailFragment() {
    }

    /**
     * Factory method to create and to return a JokeDetailFragment.
     */
    public static JokeDetailFragment newInstance(Joke joke) {
        JokeDetailFragment fragment = new JokeDetailFragment();
        Bundle args = new Bundle();

        // Parcelable
        args.putParcelable(KEY_JOKE, joke);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { // arguments is a Bundle
            selectedJoke = getArguments().getParcelable(KEY_JOKE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_joke_detail, container, false);

        // Bind views
        TextView textView = (TextView) view.findViewById(R.id.txt_joke);
        if (selectedJoke != null) {
            textView.setText(selectedJoke.getJoke());
        }

        return view;
    }
}
