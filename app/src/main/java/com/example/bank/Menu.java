package com.example.bank;

import static com.example.bank.R.menu.menu;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Menu extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


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

            for (int i = 0; i < 1000; i++) {

                System.out.println("hello");
            }

            return true;
        }
        else if(id==R.id.Setting){

            return true;
        }
        else if(id==R.id.about){

            return true;
        }
        else
        return super.onOptionsItemSelected(item);


    }
}