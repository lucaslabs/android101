package com.globant.android101;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.globant.android101.chuck_norris_jokes.ui.ChuckNorrisJokesActivity;
import com.globant.android101.hello_world.HelloWorldActivity;
import com.globant.android101.say_hi.SayHiActivity;

/**
 * Main Activity of Android 101 app.
 * The idea is to showcase:
 * 1. What an Activity is.
 * 2. XML layouts and how to bind views.
 * 3. Go to another Activity using Intent.
 *
 * @author Android team.
 */
public class MainActivity extends AppCompatActivity {

    // Views
    private Button btnHelloWorld;
    private Button btnSayHi;
    private Button btnHeyChuck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bind Activity with XML layout
        setContentView(R.layout.activity_main);

        bindViews();
        setListeners();
    }

    private void bindViews() {
        btnHelloWorld = (Button) findViewById(R.id.btn_hello_world);
        btnSayHi = (Button) findViewById(R.id.btn_say_hi);
        btnHeyChuck = (Button) findViewById(R.id.btn_hey_chuck);
    }

    private void setListeners() {
        btnHelloWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHelloWorldActivity();
            }
        });

        btnSayHi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSayHiActivity();
            }
        });

        btnHeyChuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToChuckNorrisJokesActivity();
            }
        });
    }

    private void goToHelloWorldActivity() {
        Intent intent = new Intent(MainActivity.this, HelloWorldActivity.class);
        startActivity(intent);
    }

    private void goToSayHiActivity() {
        Intent intent = new Intent(MainActivity.this, SayHiActivity.class);
        startActivity(intent);
    }

    private void goToChuckNorrisJokesActivity() {
        Intent intent = new Intent(MainActivity.this, ChuckNorrisJokesActivity.class);
        startActivity(intent);
    }

    // TODO Refactor to goToActivity(nextActivity) to DRY
}
