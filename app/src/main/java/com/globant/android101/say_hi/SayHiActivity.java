package com.globant.android101.say_hi;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        txtName.addTextChangedListener(new CustomTextWatcher(txtName));
        txtLastname.addTextChangedListener(new CustomTextWatcher(txtLastname));

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

        if (!validateLastame()) {
            return;
        }

        goToHelloActivity();
    }

    private void goToHelloActivity() {
        // TODO-LMN Implement HelloActivity passing name and lastname as a Bundle
//        Intent intent = new Intent(SayHiActivity.this, HelloActivity.class);
//        startActivity(intent);
    }

    private boolean validateName() {
        if (txtName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(txtName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateLastame() {
        if (txtLastname.getText().toString().trim().isEmpty()) {
            inputLayoutLastname.setError(getString(R.string.err_msg_lastname));
            requestFocus(txtLastname);
            return false;
        } else {
            inputLayoutLastname.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    // Inned class
    private class CustomTextWatcher implements TextWatcher {
        private View view;

        public CustomTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // No-op
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // No-op
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (view.getId() == R.id.txt_name) {
                validateName();
            }
        }
    }
}
