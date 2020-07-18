package com.example.sankashassign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class SuccessActivity extends AppCompatActivity {

    String getTypeVal,getUserVal,getPasswordValue;
    TextView tvShowType,tvCheck;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        tvShowType = findViewById(R.id.tv_show_type);
        tvCheck=findViewById(R.id.tv_check_type);

       Intent i=getIntent();
        getUserVal=i.getStringExtra("_username");
        getPasswordValue=i.getStringExtra("_password");


       DatabaseHelper databaseHelper=new DatabaseHelper(this);
        Cursor cursor=databaseHelper.viewData(getUserVal,getPasswordValue,getTypeVal);

        StringBuilder stringBuilder=new StringBuilder();
        while (cursor.moveToNext()){
            stringBuilder.append("\n UserName: "+cursor.getString(0)
            +"\n Password: "+cursor.getString(1) +"\nType :" +cursor.getString(2));


        }
        tvShowType.setText(stringBuilder);

    if(cursor.getString(2).equals("a")){
            Intent intent=new Intent(SuccessActivity.this,ImageFrag.class);

        } else if(cursor.getString(2).equals("b")) {
            Intent intent = new Intent(SuccessActivity.this, LeftRight.class);
        } else{
                Intent intent=new Intent(SuccessActivity.this,BottomTop.class);
        }


    }
}
