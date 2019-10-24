package com.gagankaushal.portfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText name,email,password,address,phone;
    Button signup;
    TextView loginhere;
    ImageView registerback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        address=(EditText)findViewById(R.id.address);
        phone=(EditText)findViewById(R.id.phone);
        signup=(Button)findViewById(R.id.signbutton);
     //   registerback=(ImageView)findViewById(R.id.regist);
        loginhere=(TextView)findViewById(R.id.textlog);
//        registerback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(Registration.this,Login.class);
//                startActivity(intent);
//            }
//        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namepatteren="^\\p{L}+[\\p{L}\\p{Z}\\p{P}{0,]";
                Boolean name1=name.getText().toString().matches(namepatteren);
                String emailpatteren="[A-Za-z0-9.-_]+@[a-zA-Z]+\\.+[a-z]";
                Boolean email1=email.getText().toString().matches(emailpatteren);
                if(!name1)
                {
                    Toast.makeText(Registration.this,"Enter correct format",Toast.LENGTH_SHORT).show();

                }else if(email1)
                {
                    Toast.makeText(Registration.this,"Enter mail",Toast.LENGTH_SHORT).show();
                  /*  email.setError("INVALID EMAIL");*/
                }else if(password.getText().toString().isEmpty())
                {
                   password.setError("INVALID");
                }else if(address.getText().toString().isEmpty())
                {
                    address.setError("INVALID DATA");
                }
                else if(phone.getText().toString().isEmpty())
                {
                    phone.setError("INVALID");
                }
                else
                {
                    DBHelperProject dbHelper=new DBHelperProject(Registration.this);
                    dbHelper.insert(name.getText().toString(),email.getText().toString(),password.getText().toString(),address.getText().toString(),phone.getText().toString());
                    Intent intent=new Intent(Registration.this,Home.class);
                    startActivity(intent);
                    name.setText("");
                    email.setText("");
                    password.setText("");
                    address.setText("");
                    phone.setText("");
                }
            }
        });
        loginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Registration.this,Login.class);
                startActivity(intent);

            }
        });

    }
}
