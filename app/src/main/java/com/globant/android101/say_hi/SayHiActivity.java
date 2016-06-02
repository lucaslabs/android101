package com.globant.android101.say_hi;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.globant.android101.R;

public class SayHiActivity extends AppCompatActivity {

    // Views
    private TextInputLayout inputLayoutName, inputLayoutLastname; // Support floating label
    private EditText txtName, txtLastname;
    private Button btnSayHi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_say_hi);

        bindViews();
        setListeners();
    }

    private void bindViews() {
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutLastname = (TextInputLayout) findViewById(R.id.input_layout_lastname);
        txtName = (EditText) findViewById(R.id.txt_name);
        txtLastname = (EditText) findViewById(R.id.txt_lastname);
        btnSayHi = (Button) findViewById(R.id.btn_say_hi);
    }

    private void setListeners() {
        btnSayHi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });
    }

    private void validateFields() {
        if (!validateName()) {
            return;
        }

        if (!validateLastname()) {
            return;
        }

        goToHelloActivity();
    }

    private void goToHelloActivity() {
        // Data from this Activity to be passed as param to next Activity
        Bundle args = new Bundle();
        args.putString(HelloActivity.KEY_NAME, txtName.getText().toString());
        args.putString(HelloActivity.KEY_LASTNAME, txtLastname.getText().toString());

        Intent intent = new Intent(SayHiActivity.this, HelloActivity.class);

        // Add args
        intent.putExtras(args);

        startActivity(intent);
    }

    private boolean validateName() {
        if (txtName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setErrorEnabled(true);
            inputLayoutName.setError(getString(R.string.error_msg_name));
            requestFocus(txtName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateLastname() {
        if (txtLastname.getText().toString().trim().isEmpty()) {
            inputLayoutName.setErrorEnabled(true);
            inputLayoutLastname.setError(getString(R.string.error_msg_lastname));
            requestFocus(txtLastname);
            return false;
        } else {
            inputLayoutLastname.setErrorEnabled(false);
            return true;
        }
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
