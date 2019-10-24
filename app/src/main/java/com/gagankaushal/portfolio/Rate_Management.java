package com.gagankaushal.portfolio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Rate_Management extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<RateM> arrayList;
    RateAdapter adapter;
    ImageView addrateuser,Rback;
    ImageView deleterate;
    TextView nodataR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_management);
        nodataR=(TextView)findViewById(R.id.nodatafoundR);
        rv = (RecyclerView) findViewById(R.id.raterecycleview);
        addrateuser = (ImageView) findViewById(R.id.addrateuser);
        deleterate=(ImageView)findViewById(R.id.deleteAllrates) ;
        Rback=(ImageView)findViewById(R.id.rateback);
        LinearLayoutManager manager = new LinearLayoutManager(Rate_Management.this, LinearLayoutManager.VERTICAL, false);
        arrayList = new ArrayList();
        adapter = new RateAdapter(Rate_Management.this, arrayList,deleterate);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        refreshrate();
        addrateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rate_Management.this, Add_Rate.class);
                startActivityForResult(intent, 13);

            }
        });
        Rback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Rate_Management.this,Dashboard.class);
                startActivity(intent1);
            }
        });
    }

    public void refreshrate() {
        arrayList.clear();
        Context context = Rate_Management.this;
        adapter.notifyDataSetChanged();
        DBHelperUser helperUser = new DBHelperUser(context);
        arrayList.addAll(helperUser.getFeedbackdata());
        if(arrayList.size()>0)
        {
            nodataR.setVisibility(View.GONE);
        }
        else
        {
            nodataR.setVisibility(View.VISIBLE);

        }
        adapter.notifyDataSetChanged();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 13) {
            refreshrate();
        }
    }
}
