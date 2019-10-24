package com.gagankaushal.portfolio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class Add_Rate extends AppCompatActivity {
    EditText msg;
    RatingBar bar;
    Button ratebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_rate_us);
        bar = (RatingBar) findViewById(R.id.AratingBar);
        msg = (EditText) findViewById(R.id.Aratemsg);
        ratebutton = (Button) findViewById(R.id.Aratesubmit);
        ratebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (msg.getText().toString().isEmpty()) {
                    msg.setError("INVALID");
                } else {
                    DBHelperUser dbHelperUser = new DBHelperUser(Add_Rate.this);
                    dbHelperUser.insert_feedback(String.valueOf(bar.getRating()), msg.getText().toString());
                    msg.setText("");
                    setResult(13);
                    finish();

                }
            }
        });
    }
}
