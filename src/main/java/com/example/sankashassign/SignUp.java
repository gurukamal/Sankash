package com.example.sankashassign;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    EditText etSignUpUsername,etSignUpPassword,etSignType;
    Button btnSignUpSubmit;
    DatabaseHelper databaseHelper;
    String validate="[a-c]";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        init();

        btnSignUpSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String signUpUserNameValue = etSignUpUsername.getText().toString();
                String signUpPasswordValue = etSignUpPassword.getText().toString();
                String signUpTypeValue = etSignType.getText().toString();


                if (signUpUserNameValue.length() > 1 ||signUpTypeValue.matches(validate) && signUpTypeValue.length()==1 ) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("username", signUpUserNameValue);
                    contentValues.put("password", signUpPasswordValue);
                    contentValues.put("type", signUpTypeValue);

                    databaseHelper.insertData(contentValues);
                    Toast.makeText(SignUp.this, "register success", Toast.LENGTH_LONG).show();
                    Intent i =new Intent(SignUp.this,MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(SignUp.this, "Enter Valid data", Toast.LENGTH_LONG).show();


                }
            }

        });
    }

    public void init(){
        etSignUpUsername=findViewById(R.id.et_sign_user_name);
        etSignUpPassword=findViewById(R.id.et_sign_password);
        etSignType=findViewById(R.id.et_sign_type);
        btnSignUpSubmit=findViewById(R.id.btn_sign_submit);
        databaseHelper=new DatabaseHelper(this);
    }
}
