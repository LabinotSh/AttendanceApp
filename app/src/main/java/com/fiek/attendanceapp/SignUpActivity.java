package com.fiek.attendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {


    private AppCompatButton btn;
    private EditText txName;
    private EditText txtUsername;
    private EditText txtPass;
    private TextView txtlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn = findViewById(R.id.btn_signup);
        txName = findViewById(R.id.input_name);
        txtUsername = findViewById(R.id.input_email);
        txtPass = findViewById(R.id.input_password);
        txtlogin = findViewById(R.id.link_login);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txName.getText().toString();
                String username = txtUsername.getText().toString();
                String pass = txtPass.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    txName.setError("please enter name");
                } else if (TextUtils.isEmpty(username)) {
                    txtUsername.setError("please enter email/username");
                } else if (TextUtils.isEmpty(pass)) {
                    txtPass.setError("please enter password");
                } else {


                    Admin admin = new Admin();
                    admin.setAdmin_name(name);
                    admin.setAdmin_username(username);
                    admin.setAdmin_password(pass);

                    DBAdapter dbAdapter = new DBAdapter(SignUpActivity.this);
                    dbAdapter.addAdmin(admin);

                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Admin Created Successfully!", Toast.LENGTH_SHORT).show();

                }
            }
        });


        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}
