package com.example.bank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bank_Registration extends AppCompatActivity {

    Button b;
    TextInputEditText name,username,password,RePassword,number,email;
    TextView t1,t2,genderMust;
    RadioButton r1,r2,r3;

    TextInputLayout l1,l2;

    String gender;

    int value = 1;

    @SuppressLint({"Id", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_registration);

        Database db = new Database(Bank_Registration.this);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("User Registration");

        r1 = findViewById(R.id.male);
        r2 = findViewById(R.id.female);
        r3 = findViewById(R.id.others);
        genderMust = findViewById(R.id.genderMust);

        value = db.getAccountNO();


        b = findViewById(R.id.registerMe);

        t1 = findViewById(R.id.PassError);
        t2 = findViewById(R.id.RePassError);
        l1 = findViewById(R.id.InputLayoutPasswordRegister);
        l2 = findViewById(R.id.InputLayoutRePasswordRegister);
        name = findViewById(R.id.nameRegister);
        username = findViewById(R.id.usernameRegister);
        password = findViewById(R.id.passwordRegister);
        email = findViewById(R.id.emailRegister);
        RePassword = findViewById(R.id.RePasswordRegister);
        number = findViewById(R.id.PhoneRegister);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(r1.isChecked()){
                    gender = r1.getText().toString();
                    genderMust.setVisibility(View.INVISIBLE);}
                else if(r2.isChecked()) {
                    gender = r2.getText().toString();
                    genderMust.setVisibility(View.INVISIBLE);
                }
                else if(r3.isChecked()) {
                    gender = r3.getText().toString();
                    genderMust.setVisibility(View.INVISIBLE);
                }
                else if(!r1.isChecked() && !r2.isChecked() && !r3.isChecked())
                    genderMust.setVisibility(View.VISIBLE);

                if(email.getText().toString().isEmpty())
                    email.setError("This field can't be empty!");
                if (name.getText().toString().isEmpty())
                    name.setError("This field can't be empty!");
                if (username.getText().toString().isEmpty())
                    username.setError("This field can't be empty!");
                if (number.getText().toString().isEmpty())
                    number.setError("This field can't be empty!");



                String passRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

                String passText = password.getText().toString();

                Pattern pattern2 = Pattern.compile(passRegex);

                Matcher matcher2 = pattern2.matcher(passText);

                boolean passwordChecker =  matcher2.matches();

                System.out.println(passwordChecker);


                if (password.getText().toString().isEmpty()) {
                    t1.setText("*This field can't be empty!");
                    t1.setVisibility(View.VISIBLE);}
                    else if(passwordChecker==false) {
                        t1.setText("Password must contain 1 digit 1 lower and upper case letter and must be of 8 or more length!");
                        t1.setVisibility(View.VISIBLE);
                    }
                else
                    t1.setVisibility(View.INVISIBLE);


                if (RePassword.getText().toString().isEmpty())
                    t2.setVisibility(View.VISIBLE);
                else if (!password.getText().toString().equals(RePassword.getText().toString())) {
                    t2.setText("Passwords Not Matching!");
                    t2.setVisibility(View.VISIBLE);
                } else
                    t2.setVisibility(View.INVISIBLE);


                String usernameRegex = "^[a-zA-Z\\s]*$";
                String usernameText = name.getText().toString();

                Pattern pattern = Pattern.compile(usernameRegex);

                Matcher matcher = pattern.matcher(usernameText);

               boolean nameChecker =  matcher.matches();

               String emailRegex = "^(.+)@(\\S+)$";
               String emailText = email.getText().toString();

               Pattern pattern1 = Pattern.compile(emailRegex);

               Matcher matcher1 = pattern1.matcher(emailText);

               boolean emailChecker1 = matcher1.matches();


               boolean emailChecker2;

               if(email.getText().toString().endsWith(".com") || email.getText().toString().endsWith(".in") || email.getText().toString().endsWith(".org") || email.getText().toString().endsWith(".net"))
                   emailChecker2=true;
               else
                   emailChecker2 = false;




                String text = number.getText().toString();

                boolean phoneChecker;

                if((text.startsWith("6") || text.startsWith("7") || text.startsWith("8") || text.startsWith("9")) && text.length()==10)
                    phoneChecker = true;
                else
                    phoneChecker = false;


                System.out.println(phoneChecker);



               if(nameChecker==true && emailChecker1==true && emailChecker2==true && passwordChecker==true && phoneChecker == true) {

                   if (!name.getText().toString().isEmpty() && !username.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && !RePassword.getText().toString().isEmpty() && !number.getText().toString().isEmpty() && password.getText().toString().equals(RePassword.getText().toString()) && !email.getText().toString().isEmpty() && (r1.isChecked() || r2.isChecked() || r3.isChecked())) {

                       db.getWritableDatabase();

                      // value++;
                       for (int a = value; a <= value; a++) {

                           db.insertData(a, email.getText().toString(), name.getText().toString(), gender, username.getText().toString(), password.getText().toString(), number.getText().toString(), 0);

                       }

                       Toast.makeText(Bank_Registration.this, "Successfully Added", Toast.LENGTH_LONG).show();


                   }

               }
               else if(nameChecker==false){


                   name.setError("Please enter a valid name!");

               }
               else if(emailChecker1==false || emailChecker2==false)
                   email.setError("Please enter a valid EmailId!");
               if(phoneChecker==false)
                   number.setError("Please enter a valid 10 digit number!");



               //db.deleteData();
                db.displayData();


            }
        });


    }
}