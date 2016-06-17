package com.globant.android101.chuck_norris_jokes.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Response for Chuck Norris API.
 * Immutable.
 */
public class JokeResponse {

    @SerializedName("value")
    private final List<Joke> jokes;

    public JokeResponse(List<Joke> jokes) {
        this.jokes = jokes;
    }

    public List<Joke> getJokes() {
        return jokes;
    }
}
