package com.gagankaushal.portfolio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProjectManagement extends AppCompatActivity {
    ImageView add,projectback;
    RecyclerView rv;
    ArrayList<PortfolioM> mArrayList;
    ProjectAdapter listAdapter;
    ImageView deletePbtn;
TextView nodataP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_management);
        nodataP=(TextView)findViewById(R.id.nodatafoundP);
        rv = (RecyclerView) findViewById(R.id.projectMrecycle);
        projectback=(ImageView)findViewById(R.id.projectback);
        deletePbtn=(ImageView)findViewById(R.id.deleteAllproject) ;
        LinearLayoutManager manager = new LinearLayoutManager(ProjectManagement.this, LinearLayoutManager.VERTICAL, false);
        mArrayList = new ArrayList();
        listAdapter = new ProjectAdapter(ProjectManagement.this, mArrayList,deletePbtn);
        rv.setLayoutManager(manager);
        rv.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        refreshProjectData();
        add = (ImageView) findViewById(R.id.addproject);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProjectManagement.this, AddProject.class);
                startActivityForResult(intent, 10);

            }
        });
        projectback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProjectManagement.this,Dashboard.class);
                startActivity(intent);
            }
        });
    }


    public void refreshProjectData() {
        mArrayList.clear();
        Context context = ProjectManagement.this;
        listAdapter.notifyDataSetChanged();
        DBHelperProject dBhelper = new DBHelperProject(context);
        mArrayList.addAll(dBhelper.getProjectdata());
        if(mArrayList.size()>0)
        {
            nodataP.setVisibility(View.GONE);
        }
        else
        {
            nodataP.setVisibility(View.VISIBLE);

        }
        listAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            refreshProjectData();
        }
    }
}
