package com.example.bank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

   String supreme;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        Database db = new Database(Dashboard.this);

      String value = getIntent().getStringExtra("username");

      supreme = value;

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle(value);



//       Intent intent1 = new Intent(Dashboard.this,Menu.class);

  //     startActivity(intent1);

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.MyAccount){

           Intent intent = new Intent(Dashboard.this,Myaccount.class);

           intent.putExtra("username",supreme);

           startActivity(intent);

            return true;
        }
        else if(id==R.id.Setting){

            Intent intent = new Intent(Dashboard.this,Settings.class);

            startActivity(intent);

            return true;
        }
        else if(id==R.id.about){

              Intent intent = new Intent(Dashboard.this,About.class);

              startActivity(intent);

            return true;
        }


        return super.onOptionsItemSelected(item);


    }

}