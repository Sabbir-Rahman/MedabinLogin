package com.example.medabin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    LoginDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db=new LoginDatabaseHelper(this);
        mTextUsername=(EditText)findViewById(R.id.username);
        mTextPassword=(EditText)findViewById(R.id.password);
        mTextCnfPassword=(EditText)findViewById(R.id.conf_password);
        mButtonRegister=(Button) findViewById(R.id.register);
        mTextViewLogin=(TextView) findViewById(R.id.login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=mTextUsername.getText().toString().trim();
                String pswd=mTextPassword.getText().toString().trim();
                String cnf_pswd=mTextCnfPassword.getText().toString().trim();
                if(pswd.equals(cnf_pswd)){
                    long value=db.addUser(user,pswd);
                    if(value>0){
                    Toast.makeText(RegisterActivity.this,"You have registered",Toast.LENGTH_SHORT).show();
                    Intent moveToLogin=new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(moveToLogin);}
                    else
                    {
                        Toast.makeText(RegisterActivity.this,"Unknown Error Try again",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Passward not matched",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
