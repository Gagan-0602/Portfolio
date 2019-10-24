package com.gagankaushal.portfolio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_Contact extends AppCompatActivity {
    EditText subject,message;
    Button save;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        subject=(EditText)findViewById(R.id.Asubject);
        message=(EditText)findViewById(R.id.Amessage);
        save=(Button)findViewById(R.id.contactusbutton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(subject.getText().toString().isEmpty())
                {
                    subject.setError("INVALID");
                }else if(message.getText().toString().isEmpty())
                {
                    message.setError("INVALID");
                }else
                {
                    DBHelperProject dbHelperProject=new DBHelperProject(Add_Contact.this);
                    dbHelperProject.contact_insert(subject.getText().toString(),message.getText().toString());
                    subject.setText("");
                    message.setText("");
                    setResult(123);
                    finish();
                }
            }
        });
    }
}
