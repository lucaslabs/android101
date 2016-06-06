package com.globant.android101.chuck_norris_jokes.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.globant.android101.R;
import com.globant.android101.chuck_norris_jokes.api.ChuckNorrisJokesApi;
import com.globant.android101.chuck_norris_jokes.api.Joke;
import com.globant.android101.chuck_norris_jokes.api.JokeResponse;
import com.globant.android101.chuck_norris_jokes.ui.adapter.JokeAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokeListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    // Constants
    private static final int MAX_JOKES_PER_REQUEST = 15;

    // Attributes
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvJokes;
    private JokeAdapter adapter;
    private ProgressBar progressBar;

    private ChuckNorrisJokesApi api;
    private List<Joke> jokes;

    private OnJokeSelectedListener jokeSelectedListener;

    /**
     * Factory method to create and to return a JokeListFragment.
     */
    public static JokeListFragment newInstance() {
        return new JokeListFragment();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnJokeSelectedListener {
        void onJokeSelected(Joke joke);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnJokeSelectedListener) {
            jokeSelectedListener = (OnJokeSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnJokeSelectedListener.");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        jokes = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_joke_list, container, false);

        bindViews(view);
        setListeners();

        return view;
    }

    private void bindViews(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);

        // Recycler view
        rvJokes = (RecyclerView) view.findViewById(R.id.rv_jokes);

        // Layout manager
        rvJokes.setLayoutManager(new LinearLayoutManager(getContext()));

        // Adapter
        adapter = new JokeAdapter(jokeSelectedListener);
        rvJokes.setAdapter(adapter);

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    }

    private void setListeners() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (jokes.isEmpty()) {
            showProgressBar();
            getJokes(false /* is not refresh*/);
        } else {
            adapter.addJokes(jokes);
        }
    }

    @Override
    public void onRefresh() {
        getJokes(true /* is refresh*/);
    }

    private void showProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void getJokes(boolean isRefresh) {
        api = createApi();

        Call<JokeResponse> call = api.getRandomJokes(MAX_JOKES_PER_REQUEST);
        call.enqueue(getJokesCallback(isRefresh));
    }

    private ChuckNorrisJokesApi createApi() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ChuckNorrisJokesApi.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(ChuckNorrisJokesApi.class);
        }
        return api;
    }

    @NonNull
    private Callback<JokeResponse> getJokesCallback(final boolean isRefresh) {
        return new Callback<JokeResponse>() {
            @Override
            public void onResponse(Call<JokeResponse> call, Response<JokeResponse> response) {
                if (isRefresh) {
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    hideProgressBar();
                }

                jokes.addAll(response.body().getJokes());
                adapter.addJokes(response.body().getJokes());

                // Scroll to recently added views
                rvJokes.smoothScrollToPosition(jokes.size() - MAX_JOKES_PER_REQUEST);
            }

            @Override
            public void onFailure(Call<JokeResponse> call, Throwable t) {
                hideProgressBar();
                Toast.makeText(getContext(), R.string.error_msg_jokes, Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    public void onDetach() {
        super.onDetach();
        jokeSelectedListener = null;
    }
}
