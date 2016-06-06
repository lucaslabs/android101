package com.globant.android101.chuck_norris_jokes.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model class for a Joke.
 * Immutable.
 */
public class Joke implements Parcelable {
    private final long id;
    private final String joke;

    public Joke(long id, String joke) {
        this.id = id;
        this.joke = joke;
    }

    protected Joke(Parcel in) {
        id = in.readLong();
        joke = in.readString();
    }

    public long getId() {
        return id;
    }

    public String getJoke() {
        return joke;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(joke);
    }

    public static final Creator<Joke> CREATOR = new Creator<Joke>() {
        @Override
        public Joke createFromParcel(Parcel in) {
            return new Joke(in);
        }

        @Override
        public Joke[] newArray(int size) {
            return new Joke[size];
        }
    };
}
