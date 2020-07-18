package com.example.sankashassign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText etLoginUserName,etLoginPassword,etLoginType;
    Button btnLoginSubmit;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        declare();


        btnLoginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginUserNameValue=etLoginUserName.getText().toString();
                String loginPasswordValue=etLoginPassword.getText().toString();

                if(databaseHelper.isValid(loginUserNameValue,loginPasswordValue)){
                    Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Login.this,SuccessActivity.class);

                  Intent sendUserName=i.putExtra("_username",loginUserNameValue);
                    Intent sendPassword=i.putExtra("_password",loginUserNameValue);
                    Intent sendType=i.putExtra("_type",loginUserNameValue);
                    startActivity(i);
                   /* Intent intent=new Intent(Login.this,SuccessActivity.class);
                    String getDbType=databaseHelper.getType();
                    Intent sendType=intent.putExtra("type",getDbType);
                    startActivity(intent);*/
                }
                else{
                    Toast.makeText(Login.this,"Login not Successful",Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
    public void declare(){
        etLoginUserName=findViewById(R.id.et_login_username);
        etLoginPassword=findViewById(R.id.et_login_password);
        btnLoginSubmit=findViewById(R.id.btn_login_submit);
        databaseHelper=new DatabaseHelper(this);
    }
}
