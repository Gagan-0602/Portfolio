package com.gagankaushal.portfolio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUser extends AppCompatActivity {
    EditText name, email, password, phone, address;
    Button add;
    int noofletters=12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        name = (EditText) findViewById(R.id.Aname);
        email = (EditText) findViewById(R.id.Aemail);
        password = (EditText) findViewById(R.id.Apassword);
        phone = (EditText) findViewById(R.id.Aphone);
        address = (EditText) findViewById(R.id.Aaddress);
        add = (Button) findViewById(R.id.useradd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namepatteren = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}{0,]";
                Boolean name1 = name.getText().toString().matches(namepatteren);

                String emailpatteren = "[A-Za-z0-9.-_]+@[a-zA-Z]+\\.+[a-z]";
                Boolean email1 = email.getText().toString().matches(emailpatteren);
                if (!name1)
                {
                    Toast.makeText(AddUser.this, "Enter valid name", Toast.LENGTH_SHORT).show();
                }
                else if (name.length()<3)
                {
                    name.setError("Invalid");
                }
                else if (email1) {

                    Toast.makeText(AddUser.this, "Enter mail", Toast.LENGTH_SHORT).show();

                } else if (password.length()<8)
                {
                    password.setError("please enter 8 digit password");
                } else if (address.getText().toString().isEmpty()) {
                    address.setError("INVALID DATA");
                } else if (phone.length() != 10)
                {
                    phone.setError("please enter 10 digit phone number ");
                } else {
                    DBHelperUser dbHelperUser = new DBHelperUser(AddUser.this);
                    dbHelperUser.insert_user(name.getText().toString(), email.getText().toString(), password.getText().toString(), phone.getText().toString(), address.getText().toString());
                    name.setText("");
                    email.setText("");
                    password.setText("");
                    phone.setText("");
                    address.setText("");
                    setResult(123);
                    finish();
                }
            }
        });
    }
}
