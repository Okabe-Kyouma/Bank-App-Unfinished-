package com.example.bank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Myaccount extends AppCompatActivity {

    TextView sn,se,su,sa,sp,sg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("My Account");


        sn = findViewById(R.id.showName);
        se = findViewById(R.id.showEmail);
        su = findViewById(R.id.showUsername);
        sa = findViewById(R.id.showAccountNo);
        sp = findViewById(R.id.showPhoneNo);
        sg = findViewById(R.id.showGender);

        Database db = new Database(Myaccount.this);

        Intent intent = getIntent();

        String value = intent.getStringExtra("username");

        String str =  db.searchData(value);

        String arr[] = str.split(" ");

        System.out.println(Arrays.toString(arr) + "length: " + arr.length);

        if(arr.length==6){
            sn.setText(arr[0]);
            se.setText(arr[1]);
            su.setText(arr[2]);
            sa.setText(arr[3]);
            sp.setText(arr[4]);
            sg.setText(arr[5]);
        }
        else {
            sn.setText(arr[0] + " " + arr[1]);
            se.setText(arr[2]);
            su.setText(arr[3]);
            sa.setText(arr[4]);
            sp.setText(arr[5]);
            sg.setText(arr[6]);
        }

        System.out.println(Arrays.toString(arr));






    }


}