package com.globant.android101.chuck_norris_jokes.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Chuck Norris Jokes API.
 */
public interface ChuckNorrisJokesApi {

    String ENDPOINT = "http://api.icndb.com/";

    @GET("jokes/random/{amount}")
    Call<JokeResponse> getRandomJokes(@Path("amount") int amount);
}