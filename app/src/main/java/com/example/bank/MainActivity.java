package com.example.bank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

   Button bSignup,bSignIn;
   EditText et1,et2;
   TextView forgot;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database db = new Database(MainActivity.this);

        bSignIn = findViewById(R.id.buttonSignIn);
        bSignup = findViewById(R.id.buttonsignup);
        et1 = findViewById(R.id.username);
        et2 = findViewById(R.id.password);
        forgot = findViewById(R.id.forgot);


        bSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(et1.getText().toString().isEmpty()){

                    et1.setError("This field can't be empty!");
                }
                if(et2.getText().toString().isEmpty()){
                    et2.setError("This field can't be empty!");
                }


                if(!et1.getText().toString().isEmpty() && !et2.getText().toString().isEmpty()){

                    String loginUsername = et1.getText().toString();

                    String loginPassword = et2.getText().toString();

                  //  String query = "select username,password from users where username = " + loginUsername +  " && " + "password = " + loginPassword;

                    try {
                        boolean bool = db.searchData(loginUsername, loginPassword);

                        System.out.println(bool);

                        if(bool){

                            Intent intent = new Intent(MainActivity.this,Dashboard.class);

                            intent.putExtra("username",et1.getText().toString());
                            startActivity(intent);

                        }
                        else{

                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                            builder.setMessage("Account Not Found!");
                            builder.setPositiveButton("Sign-Up", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    Intent intent = new Intent(MainActivity.this,Bank_Registration.class);

                                    startActivity(intent);

                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    forgot.setVisibility(View.VISIBLE);
                                }
                            });

                            AlertDialog alert  = builder.create();

                            alert.show();

                        }

                    }
                    catch (Exception e){

                        System.out.println("Exception: " + e);

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                        builder.setMessage("Account Not Found!");
                        builder.setPositiveButton("Sign-Up", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent intent = new Intent(MainActivity.this,Bank_Registration.class);

                                startActivity(intent);

                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                forgot.setVisibility(View.VISIBLE);
                            }
                        });

                        AlertDialog alert  = builder.create();

                        alert.show();


                    }



                }

            }
        });

        bSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,Bank_Registration.class);

                startActivity(intent);

            }
        });



    }

    public void onBackPressed(){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("Are you sure?");
        builder.setPositiveButton("exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(0);
            }
        });
        builder.setNegativeButton("no",null);

AlertDialog alert = builder.create();

alert.show();

    }

    public void forgot(View view){

         Intent intent = new Intent(MainActivity.this,Forgot.class);

         startActivity(intent);



    }

}