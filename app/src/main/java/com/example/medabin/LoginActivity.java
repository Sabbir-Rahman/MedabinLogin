package com.example.medabin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    LoginDatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new LoginDatabaseHelper(this);
        mTextUsername=(EditText)findViewById(R.id.username);
        mTextPassword=(EditText)findViewById(R.id.password);
        mButtonLogin=(Button) findViewById(R.id.login);
        mTextViewRegister=(TextView) findViewById(R.id.registerNow);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=mTextUsername.getText().toString().trim();
                String pswd=mTextPassword.getText().toString().trim();
                Boolean res =db.checkUser(user,pswd);
                if(res==true)
                {
                    Toast.makeText(LoginActivity.this,"Welcome to medabin",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
