package com.gagankaushal.portfolio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddProject extends AppCompatActivity {
    ImageView img;
    EditText title, desc;
    Button save;
    String userid = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproject);
        img = (ImageView) findViewById(R.id.userImg);
        title = (EditText) findViewById(R.id.useraddT);
        desc = (EditText) findViewById(R.id.useraddD);
        save = (Button) findViewById(R.id.save);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (title.getText().toString().isEmpty()) {
                        title.setError("INVALID");
                    } else if (desc.getText().toString().isEmpty()) {
                        desc.setError("INVALID");
                    }else
                    {


                    DBHelperProject dbHelper = new DBHelperProject(AddProject.this);
                    dbHelper.insert3(userid, img.getDrawable().toString(), title.getText().toString(), desc.getText().toString());
                    title.setText("");
                    desc.setText("");
                    setResult(10);
                    finish();
                }}
            });
        }
    }
