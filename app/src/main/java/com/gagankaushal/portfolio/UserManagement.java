package com.gagankaushal.portfolio;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class UserManagement extends AppCompatActivity {
    ImageView adduser;
    TextView nodataU;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    ImageView deleteBtn,backuser;
    ArrayList<UserM> userMArrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_management);
nodataU=(TextView)findViewById(R.id.nodatafoundU);
        recyclerView=(RecyclerView)findViewById(R.id.userrecycleview);
        backuser=(ImageView)findViewById(R.id.back);
        deleteBtn=(ImageView)findViewById(R.id.deleteAll);
        LinearLayoutManager manager=new LinearLayoutManager(UserManagement.this,LinearLayoutManager.VERTICAL,false);
        userMArrayList=new ArrayList<>();
        userAdapter=new UserAdapter(UserManagement.this,userMArrayList,deleteBtn);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();
        refresh();
        adduser=(ImageView)findViewById(R.id.adduser);
        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserManagement.this,AddUser.class);
                startActivityForResult(intent,123);
            }
        });
        backuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserManagement.this,Dashboard.class);
                startActivity(intent);
            }
        });
   /* delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

            AlertDialog.Builder builder=new AlertDialog.Builder(UserManagement.this);
            {
                builder.setTitle("DELETE");
                builder.setMessage("ARE YOU SURE FOR DELETE SELECTED ITEMS");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        }

});*/
    }



    public void refresh()
    {
        userMArrayList.clear();
        Context context=UserManagement.this;
        userAdapter.notifyDataSetChanged();
        DBHelperUser dbHelperUser=new DBHelperUser(context);
        userMArrayList.addAll(dbHelperUser.getUserData());
        if(userMArrayList.size()>0)
        {
            nodataU.setVisibility(View.GONE);
        }
        else
        {
            nodataU.setVisibility(View.VISIBLE);

        }
        userAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==123){
            refresh();
        }
    }
}
