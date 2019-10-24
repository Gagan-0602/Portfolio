package com.gagankaushal.portfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    EditText username,password;
    Button login;
     Toolbar toolbar;
    TextView register;
    String mail1,pass1;
    String email,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.loginbutton);
        toolbar=(Toolbar)findViewById(R.id.toolbarlog) ;
        register=(TextView)findViewById(R.id.registertext);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                mail1= username.getText().toString();
                pass1=password.getText().toString();
                if(mail1.isEmpty())
                {
                    username.setError("INVALID");
                }
                else if(pass1.isEmpty())
                {
                    password.setError("INVALID");
                }else {

                    DBHelperProject dbHelper = new DBHelperProject(Login.this);
                    boolean valid = dbHelper.login(mail1, pass1);
                    if (valid) {
                        LocalSession localSession=new LocalSession(Login.this);
                        localSession.createSession(email,name);
                        Intent intent = new Intent(Login.this, Home.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "NOT REGISTER", Toast.LENGTH_SHORT).show();
                    }

                }username.setText("");
                password.setText("");
                }
        });
toolbar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Login.this, AndroidDatabaseManager.class);
        startActivity(intent);
    }
});
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });
          }

   /* @Override
    protected void onResume() {
        super.onResume();
       *//* LocalSession localSession = new LocalSession(Login.this);
        if (localSession.isLogin()) {
            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(Login.this, Login.class);
            startActivity(intent);
        }
    }*/
}

