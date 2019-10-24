package com.gagankaushal.portfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UserUpdate extends AppCompatActivity {
     EditText name, email, password, phone, address;

    UserM  userM;
    Button update;
    ImageView userupdateback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_update_layout);
        name = (EditText) findViewById(R.id.Uname);
        email = (EditText) findViewById(R.id.Uemail);
        password = (EditText) findViewById(R.id.Upassword);
        phone = (EditText) findViewById(R.id.Uphone);
        address = (EditText) findViewById(R.id.Uaddress);
        update=(Button)findViewById(R.id.updatebutton);
      userupdateback=(ImageView)findViewById(R.id.backuserupdate);
        userM = (UserM) getIntent().getSerializableExtra("data");
         name.setText(userM.getName());
        email.setText(userM.getEmail());
        password.setText(userM.getPassword());
        address.setText(userM.getAddress());
        phone.setText(userM.getPhone());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namepatteren = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}{0,]";
                Boolean name1 = name.getText().toString().matches(namepatteren);

                String emailpatteren = "[A-Za-z0-9.-_]+@[a-zA-Z]+\\.+[a-z]";
                Boolean email1 = email.getText().toString().matches(emailpatteren);
                if (!name1)
                {
                    Toast.makeText(UserUpdate.this, "Enter valid name", Toast.LENGTH_SHORT).show();
                }
                else if (name.length()<3)
                {
                    name.setError("Invalid");
                }
                else if (email1) {

                    Toast.makeText(UserUpdate.this, "Enter mail", Toast.LENGTH_SHORT).show();

                } else if (password.length()<8)
                {
                    password.setError("please enter 8 digit password");
                } else if (address.getText().toString().isEmpty()) {
                    address.setError("INVALID DATA");
                } else if (phone.length() != 10) {
                    phone.setError("please enter 10 digit phone number ");
                }else {


                DBHelperUser dbHelperUser=new DBHelperUser(UserUpdate.this);
                dbHelperUser.update_user(     userM.getId(),name.getText().toString(),email.getText().toString(),password.getText().toString(),phone.getText().toString(),address.getText().toString());
                setResult(123);
                finish();}
            }
        });

        userupdateback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserUpdate.this,UserManagement.class);
                startActivity(intent);
            }
        });
    }
}
