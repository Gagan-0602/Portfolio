package com.gagankaushal.portfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

public class Rating extends AppCompatActivity {
    RatingBar ratingBar;
    EditText msg;
    Button ratingsubmit;
    String userid="";
    ImageView tabrateback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rateus);
        tabrateback=(ImageView) findViewById(R.id.tabrateus);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        msg=(EditText)findViewById(R.id.ratemsg);
        ratingsubmit=(Button)findViewById(R.id.ratesubmit);
        tabrateback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Rating.this,Home.class);
                startActivity(intent);
            }
        });
        ratingsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelperProject dbHelper=new DBHelperProject(Rating.this);
                dbHelper.insert2(userid,String.valueOf(ratingBar.getRating()),msg.getText().toString());
                msg.setText("");
                finish();
            }

        });

    }
}
