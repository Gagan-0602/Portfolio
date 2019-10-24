package com.gagankaushal.portfolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Contact_tab extends Fragment {
EditText subject,msg;
Button submit;
String userId="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.contact_tab, container, false);
        TextView tv=(TextView)v.findViewById(R.id.tv);
   subject=(EditText)v.findViewById(R.id.subject);
      msg=(EditText)v.findViewById(R.id.message1);
submit=(Button)v.findViewById(R.id.contactusbutton);
 getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
      submit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            if(subject.getText().toString().isEmpty())
            {
                subject.setError("INVALID");
            }else if(msg.getText().toString().isEmpty())
            {
                msg.setError("INVALID");
            }else
            {
                DBHelperProject dbHelper1=new DBHelperProject(getActivity());
                dbHelper1.insert1(subject.getText().toString(),msg.getText().toString(),userId);
            }subject.setText("");
            msg.setText("");

          }
      });
    return v;
    }



}
