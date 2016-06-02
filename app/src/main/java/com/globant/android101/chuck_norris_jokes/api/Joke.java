package com.globant.android101.chuck_norris_jokes.api;

/**
 * Model class for a Joke.
 * Immutable.
 */
public class Joke {
    private final long id;
    private final String joke;

    public Joke(long id, String joke) {
        this.id = id;
        this.joke = joke;
    }

    public long getId() {
        return id;
    }

    public String getJoke() {
        return joke;
    }
}
