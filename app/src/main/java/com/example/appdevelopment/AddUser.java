package com.example.appdevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddUser extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);


        Button add_user_btn = (Button) findViewById(R.id.add_user);
        Button main_pg_btn = (Button) findViewById(R.id.main_page);
        add_user_btn.setOnClickListener(this);
        main_pg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ints = new Intent(getApplicationContext(), Login.class);
                startActivity(ints);

            }
        });




    }


    @Override
    public void onClick(View v) {

        EditText txt_user = (EditText) findViewById(R.id.txt_add_username);
        String username = txt_user.getText().toString();



        EditText txt_pass = (EditText) findViewById(R.id.txt_add_password);
        String password = txt_pass.getText().toString();


        List<User> listOfExistUser = Login.connect.userDAO().getAllUser();

        String user = "";

        for(int i = 0; i<listOfExistUser.size();i++){
            if(username.equals(listOfExistUser.get(i).getUsername())){
                Log.d("CHECK USERNAME", "::::already::::Exist");

                user = username;
            }

        }



        Boolean isAdmin = false;

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Sorry, Username or password is empty.",
                    Toast.LENGTH_SHORT).show();

            AlertDialog.Builder popup = new AlertDialog.Builder(this);

            popup.setTitle("Sorry....");
            popup.setMessage("Username or password is empty.");

            popup.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });

            popup.show();


            Intent intent = new Intent(this, AddUser.class);
            startActivity(intent);

        }else if(user.equals(username)){
            Toast.makeText(getApplicationContext(), "Sorry, Username already exist.",
                    Toast.LENGTH_SHORT).show();




            Intent intent = new Intent(this, AddUser.class);
            startActivity(intent);
        }
        else {

            User u = new User();
            u.setAdmin(isAdmin);
            u.setUsername(username);
            u.setPassword(password);
            // u.setEmail(email);

            // Insert into USERS

            Login.connect.userDAO().addUser(u);
            txt_user.setText("");
            txt_pass.setText("");


            Toast.makeText(getApplicationContext(), "User added", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, Login.class);
            startActivity(intent);

        }


    }
}
