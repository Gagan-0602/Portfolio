package com.gagankaushal.portfolio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Contact_management extends AppCompatActivity {
    RecyclerView rv;
    ImageView addcontact;
    ImageView con_back;
    ImageView deletebtn;
    ArrayList<ContactM> list;
    TextView nodataC;
    ContactAdapter adapter;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_management);
        rv=(RecyclerView)findViewById(R.id.contactrecycleview);
        addcontact=(ImageView)findViewById(R.id.addcontact);
        nodataC=(TextView)findViewById(R.id.nodatafoundC);
        deletebtn=(ImageView) findViewById(R.id.deletecontactAll);
        con_back=(ImageView)findViewById(R.id.contactback);
        LinearLayoutManager manager=new LinearLayoutManager(Contact_management.this,LinearLayoutManager.VERTICAL,false);
        list= new ArrayList();
        adapter=new ContactAdapter(Contact_management.this,list,deletebtn);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        refreshContact();
        addcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Contact_management.this,Add_Contact.class);
                startActivityForResult(intent,123);
            }
        });
        con_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Contact_management.this,Dashboard.class);
            startActivity(i);
            }
        });
    }
    public  void refreshContact()
    {
      list.clear();
        Context context=Contact_management.this;
        adapter.notifyDataSetChanged();
        DBHelperProject dbHelperProject=new DBHelperProject(context);
        list.addAll(dbHelperProject.getContactdata());
        if(list.size()>0)
        {
            nodataC.setVisibility(View.GONE);
        }
        else
        {
            nodataC.setVisibility(View.VISIBLE);

        }
       adapter.notifyDataSetChanged();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==123){
            refreshContact();
        }
}}
