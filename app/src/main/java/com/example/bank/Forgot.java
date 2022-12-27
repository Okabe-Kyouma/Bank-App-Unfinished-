package com.example.bank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Forgot extends AppCompatActivity {

    EditText number;
    Button GetData;
    TextView t1,t2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Retrieve Password");

        Database db = new Database(Forgot.this);

        number = findViewById(R.id.NumberPlace);
        GetData = findViewById(R.id.GetData);
        t1 = findViewById(R.id.GetUsername);
        t2 = findViewById(R.id.GetPassword);


        GetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!number.getText().toString().isEmpty()){

                  String ans =   db.getData(number.getText().toString());

                   String[] arr = ans.split(" ");

                   t1.setText("Your Username is: " + arr[0]);
                   t2.setText( "Your Password is: " + arr[1]);

                }

            }
        });



    }
}