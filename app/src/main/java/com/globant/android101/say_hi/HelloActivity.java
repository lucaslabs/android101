package com.globant.android101.say_hi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.globant.android101.R;

public class HelloActivity extends AppCompatActivity {

    // Constants
    public static final String KEY_NAME = "arg_name";
    public static final String KEY_LASTNAME = "arg_lastname";

    // Views
    private TextView txtHelloName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hello);

        bindViews();
        setValues();
    }

    private void bindViews() {
        txtHelloName = (TextView) findViewById(R.id.txt_hello_name);
    }

    private void setValues() {
        Bundle args = getIntent().getExtras();
        if (args != null) {
            String name = args.getString(KEY_NAME);
            String lastname = args.getString(KEY_LASTNAME);
            txtHelloName.setText(String.format(getString(R.string.say_hello), name, lastname));
        }
    }


}
