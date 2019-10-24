package com.gagankaushal.portfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        TextView userM,rateM,contactusM,projectM;
        LinearLayout Ulayout,Playout,Clayout,Flayout;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        userM=(TextView)findViewById(R.id.userM);
       rateM=(TextView)findViewById(R.id.feedbackM);
       contactusM=(TextView)findViewById(R.id.contactusM);
        projectM=(TextView)findViewById(R.id.projectM);
        Ulayout=(LinearLayout)findViewById(R.id.linlayout) ;
        Playout=(LinearLayout)findViewById(R.id.linlayout4) ;
        Clayout=(LinearLayout)findViewById(R.id.linlayout2) ;
        Flayout=(LinearLayout)findViewById(R.id.linlayout1) ;
       Playout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Dashboard.this,ProjectManagement.class);
                startActivity(i);
            }
        });

       Ulayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(Dashboard.this,UserManagement.class);
               startActivity(i);
           }
       });
      Clayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(Dashboard.this,Contact_management.class);
               startActivity(i);
           }
       });
      Flayout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i=new Intent(Dashboard.this,Rate_Management.class);
              startActivity(i);
          }
      });
    }
}
