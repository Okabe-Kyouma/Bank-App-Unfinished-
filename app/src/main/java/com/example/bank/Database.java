package com.example.bank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.appcompat.app.AlertDialog;

public class Database extends SQLiteOpenHelper {

    public Database(Context context){

        super(context,"Bank.db",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

       sqLiteDatabase.execSQL("create Table users(accountNo int,email varchar(30),name text,gender varchar(10),username text primary key,password text,phoneNo text,balance int)");

    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i,int i1){

        sqLiteDatabase.execSQL("drop table if exists users");

    }

    public void insertData(int accountNo,String email,String name,String gender,String username,String password,String phoneNO,int balance){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("accountNo",accountNo);
        contentValues.put("email",email);
        contentValues.put("name",name);
        contentValues.put("gender",gender);
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("phoneNO",phoneNO);
        contentValues.put("balance",balance);
        db.insert("users",null,contentValues);
    }

    public void deleteData(int accountNo){


        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from users where accountNo = " + accountNo);

    }

    public int getAccountNO(){

           int res = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select accountNo from users";

        String[] arr = {"accountNO"};

       Cursor cursor =  db.query("users",arr,null,null,null,null,null);

       cursor.moveToFirst();

       /*
        for(cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext()){
            res = Integer.parseInt(cursor.getString(1));

        }



        */

        res = cursor.getCount();

       cursor.close();
       db.close();

       return res;

    }

    public void displayData(){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select accountNO from users where accountNo = 1";

        String[] arr = {"accountNo","email","name","gender","username","password","phoneNO","balance"};


        Cursor cursor = db.query("users",arr,null,null,null,null,null);
            //System.out.println(cursor.getInt(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(5) + " " + cursor.getInt(6));


        int ac = cursor.getColumnIndex("accountNo");
        int em = cursor.getColumnIndex("email");
        int na = cursor.getColumnIndex("name");
        int ge = cursor.getColumnIndex("gender");
        int us = cursor.getColumnIndex("username");
        int pa = cursor.getColumnIndex("password");
        int ph = cursor.getColumnIndex("phoneNO");
        int bl = cursor.getColumnIndex("balance");

        System.out.println(ac + " " + em + " " + na + " " + ge + " " + us + " " + pa + " " + ph + " " + bl);

        cursor.moveToFirst();

       for(cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext()){

           System.out.println(cursor.getString(ac) + " " + cursor.getString(em) + " " + cursor.getString(na) + " " + cursor.getString(ge) + " " + cursor.getString(us) + " " + cursor.getString(pa) + " " + cursor.getString(6) + " " + cursor.getString(bl));

       }

        db.close();
        cursor.close();



    }

    public boolean searchData(String username1,String password1){

        SQLiteDatabase db = getReadableDatabase();

        String[] arr = {"username","password"};


        Cursor cursor = db.query("users",arr,null,null,null,null,null);
        //System.out.println(cursor.getInt(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(5) + " " + cursor.getInt(6));



        int us = cursor.getColumnIndex("username");
        int pa = cursor.getColumnIndex("password");


        cursor.moveToFirst();

        for(cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext()){

            //System.out.println(cursor.getString(ac) + " " + cursor.getString(na) + " " + cursor.getString(us) + " " + cursor.getString(pa) + " " + cursor.getString(4) + " " + cursor.getString(bl));

            if(cursor.getString(us).equals(username1) && cursor.getString(pa).equals(password1)){

                return true;

            }

        }

        db.close();
        cursor.close();


      return false;



    }

    public String getData(String number){


        SQLiteDatabase db = getReadableDatabase();

        String[] arr = {"username","password","phoneNO"};

        Cursor cursor = db.query("users",arr,null,null,null,null,null);

        int us = cursor.getColumnIndex("username");
        int pa = cursor.getColumnIndex("password");

        int nu = cursor.getColumnIndex("phoneNO");

        cursor.moveToFirst();

        System.out.println(us + " " + pa + " " + nu + " ");


     for(;!cursor.isAfterLast();cursor.moveToNext()) {
        if (cursor.getString(2).equals(number)) {

         return cursor.getString(us) + " " + cursor.getString(pa) + "";

        }
        }


         return "could Not found any account associated with it!";

        }

    public String searchData(String username1){

        SQLiteDatabase db = getReadableDatabase();

        String[] arr = {"name","email","username","accountNo","phoneNO","gender"};


        Cursor cursor = db.query("users",arr,null,null,null,null,null);
        //System.out.println(cursor.getInt(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(5) + " " + cursor.getInt(6));


        int na = cursor.getColumnIndex("name");
        int em= cursor.getColumnIndex("email");
        int us = cursor.getColumnIndex("username");
        int ac = cursor.getColumnIndex("accountNo");
        int ph = cursor.getColumnIndex("phoneNO");
        int ge = cursor.getColumnIndex("gender");

        for (int i = 0; i < 10; i++) {
            System.out.println(na + " " + em + " " + us + " " + ac + " " + ph + " " + ge);
        }

        cursor.moveToFirst();

        for(cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext()){

            //System.out.println(cursor.getString(ac) + " " + cursor.getString(na) + " " + cursor.getString(us) + " " + cursor.getString(pa) + " " + cursor.getString(4) + " " + cursor.getString(bl));

            if(cursor.getString(us).equals(username1)){

               return cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(5);

            }

        }

        db.close();
        cursor.close();


        return "zero";



    }

}
