package com.globant.android101.chuck_norris_jokes.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.globant.android101.R;
import com.globant.android101.chuck_norris_jokes.api.Joke;
import com.globant.android101.chuck_norris_jokes.ui.JokeListFragment.OnJokeSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to show and to recycle Joke views.
 */
public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.JokeViewHolder> {

    private OnJokeSelectedListener onJokeSelectedListener;
    private List<Joke> jokes;

    public JokeAdapter(OnJokeSelectedListener onJokeSelectedListener) {
        this.onJokeSelectedListener = onJokeSelectedListener;
        this.jokes = new ArrayList<>();
    }

    @Override
    public JokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item, parent, false);
        return new JokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JokeViewHolder holder, int position) {
        Joke joke = jokes.get(position);
        holder.selectedJoke = joke;
        holder.txtJoke.setText(joke.getJoke());
    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }

    public void addJokes(List<Joke> moreJokes) {
        jokes.addAll(moreJokes);
        notifyDataSetChanged();
    }

    public List<Joke> getAllJokes() {
        return jokes;
    }

    public class JokeViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtJoke;
        private Joke selectedJoke;

        public JokeViewHolder(View view) {
            super(view);
            txtJoke = (TextView) view.findViewById(R.id.txt_joke);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onJokeSelectedListener.onJokeSelected(selectedJoke);
                }
            });
        }
    }
}
