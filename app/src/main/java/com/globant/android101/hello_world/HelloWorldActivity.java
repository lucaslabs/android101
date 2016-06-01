package com.globant.android101.hello_world;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.globant.android101.R;

public class HelloWorldActivity extends AppCompatActivity {

    // Views
    private ImageView imgWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hello_world);

        bindViews();
        setListeners();
    }

    private void bindViews() {
        imgWorld = (ImageView) findViewById(R.id.img_world);
    }

    private void setListeners() {
        imgWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHelloWorldToast();
            }
        });
    }

    private void showHelloWorldToast() {
        Toast.makeText(HelloWorldActivity.this, R.string.common_hello_world, Toast.LENGTH_LONG).show();
    }
}
