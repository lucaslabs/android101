package com.globant.android101.hello_world;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.globant.android101.R;

/**
 * First sample of Android 101 app.
 * The idea is to showcase:
 * 1. Activity lifecycle.
 * 2. Show what happens on configuration changes: rotate the device.
 * 3. Save and restore instance state.
 *
 * @author Android team.
 */
public class HelloWorldActivity extends AppCompatActivity {

    // Constants
    private static final String TAG = HelloWorldActivity.class.getSimpleName();

    // Views
    private ImageView imgWorld;

    // Attributes
    private int tapCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hello_world);

        bindViews();
        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    private void bindViews() {
        imgWorld = (ImageView) findViewById(R.id.img_world);
    }

    private void setListeners() {
        imgWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapCounter++;
                showHelloWorldToast();
            }
        });
    }

    // TODO Save tapCounter value/state!!!

    private void showHelloWorldToast() {
        String message = String.format(getString(R.string.hello_world_msg), tapCounter);
        Toast.makeText(HelloWorldActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }
}
