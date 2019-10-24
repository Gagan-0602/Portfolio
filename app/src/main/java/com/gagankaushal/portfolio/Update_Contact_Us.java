package com.gagankaushal.portfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Update_Contact_Us extends AppCompatActivity {
    EditText sub,msg;
    ContactM contactM;
    ImageView contactback1;
    Button updatecontact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_contact_us);
        sub=(EditText)findViewById(R.id.Usubject);
        msg=(EditText)findViewById(R.id.Umessage);
        contactback1=(ImageView)findViewById(R.id.updatecontactback) ;
        updatecontact=(Button) findViewById(R.id.updatecontact);
        contactM=(ContactM)getIntent().getSerializableExtra("contactdata");

        sub.setText(contactM.getSubject());
        msg.setText(contactM.getMessage());
        updatecontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelperProject helperProject=new DBHelperProject(Update_Contact_Us.this);
                helperProject.contact_update(contactM.getId(),sub.getText().toString(),msg.getText().toString());
               setResult(123);
               finish();
            }
        });
    contactback1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       Intent i=new Intent(Update_Contact_Us.this,Contact_management.class);
         startActivity(i);
    }
});
    }
}
